/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.TransactionProduct;

import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Luiz
 */
public class TransactionProductDAO extends AbstractDAO<Long, TransactionProduct> {

    long y = 3;

    public List<TransactionProduct> listbyId(String x) {
        this.entityManager = JPAUtil.getEntityManager();
        String queryString = "SELECT o FROM TransactionProduct o where o.id" + " = :param";

        Query query = entityManager.createQuery(queryString);

        query.setParameter("param", y);

        List<TransactionProduct> queryResult = query.getResultList();

        return queryResult;
    }
}
