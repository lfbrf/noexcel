/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.PagSeguro;
import br.edu.utfpr.model.dao.PagSeguroDAO;

/**
 *
 * @author Luiz
 */
public class PagSeguroService extends AbstractService<Long, PagSeguro> {

    public PagSeguroService() {
        dao = new PagSeguroDAO();

    }
}
