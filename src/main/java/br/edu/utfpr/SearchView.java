/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.User;
import br.edu.utfpr.model.dto.UserDTO;
import br.edu.utfpr.model.service.UserService;
import br.edu.utfpr.util.MessageUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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

    private boolean isShowImage = true;
    private boolean isShowCredit = false;
    private boolean isShowExtract = false;
    private boolean isShowMeal = false;

    @PostConstruct
    public void init() {
        userService = new UserService();
    }

    public void searchUser() {
        User user = userService.getByProperty("login", getText1());
        if (user == null) {
            setIsShowImage(true);
            MessageUtil.showMessage("", "Cliente não encontrado!", FacesMessage.SEVERITY_INFO);
            return;
        }

        userDTO = new UserDTO(user.getLogin(), user.getName(), user.getBalance().toString());
        setIsShowImage(false);
    }

    public String onCreditInsertion() {
        setIsShowCredit(true);
        setIsShowExtract(false);
        setIsShowMeal(false);
        return "";
    }

    public String onShowExtract() {
        setIsShowCredit(false);
        setIsShowExtract(true);
        setIsShowMeal(false);
        return "";
    }

    public String onRegisterMeal() {
        setIsShowCredit(false);
        setIsShowExtract(false);
        setIsShowMeal(true);
        return "";
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

    public boolean isIsShowImage() {
        return isShowImage;
    }

    public void setIsShowImage(boolean isShowImage) {
        this.isShowImage = isShowImage;
    }

    public boolean isIsShowCredit() {
        return isShowCredit;
    }

    public void setIsShowCredit(boolean isShowCredit) {
        this.isShowCredit = isShowCredit;
    }

    public boolean isIsShowExtract() {
        return isShowExtract;
    }

    public void setIsShowExtract(boolean isShowExtract) {
        this.isShowExtract = isShowExtract;
    }

    public boolean isIsShowMeal() {
        return isShowMeal;
    }

    public void setIsShowMeal(boolean isShowMeal) {
        this.isShowMeal = isShowMeal;
    }

}
