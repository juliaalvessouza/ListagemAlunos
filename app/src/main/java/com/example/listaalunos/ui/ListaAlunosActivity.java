package com.example.listaalunos.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.example.listaalunos.R;
import com.example.listaalunos.model.Aluno;

import static com.example.listaalunos.ui.ConstantesActivities.CHAVE_ALUNO;


public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private ListaAlunosView listaAlunosView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        listaAlunosView = new ListaAlunosView(this);
        addNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_aluno_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.menu_remover_lista_aluno){
            listaAlunosView.confirmaRemocaoAluno(item);
        }

        return super.onContextItemSelected(item);
    }

    private void addNovoAluno() {
        final FloatingActionButton fabNovoAluno = findViewById(R.id.fab_novo_aluno);
        fabNovoAluno.setOnClickListener(v -> abreFormularioInsertAluno());
    }

    private void abreFormularioInsertAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAluno();
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.lista_de_alunos);
        listaAlunosView.configuraAdapter(listaAlunos);
        configuraClickCurto(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void configuraClickCurto(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
            abreFormularioModoEdit(alunoEscolhido);
        });
    }

    private void abreFormularioModoEdit(Aluno aluno) {
        Intent i = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        i.putExtra(CHAVE_ALUNO, aluno);
        startActivity(i);
    }
}
