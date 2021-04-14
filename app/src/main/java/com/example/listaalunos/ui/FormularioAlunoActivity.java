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
import com.example.listaalunos.db.TelefoneAlunoDAO;
import com.example.listaalunos.model.Aluno;
import com.example.listaalunos.model.Telefone;
import com.example.listaalunos.model.TipoTelefone;

import java.util.List;

import static com.example.listaalunos.ui.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno";
    private EditText campoNome;
    private EditText campoTelefoneProprio;
    private EditText campoTelefoneReferencia;
    private EditText campoEmail;
    private RoomAlunoDAO alunoDAO;
    private TelefoneAlunoDAO telefoneAlunoDAO;
    private Aluno aluno;
    private List<Telefone> telefonesDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        ListaAlunosDatabase database = ListaAlunosDatabase.getInstance(this);
        alunoDAO = database.getRoomAlunoDAO();
        telefoneAlunoDAO = database.getTelefoneAlunoDAO();
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
        campoEmail.setText(aluno.getEmail());
        preencheCamposDeTelefone();
    }

    private void preencheCamposDeTelefone() {
        telefonesDoAluno = telefoneAlunoDAO.buscaTodosTelefone(aluno.getId());
        for(Telefone telefone: telefonesDoAluno){
            if (telefone.getTipo() == TipoTelefone.PROPRIO){
                campoTelefoneProprio.setText(telefone.getNumero());
            } else {
                campoTelefoneReferencia.setText(telefone.getNumero());
            }
        }
    }

    private void finalizaFormulario() {
        preencheAluno();

        Telefone telefoneProprio = criaTelefone(campoTelefoneProprio, TipoTelefone.PROPRIO);
        Telefone telefoneReferencia = criaTelefone(campoTelefoneReferencia, TipoTelefone.REFERENCIA);

        if(aluno.temIdValido()){
            editaAluno(telefoneProprio, telefoneReferencia);
        } else {
            salvaAluno(telefoneProprio, telefoneReferencia);
        }
        finish();
    }

    private Telefone criaTelefone(EditText campoTelefoneProprio, TipoTelefone proprio) {
        String numeroProprio = campoTelefoneProprio.getText().toString();
        return new Telefone(numeroProprio, proprio);
    }

    private void salvaAluno(Telefone telefoneProprio, Telefone telefoneReferencia) {
        int alunoId = alunoDAO.salva(aluno).intValue();
        vinculaAlunocomTelefone(alunoId, telefoneProprio, telefoneReferencia);
        telefoneAlunoDAO.salva(telefoneProprio, telefoneReferencia);
    }

    private void editaAluno(Telefone telefoneProprio, Telefone telefoneReferencia) {
        alunoDAO.edita(aluno);
        vinculaAlunocomTelefone(aluno.getId(), telefoneProprio, telefoneReferencia);
        atualizaIdsDosTelefones(telefoneProprio, telefoneReferencia);
        telefoneAlunoDAO.atualiza(telefoneProprio, telefoneReferencia);
    }

    private void atualizaIdsDosTelefones(Telefone telefoneProprio, Telefone telefoneReferencia) {
        for(Telefone telefone : telefonesDoAluno){
            if(telefone.getTipo() == TipoTelefone.PROPRIO){
               telefoneProprio.setId(telefone.getId());
            }else {
                telefoneReferencia.setId(telefone.getId());
            }
        }
    }

    private void vinculaAlunocomTelefone( int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones){
            telefone.setAlunoId(alunoId);
        }
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
//        aluno.setTelefoneProprio(telefoneProprio);
//        aluno.setTelefoneReferencia(telefoneReferencia);
        aluno.setEmail(email);
    }
}