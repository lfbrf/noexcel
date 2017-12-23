/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TransactionBean {

    private long credit;

    /**
     * Creates a new instance of TransactionBean
     */
    public TransactionBean() {
    }

    public void insertCredit() {
        System.out.println("Crédito inserido!!!!" + credit);
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

}
