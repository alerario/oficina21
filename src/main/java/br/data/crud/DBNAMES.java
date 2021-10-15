/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.data.crud;

import lombok.Getter;

/**
 *
 * @author alexandrelerario
 */
public class DBNAMES implements java.io.Serializable {

    @Getter
    private String DB1 = "testeDB"; //banco de dados default

    @Getter
    private String DB1URL = "localhost";

    public DBNAMES() {
        String BDHost = System.getenv("DATABASE_URL"); // variavel de ambiente criada para configurar a url do banco
        if(BDHost!=null){
            DB1URL=BDHost;
        }
    }

}
