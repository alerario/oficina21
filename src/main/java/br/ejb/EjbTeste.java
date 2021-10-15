/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ejb;

import br.data.crud.CrudTeste;
import br.data.model.Teste;
import java.util.Collection;
import javax.ejb.Stateless;

/**
 *
 * @author alexandrelerario
 */
@Stateless
public class EjbTeste {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Collection<Teste> getAll(){
        CrudTeste ct = new CrudTeste();
        return ct.getAll();
    }
    
    public void create(Teste teste){
        CrudTeste crudTeste = new CrudTeste();
        crudTeste.insertOne(teste);
    }
}
