/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.RecoveryPass;
import br.edu.utfpr.model.TransactionProduct;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Luiz
 */
public class RecoveryPassDAO extends AbstractDAO<Long, RecoveryPass> {

    public void deletebyEmail(String email) {

        this.entityManager = JPAUtil.getEntityManager();
        String queryString = "DELETE  FROM  RecoveryPass    where email= :param";

        Query query = entityManager.createQuery(queryString);

        query.setParameter("param", email);

        System.out.println(">>>>" + query.executeUpdate());

    }
}
