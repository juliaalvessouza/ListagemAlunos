package com.example.listaalunos.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.listaalunos.daoRoom.RoomAlunoDAO;
import com.example.listaalunos.model.Aluno;

import static com.example.listaalunos.db.ListaAlunosMigration.TODAS_MIGRATION;


@Database(entities = {Aluno.class}, version = 2, exportSchema = false)
@TypeConverters(ConversorCalendar.class)
public abstract class ListaAlunosDatabase extends RoomDatabase {

    private static final String NOME_BD = "listaalunos.db";
    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static ListaAlunosDatabase getInstance(Context context){
        return Room.databaseBuilder(context, ListaAlunosDatabase.class, NOME_BD)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations(TODAS_MIGRATION)
                .build();

    }

}



