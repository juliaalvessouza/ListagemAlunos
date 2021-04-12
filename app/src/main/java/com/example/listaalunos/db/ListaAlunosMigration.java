package com.example.listaalunos.db;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class ListaAlunosMigration {

    public static final Migration TODAS_MIGRATION = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
        }
    };

}

//                .addMigrations(new Migration(1, 2) {
//                    @Override
//                    public void migrate(@NonNull SupportSQLiteDatabase database) {
//                        //criar nova tabela com as informações desejadas (não podemos fazer DROP)
//                        database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
//                                "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
//                                " `nome` TEXT," +
//                                " `telefone` TEXT," +
//                                " `email` TEXT)");
//                        //copiar dados da tabela antiga para a nova
//                        database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
//                                "SELECT id, nome, telefone, email FROM aluno");
//
//                        //remover a tabela antiga
//                        database.execSQL("DROP TABLE aluno");
//
//                        // renomear a tabela nova com o nome da tabela antiga
//                        database.execSQL("ALTER TABLE Aluno_novo RENAME TO aluno");
//                    }
//                })
