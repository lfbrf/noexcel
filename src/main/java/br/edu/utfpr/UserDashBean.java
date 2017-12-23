/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.User;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Luiz
 */
@Named(value = "userDashBean")

@ManagedBean

public class UserDashBean {

    private User user;

    /**
     * Creates a new instance of UserDashBean
     */
    public UserDashBean() {

    }

}
