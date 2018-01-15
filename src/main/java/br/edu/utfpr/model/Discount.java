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
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal atualValue;

    public BigDecimal getAtualValue() {
        return atualValue;
    }

    public void setAtualValue(BigDecimal atualValue) {
        this.atualValue = atualValue;
    }

    public Discount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @ManyToOne
    private Product product;

    @ManyToOne
    private Type type;

    public Discount(Type type, Product product) {
        this.type = type;
        this.product = product;
    }
}
