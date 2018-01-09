/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author cabrito
 */
public class UserRoleDAO extends AbstractDAO<Long, UserRole> {

    public List<UserRole> listPendents() {
        this.entityManager = JPAUtil.getEntityManager();
        String queryString = "SELECT o FROM UserRole o where o.role" + " = :param";

        Query query = entityManager.createQuery(queryString);

        query.setParameter("param", "PENDING-USER");

        List<UserRole> queryResult = query.getResultList();

        return queryResult;
    }

}
