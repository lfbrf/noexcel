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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@ManagedBean
@Named("registerBean")
public class RegisterBean {

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    String password;
    String confirmPassword;

    public void validatePassword(FacesContext context, UIComponent toValidate, Object value) {
        String confirm = (String) value;
        UIInput passComp = (UIInput) toValidate.getAttributes().get("passwordComponent");
        String password = (String) passComp.getValue();
        if (!password.equals(confirm)) {
            FacesMessage message = new FacesMessage("Password and Confirm Password Should match");
            throw new ValidatorException(message);
        }
    }

}
