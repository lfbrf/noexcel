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
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ScriptAssert;

@ManagedBean

public class BeanValidationView {

    @Size(min = 6, message = "RA deve conter no minimo 7 digitos")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*\\d)[0-9\\d]{7,}$", message = "Campo RA deve conter apenas numeros")
    private String ra;

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    @Size(min = 6, message = "Senha deve conter no minimo 6 digitos")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "A senha deve conter no minimo uma letra e um numero")
    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassVerify() {
        return passVerify;
    }

    public void setPassVerify(String passVerify) {
        this.passVerify = passVerify;
    }
    private String passVerify;

    @Size(min = 2, max = 5)
    private String name;

    @Email(message = "Email precisa ser valido")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "^[-+]?\\d+$")
    private String pattern;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
