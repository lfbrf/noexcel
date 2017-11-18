/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Composer;
import br.edu.utfpr.model.dao.ComposerDAO;

/**
 *
 * @author Roni
 */
public class ComposerService extends AbstractService<Long, Composer>{

    public ComposerService() {
        dao = new ComposerDAO();
    }   
}
