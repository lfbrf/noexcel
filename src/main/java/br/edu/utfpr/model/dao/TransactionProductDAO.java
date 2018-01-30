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

    public List<TransactionProduct> listbyId(Long x) {
        this.entityManager = JPAUtil.getEntityManager();
        String queryString = "SELECT o FROM TransactionProduct o where o.transaction.id" + " = :param";

        Query query = entityManager.createQuery(queryString);

        query.setParameter("param", x);

        List<TransactionProduct> queryResult = query.getResultList();

        return queryResult;
    }

    public TransactionProduct getbyPT(long x, long y) {
        this.entityManager = JPAUtil.getEntityManager();
        String queryString = "SELECT o FROM TransactionProduct o where o.transaction.id" + " = :param" + " and product.id" + "= :pa";

        Query query = entityManager.createQuery(queryString);

        query.setParameter("param", x);
        query.setParameter("pa", y);
        List<TransactionProduct> queryResult = query.getResultList();

        TransactionProduct returnObject = null;

        if (!queryResult.isEmpty()) {
            returnObject = queryResult.get(0);
        }
        return returnObject;
    }
}
