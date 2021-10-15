/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.data.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import lombok.Getter;
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
public abstract class AbstractCrud<T> {

    private static CodecRegistry pojoCodecRegistry;

    @Getter
    private String database;

    @Getter
    private String URL;
    
    
    private MongoClient mongoClient;
    private MongoCollection<Document> mongoCollection;
    private MongoDatabase bancoDeDados;
    private String collectionName;
    private Class classe;
    private static DBNAMES dbNames= new DBNAMES();;
    
    public void insertOne(T objeto) {
        this.openClient();
        mongoCollection.insertOne(parse(this.OBJtoJSON(objeto)));
        this.closeClient();
    }

    public ArrayList<T> getAll(){
        this.openClient();
        ArrayList<T> lista = new ArrayList<>();
        MongoCollection<T> collection = bancoDeDados.getCollection(collectionName,classe);
        FindIterable<T> listaBD = collection.find();
        for (T t : listaBD) {
            lista.add(t);
        }
        this.closeClient();
        return lista;
    }
    
    public AbstractCrud(String database, String URL) {
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        this.database = database;
        this.URL = URL;
        // T classe = null;
         classe =  ((Class) ((ParameterizedType) getClass()
      .getGenericSuperclass()).getActualTypeArguments()[0]);
         collectionName =classe.getSimpleName();
    }

    public AbstractCrud(){
        this(dbNames.getDB1(), dbNames.getDB1URL());
    }
    
    private boolean openClient() {
       
        mongoClient = new MongoClient(this.URL, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
       
        bancoDeDados = mongoClient.getDatabase(database).withCodecRegistry(pojoCodecRegistry);
        mongoCollection = bancoDeDados.getCollection(collectionName).withCodecRegistry(pojoCodecRegistry);
        return true;
    }

    private boolean closeClient() {
        mongoClient.close();
        return true;
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
