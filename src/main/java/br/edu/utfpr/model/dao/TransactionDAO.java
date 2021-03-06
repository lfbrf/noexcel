/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.Transaction;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Luiz
 */
public class TransactionDAO extends AbstractDAO<Long, Transaction> {

    public List<Transaction> listbyRa(String ra) {
        this.entityManager = JPAUtil.getEntityManager();
        String queryString = "SELECT o FROM Transaction o where o.login" + " = :param";

        Query query = entityManager.createQuery(queryString);
        System.out.println("RA AQUI >>" + ra);
        query.setParameter("param", ra);

        List<Transaction> queryResult = query.getResultList();

        return queryResult;
    }
}
