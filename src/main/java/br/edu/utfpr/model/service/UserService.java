/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.User;
import br.edu.utfpr.model.dao.UserDAO;
import java.util.List;

/**
 *
 * @author Filipe
 */
public class UserService extends AbstractService<Long, User> {

    public UserService() {
        dao = new UserDAO();

    }

    public List<String> listByNames() {
        //tratar transaçoes
        return ((UserDAO) dao).listByNames();
    }

    public List<User> typesNotManager() {
        return ((UserDAO) dao).typesNotManager();
    }
}
