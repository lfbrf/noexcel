/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Menu;
import br.edu.utfpr.model.dao.MenuDAO;
import java.util.List;

/**
 *
 * @author Luiz
 */
public class MenuService extends AbstractService<Long, Menu> {

    public MenuService() {
        dao = new MenuDAO();

    }

    public Boolean isrepeatFilds(long type_id, long product_id) {
        //tratar transaçoes
        System.out.println("PEGOUUU");
        return ((MenuDAO) dao).isrepeatFilds(type_id, product_id);

    }

    public Menu listrepeatFilds(long type_id, long product_id) {
        //tratar transaçoes
        System.out.println("PEGOUUUME" + type_id + "PROD" + product_id);
        return ((MenuDAO) dao).listrepeatFilds(type_id, product_id);

    }
}
