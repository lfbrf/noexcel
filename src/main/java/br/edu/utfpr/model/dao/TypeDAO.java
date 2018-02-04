/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.Type;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Luiz
 */
public class TypeDAO extends AbstractDAO<Long, Type> {

    public List<Type> typesManager() {
        this.entityManager = JPAUtil.getEntityManager();
        String queryString = "SELECT o FROM Type o where o.description like '%GERENTE-%'";
        Query query = entityManager.createQuery(queryString);
        List<Type> queryResult = query.getResultList();
        return queryResult;
    }

    public List<Type> typesNotManager() {
        this.entityManager = JPAUtil.getEntityManager();
        String queryString = "SELECT  o FROM Type o where o.description not like '%GERENTE-%'";
        Query query = entityManager.createQuery(queryString);
        List<Type> queryResult = query.getResultList();
        return queryResult;
    }

}
