/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Luiz
 */
@ManagedBean
@ViewScoped
public class Bean {

    private boolean red;

    public String getCor() {
        return "color:" + (red ? "red" : "blue");
    }

    public void alteraCor() {
        this.red = !red;
    }
}
