/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.Discount;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Luiz
 */
public class DiscountDAO extends AbstractDAO<Long, Discount> {

    public Boolean isrepeatFilds(long type_id, long product_id) {
        this.entityManager = JPAUtil.getEntityManager();
        System.out.println("-------------" + product_id);
        String queryString = "SELECT o FROM Discount o where  o.type.id =:param AND  o.product.id  =:param2";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("param", type_id);
        query.setParameter("param2", product_id);

        //query.setParameter("param2", product_id.toString());
        List<Discount> queryResult = query.getResultList();
        if (queryResult.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<Discount> listrepeatFilds(long type_id, long product_id) {
        this.entityManager = JPAUtil.getEntityManager();
        System.out.println("-------------" + product_id);
        String queryString = "SELECT o FROM Discount o where  o.type.id =:param AND  o.product.id  =:param2";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("param", type_id);
        query.setParameter("param2", product_id);

        //query.setParameter("param2", product_id.toString());
        List<Discount> queryResult = query.getResultList();
        return queryResult;
    }
}
