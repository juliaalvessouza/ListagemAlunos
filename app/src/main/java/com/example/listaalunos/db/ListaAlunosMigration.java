package com.example.listaalunos.db;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.listaalunos.model.TipoTelefone;

import static com.example.listaalunos.model.TipoTelefone.PROPRIO;

class ListaAlunosMigration {

//    public static final Migration[] TODAS_MIGRATION = {};

    public static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (`" +
                    "id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoDeCadastro` INTEGER)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, email, momentoDeCadastro)" +
                                "SELECT id, nome, email, momentoDeCadastro FROM aluno");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT, " +
                    "`alunoId` INTEGER NOT NULL)");

            database.execSQL("INSERT INTO Telefone (numero, alunoId)" +
                    "SELECT telefoneProprio, id FROM aluno");

            database.execSQL("UPDATE Telefone SET tipo = ?", new TipoTelefone[]{PROPRIO});

            database.execSQL("DROP TABLE aluno");

            database.execSQL("ALTER TABLE Aluno_novo RENAME TO aluno");
        }


    };



}
// SEGUE EXEMPLOS DE MIGRATIONS
//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
//        }
//
//    };
//


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
