package com.example.listaalunos.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.listaalunos.adapter.AdapterListaAlunos;
import com.example.listaalunos.daoRoom.RoomAlunoDAO;
import com.example.listaalunos.db.ListaAlunosDatabase;
import com.example.listaalunos.model.Aluno;

public class ListaAlunosView {

    private final Context context;
    private final AdapterListaAlunos adapter;
    private final RoomAlunoDAO aluno;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new AdapterListaAlunos(this.context);
        aluno = ListaAlunosDatabase.getInstance(context)
                .getRoomAlunoDAO();
    }

    public void confirmaRemocaoAluno(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Deseja remover o aluno?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = (Aluno) adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAluno() {
        adapter.atualizaAluno(aluno.todos());
    }

    private void remove(Aluno aluno) {
        this.aluno.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaAlunos) {
         listaAlunos.setAdapter(adapter);
    }

}
