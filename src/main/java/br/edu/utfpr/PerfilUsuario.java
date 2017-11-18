/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

/**
 *
 * @author Luiz
 */
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.service.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped

public class PerfilUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();
    private List<String> paises = new ArrayList<>();
    List<User> users = null;
    List<UserRole> roles = null;

    private String nome;
    private String pais;

    public List<String> sugerirPaises(String consulta) {
        List<String> paisesSugeridos = new ArrayList<>();
        users = userService.findAll();
        UserRole defaultUserRole = new UserRole();

        List<UserRole> usuarios = null;

        System.out.println("AUTOCOMPLETE1");
        System.out.println("AUTOCOMPLETE1");
        for (User usuario : users) {
            if (usuario.getName().toLowerCase().startsWith(consulta.toLowerCase())) {
                paisesSugeridos.add(usuario.getName());
            }
        }

        return paisesSugeridos;
    }

    public void atualizar() {

        System.out.println("País: " + pais);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Perfil atualizado!"));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

}
