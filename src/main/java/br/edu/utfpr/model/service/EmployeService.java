/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Employe;
import br.edu.utfpr.model.dao.EmployeDAO;

/**
 *
 * @author FELIPE
 */

public class EmployeService extends AbstractService<Long, Employe> {

    public EmployeService() {
        dao = new EmployeDAO();
    }
}
