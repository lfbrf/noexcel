/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Discount;
import br.edu.utfpr.model.dao.DiscountDAO;
import java.util.List;

/**
 *
 * @author Luiz
 */
public class DiscountService extends AbstractService<Long, Discount> {

    public DiscountService() {
        dao = new DiscountDAO();

    }

    public Boolean isrepeatFilds(long type_id, long product_id) {
        //tratar transaçoes
        System.out.println("PEGOUUU");
        return ((DiscountDAO) dao).isrepeatFilds(type_id, product_id);

    }

    public List<Discount> listrepeatFilds(long type_id, long product_id) {
        //tratar transaçoes
        System.out.println("PEGOUUU");
        return ((DiscountDAO) dao).listrepeatFilds(type_id, product_id);

    }
}
