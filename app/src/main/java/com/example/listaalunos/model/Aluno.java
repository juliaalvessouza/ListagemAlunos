package com.example.listaalunos.model;



import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private  String nome;
    private  String telefoneProprio;
    private String telefoneReferencia;
    private  String email;
    private Calendar momentoDeCadastro = Calendar.getInstance();

    public boolean temIdValido(){
        return id > 0;
    }


    @Override
    public String toString() {return nome + " - " + telefoneProprio;}

    public String getNome() {
        return nome;
    }

    public String getTelefoneProprio() {
        return telefoneProprio;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefoneProprio(String telefoneProprio) {
        this.telefoneProprio = telefoneProprio;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nome;
    }

    public Calendar getMomentoDeCadastro() {
        return momentoDeCadastro;
    }

    public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
        this.momentoDeCadastro = momentoDeCadastro;
    }

    public String getTelefoneReferencia() {
        return telefoneReferencia;
    }

    public void setTelefoneReferencia(String telefoneReferencia) {
        this.telefoneReferencia = telefoneReferencia;
    }
}
