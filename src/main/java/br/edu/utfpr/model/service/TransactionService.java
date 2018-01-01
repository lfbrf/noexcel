/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Transaction;
import br.edu.utfpr.model.dao.TransactionDAO;

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
public class TransactionService extends AbstractService<Long, Transaction> {

    public TransactionService() {
        dao = new TransactionDAO();

    }
}
