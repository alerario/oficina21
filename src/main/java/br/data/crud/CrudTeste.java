/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.data.crud;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import br.data.model.Teste;

/**
 *
 * @author alexandrelerario
 */
public class CrudTeste extends AbstractCrud<br.data.model.Teste> {

    private EntityManager em;

    public CrudTeste() {
        super(br.data.model.Teste.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }
    
}
