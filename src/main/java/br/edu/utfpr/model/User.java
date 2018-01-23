/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author ronifabio
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String login;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;

    @ManyToOne
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRadioVal() {
        return radioVal;
    }

    public void setRadioVal(String radioVal) {
        this.radioVal = radioVal;
    }
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    long time = cal.getTimeInMillis();

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    private String comment;
    private String radioVal;
    public String name;
    @Email(message = "Email precisa ser valido")
    private String email;

    @Size(min = 6, message = "Senha deve conter no minimo 6 digitos")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "A senha deve conter no minimo uma letra e um numero")
    private String password;

    public boolean isCheckuser() {
        return checkuser;
    }

    public void setCheckuser(boolean checkuser) {
        this.checkuser = checkuser;
    }
    private boolean checkuser;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    private BigDecimal balance;

    public User() {
        super();

    }

    public User(String name, String email, String login, String password, BigDecimal balance, boolean checkuser, long time, Type type) {
        this();
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.time = time;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
