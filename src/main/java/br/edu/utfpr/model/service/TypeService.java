/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Type;
import br.edu.utfpr.model.dao.TypeDAO;

/**
 *
 * @author Luiz
 */
public class TypeService extends AbstractService<Long, Type> {

    public TypeService() {
        dao = new TypeDAO();

    }
}
