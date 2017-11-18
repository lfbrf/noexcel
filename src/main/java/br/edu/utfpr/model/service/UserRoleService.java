/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.dao.UserRoleDAO;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;

/**
 *
 * @author cabrito
 */
public class UserRoleService extends AbstractService<Long, UserRole> {

    public UserRoleService() {
        dao = new UserRoleDAO();
    }

    public List<UserRole> listPendents() {
        //tratar transaçoes
        List<UserRole> pendents = null;
        try {
            JPAUtil.beginTransaction();
            pendents = ((UserRoleDAO) dao).listPendents();
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollBack();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return pendents;
    }

}
