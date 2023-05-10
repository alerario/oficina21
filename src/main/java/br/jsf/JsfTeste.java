/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.jsf;

import br.data.model.Teste;
import br.ejb.EjbTeste;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexandrelerario
 */
@Named(value = "jsfTeste")
@RequestScoped
public class JsfTeste {

    @EJB
    private EjbTeste ejbTeste;

    /**
     * Creates a new instance of JsfTeste
     */
    public JsfTeste() {
    }
    
    public Collection<Teste> getAll(){
        return ejbTeste.getAll();
    }
    
    public void add(){
        Teste t = new Teste();
        t.setCodigo(codigo);
        t.setNome(nome);
        ejbTeste.create(t);
    }
    
    @Getter @Setter
    private int codigo;
    
    @Getter @Setter
    private String nome;
    
}
