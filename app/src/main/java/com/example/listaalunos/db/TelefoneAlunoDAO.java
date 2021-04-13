package com.example.listaalunos.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.listaalunos.model.Telefone;

@Dao
public interface TelefoneAlunoDAO {

    @Query("SELECT * FROM Telefone " +
            "WHERE alunoId = :alunoId LIMIT 1 ")
    Telefone buscaTelefone(int alunoId);

    @Insert
    void salva(Telefone...telefones);
}
