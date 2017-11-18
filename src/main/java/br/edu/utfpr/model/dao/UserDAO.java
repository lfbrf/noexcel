/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.User;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Filipe
 */
public class UserDAO extends AbstractDAO<Long, User> {

    public List<String> listByNames() {
        this.entityManager = JPAUtil.getEntityManager();
        String queryString = "SELECT name FROM User ";
        Query query = entityManager.createQuery(queryString);
        List<String> queryResult = query.getResultList();
        return queryResult;
    }

}
