/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.data.crud;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alexandrelerario
 */
public class EMNames implements java.io.Serializable {

    public static final String EMN1 = "testeDB";

    public static Map<String, String> getEMN1Props() {

        String heroku_db = System.getenv("DATABASE_URL"); // variavel de ambiente criada pelo Heroku
        Map<String, String> properties = new HashMap<>();

        if (heroku_db == null) { //caso nao tenhamos a variavel de ambiente       
            properties.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/teste");
            properties.put("javax.persistence.jdbc.user", "postgres");
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("javax.persistence.jdbc.password", "postgres");
        } else { 
            String jdbc_database_url = System.getenv("JDBC_DATABASE_URL");
            String jdbc_database_username = System.getenv("JDBC_DATABASE_USERNAME");
            String jdbc_database_password = System.getenv("JDBC_DATABASE_PASSWORD");
            System.out.println("==== === === IMPRIMIR VARIAVEIS PARA CONF DO BD ===");
            System.out.println("jdbc_database_url:"+jdbc_database_url);
            System.out.println("jdbc_database_username:"+jdbc_database_username);
            System.out.println("jdbc_database_password:"+jdbc_database_password);
            
            properties.put("javax.persistence.jdbc.url", jdbc_database_url);
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver"); //configurando driver para postgres

        }
        return properties;
    }

}
