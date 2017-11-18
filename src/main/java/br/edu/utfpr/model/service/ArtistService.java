/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Artist;
import br.edu.utfpr.model.dao.ArtistDAO;

/**
 *
 * @author Roni
 */
public class ArtistService extends AbstractService<Long, Artist>{

    public ArtistService() {
        this.dao = new ArtistDAO();
    }
    
    
    
}
