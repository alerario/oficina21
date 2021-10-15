/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.data.crud;

import br.data.model.Teste;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import static org.bson.Document.parse;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author alexandrelerario
 */
public class CrudTeste {//extends AbstractCrudOld<br.data.model.Teste> {

    
    public void persist(Teste teste){
        System.out.println("configurando codecregistry");
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

        MongoDatabase bancoDeDados = mongoClient.getDatabase("teste").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Document> testecol = bancoDeDados.getCollection("testecol").withCodecRegistry(pojoCodecRegistry);
        testecol.insertOne(parse(this.OBJtoJSON(teste)));
        mongoClient.close();
    }
    
    public List<Teste> getAll(){
        List<Teste> lt = new ArrayList<>();
        
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        
         MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

        MongoDatabase bancoDeDados = mongoClient.getDatabase("teste").withCodecRegistry(pojoCodecRegistry);
       
        MongoCollection<Teste> collection = bancoDeDados.getCollection("testecol", Teste.class);      

        FindIterable<Teste> lista = collection.find();
        for (Teste person : lista) {
            lt.add(person);
        }
               
         mongoClient.close();
       return lt;
    }
    
    private String OBJtoJSON(Object objeto) {
        //transforacao utilizando jackson
        String result = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(objeto);
        } catch (Exception e) {
            System.out.println("ERRO:" + e.getMessage());
        }
        return result;
    }
   
}
