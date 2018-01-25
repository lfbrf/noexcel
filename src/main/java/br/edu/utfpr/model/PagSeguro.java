/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Luiz
 */
@Entity
public class PagSeguro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    private String referencia;

    private String referenciaPS;

    public void insert(PagSeguro ps) {

    }

    public String getReferenciaPS() {
        return referenciaPS;
    }

    public void setReferenciaPS(String referenciaPS) {
        this.referenciaPS = referenciaPS;
    }

    public PagSeguro() {
    }

    public PagSeguro(User user, BigDecimal value, String referencia, String status, String referenciaPS) {
        this.user = user;
        this.value = value;
        this.referencia = referencia;
        this.status = status;
        this.referenciaPS = referenciaPS;
    }

    @ManyToOne
    private User user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    private BigDecimal value;

}
