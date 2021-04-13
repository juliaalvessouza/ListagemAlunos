package com.example.listaalunos.db;

import androidx.room.TypeConverter;

import com.example.listaalunos.model.TipoTelefone;

public class ConversorTipoTelefone {

    @TypeConverter
    public String paraString(TipoTelefone tipo){
        return tipo.name();
    }

    @TypeConverter
    public TipoTelefone paraTipoTelefone(String valor){
        if (valor != null){
            return TipoTelefone.valueOf(valor);

        }
        return TipoTelefone.PROPRIO;
    }

}
