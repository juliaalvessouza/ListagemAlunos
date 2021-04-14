package com.example.listaalunos.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listaalunos.model.Telefone;

import java.util.List;

@Dao
public interface TelefoneAlunoDAO {

    @Query("SELECT * FROM Telefone " +
            "WHERE alunoId = :alunoId LIMIT 1 ")
    Telefone buscaTelefone(int alunoId);

    @Insert
    void salva(Telefone...telefones);

    @Query("SELECT * FROM Telefone " +
            "WHERE alunoId = :alunoId ")
    List<Telefone> buscaTodosTelefone(int alunoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone... telefones);
}
