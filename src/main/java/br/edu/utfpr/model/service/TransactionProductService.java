/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Transaction;
import br.edu.utfpr.model.TransactionProduct;
import br.edu.utfpr.model.dao.TransactionDAO;
import br.edu.utfpr.model.dao.TransactionProductDAO;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;

/**
 *
 * @author Luiz
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Filipe
 */
public class TransactionProductService extends AbstractService<Long, TransactionProduct> {

    public TransactionProductService() {
        dao = new TransactionProductDAO();

    }

    public List<TransactionProduct> listbyId(String x) {
        //tratar transaçoes
        System.out.println("PEGOUUU");
        return ((TransactionProductDAO) dao).listbyId(x);

    }

}
