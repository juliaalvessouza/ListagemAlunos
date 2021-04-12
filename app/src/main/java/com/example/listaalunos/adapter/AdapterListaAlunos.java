package com.example.listaalunos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.listaalunos.R;
import com.example.listaalunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaAlunos extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public AdapterListaAlunos(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_aluno, parent, false);
        Aluno alunoDevolvido = alunos.get(position);
        vincula(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincula(View viewCriada, Aluno aluno) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNomeCompleto());
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(aluno.getTelefoneProprio());
    }

    public void atualizaAluno(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
