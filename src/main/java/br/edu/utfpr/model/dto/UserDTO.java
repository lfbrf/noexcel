/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dto;

/**
 *
 * @author Luiz
 */
public class UserDTO {

    private String ra;
    private String name;

    public UserDTO() {
    }

    public UserDTO(String ra, String name) {
        this.ra = ra;
        this.name = name;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
