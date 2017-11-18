/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.User;
import br.edu.utfpr.model.dto.UserDTO;
import br.edu.utfpr.model.service.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
/**
 *
 * @author Luiz
 */
import javax.faces.bean.ManagedBean;

@ManagedBean
public class SearchView {

    private UserService userService;
    private User user = null;

    private UserDTO userDTO = null;
    private String text1;

    @PostConstruct
    public void init() {
        userService = new UserService();
    }

    public void searchUser() {
        User user = userService.getByProperty("login", getText1());
        if (user == null) {
            return;
        }
        userDTO = new UserDTO(user.getLogin(), user.getName());
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

//    public void setText1(String text1) {
//        User usuario = userService.getByProperty("login", text1);
//        if (usuario != null) {
//            this.text1 = text1;
//            this.text2 = usuario.getName();
//        } else {
//            this.text1 = "Infelizmente sua busca nao retornou nehum resultado";
//        }
//
//    }
    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

}
