package com.example.listaalunos.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.listaalunos.R;
import com.example.listaalunos.daoRoom.RoomAlunoDAO;
import com.example.listaalunos.db.ListaAlunosDatabase;
import com.example.listaalunos.model.Aluno;
import static com.example.listaalunos.ui.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno";
    private EditText campoNome;
    private EditText campoTelefoneProprio;
    private EditText campoTelefoneReferencia;
    private EditText campoEmail;
    private RoomAlunoDAO alunoDAO;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        ListaAlunosDatabase database = ListaAlunosDatabase.getInstance(this);
        alunoDAO = database.getRoomAlunoDAO();
        ids();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.menu_formulario_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else{
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefoneProprio.setText(aluno.getTelefoneProprio());
        campoTelefoneReferencia.setText(aluno.getTelefoneReferencia());
        campoEmail.setText(aluno.getEmail());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if(aluno.temIdValido()){
            alunoDAO.edita(aluno);
        } else {
            alunoDAO.salva(aluno);
        }
        finish();
    }

    private void ids() {
        campoNome = findViewById(R.id.campo_nome);
        campoTelefoneProprio = findViewById(R.id.campo_telefone_proprio);
        campoTelefoneReferencia = findViewById(R.id.campo_telefone_referencia);
        campoEmail = findViewById(R.id.campo_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefoneProprio = campoTelefoneProprio.getText().toString();
        String telefoneReferencia = campoTelefoneReferencia.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefoneProprio(telefoneProprio);
        aluno.setTelefoneReferencia(telefoneReferencia);
        aluno.setEmail(email);
    }
}