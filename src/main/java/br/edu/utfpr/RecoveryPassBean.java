/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.RecoveryPass;
import br.edu.utfpr.model.service.RecoveryPassService;
import java.security.SecureRandom;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import tls.EmailUtil;

/**
 *
 * @author Luiz
 */
@ManagedBean
@RequestScoped
public class RecoveryPassBean {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(String confirmacao) {
        this.confirmacao = confirmacao;
    }

    @PostConstruct
    public void init() {
        recoveryPassService = new RecoveryPassService();
    }
    private String email;
    private String confirmacao;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    private String token;

    public RecoveryPassService getRecoveryPassService() {
        return recoveryPassService;
    }

    public void setRecoveryPassService(RecoveryPassService recoveryPassService) {
        this.recoveryPassService = recoveryPassService;
    }
    private RecoveryPassService recoveryPassService;

    public String redirectForm() {
        return "recovery.jsf?faces-redirect=true";
    }
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public String generateToken() {

        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(randomString(10));
        setToken(sha256hex);
        return sha256hex;
    }

    public boolean persistForgotUser() {
        RecoveryPass rp = new RecoveryPass();
        rp.setEmail(email);
        String token = generateToken();
        rp.setToken(token);
        if (recoveryPassService.save(rp)) {
            return true;
        }
        return false;
    }

    public String verifyEmail() {

        if (!email.equals(confirmacao)) {

            return "";
        } else {
            if (persistForgotUser()) {
                final String fromEmail = "onlineauctionutfpr@gmail.com"; //requires valid gmail id
                final String password = "online123"; // correct password for gmail id
                final String toEmail = "luizfelipebasile@gmail.com"; // can be any email id

                System.out.println("TLSEmail Start");
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
                props.put("mail.smtp.port", "587"); //TLS Port
                props.put("mail.smtp.auth", "true"); //enable authentication
                props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

                //create Authenticator object to pass in Session.getInstance argument
                Authenticator auth = new Authenticator() {
                    //override the getPasswordAuthentication method
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                };
                Session session = Session.getInstance(props, auth);

                EmailUtil.sendEmail(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body ", getToken());

            }
        }
        return "";
    }

}
