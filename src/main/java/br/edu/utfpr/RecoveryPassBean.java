/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.RecoveryPass;
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.service.RecoveryPassService;
import br.edu.utfpr.model.service.UserService;
import br.edu.utfpr.util.MessageUtil;
import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.http.HttpSession;
import tls.EmailUtil;

/**
 *
 * @author Luiz
 */
@ManagedBean
@SessionScoped
public class RecoveryPassBean {

    public String getEmail() {
        return email;
    }

    public boolean isShowDiv() {
        return showDiv;
    }

    public void setShowDiv(boolean showDiv) {
        this.showDiv = showDiv;
    }

    private boolean showDiv;

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
        userService = new UserService();

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("rp", "falso");

    }
    private String email;
    private String confirmacao;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }
    private String pass;
    private String repass;

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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    private UserService userService;

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
        token = generateToken();
        setToken(token);
        rp.setToken(token);
        if (recoveryPassService.save(rp)) {

            return true;
        }

        return false;
    }

    public void istoShowRecor(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        System.out.println("OKJKOJIJ");
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String em = paramMap.get("email");
        String tk = paramMap.get("repetir");
        fc.getExternalContext().getSessionMap().put("emailusuario", em);
        fc.getExternalContext().getSessionMap().put("token", tk);
        RecoveryPass rec = recoveryPassService.getByProperty("email", em);
        System.out.println(tk + "AHA");
        if (rec != null && rec.getToken().equals(tk)) {
            nav.performNavigation("recoveryform");
        } else {
            nav.performNavigation("index");
        }

    }

    public void istoShow(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();

        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Object x = null;
        System.out.println("voasd");
        String usr_ID = (String) session.getAttribute("rp");
        System.out.println(usr_ID);
        if (usr_ID != null && usr_ID.equals("emailetoken")) {
            System.out.println("INVOssda");
            nav.performNavigation("novasenha");
        } else {
            nav.performNavigation("index");
        }

    }

    public String changePass() {
        System.out.println("AQUI <>");
        FacesContext fc = FacesContext.getCurrentInstance();

        RecoveryPass recor = recoveryPassService.getByProperty("email", getEmail());
        if (recor != null && getToken() != null && recor.getToken().equals(getToken())) {

        }
        setShowDiv(true);
        fc.getExternalContext().getSessionMap().put("rp", "emailetoken");
        return "novasenha.jsf?faces-redirect=true";

    }

    public String generateNewPass() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String tk = (String) session.getAttribute("token");
        String email_user = (String) session.getAttribute("emailusuario");
        System.out.println("1111");
        if (!pass.equals(repass)) {
            MessageUtil.showMessage("As senhas nao conferem, tente novamente", "As senhas nao conferem, tente novamente", FacesMessage.SEVERITY_INFO);

            System.out.println("2222");
            return "";
        }
        RecoveryPass rec = recoveryPassService.getByProperty("email", email_user);
        if (rec != null && rec.getToken().equals(tk)) {
            User u = userService.getByProperty("email", email_user);
            String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(pass);
            u.setPassword(sha256hex);
            System.out.println("3333");
            recoveryPassService.delete(rec);
            userService.update(u);
            MessageUtil.showMessage("Senha alterada com sucesso, faca login para continuar.", "Senha alterada com sucesso, faca login para continuar.", FacesMessage.SEVERITY_INFO);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "login.jsf?faces-redirect=true";
        }
        MessageUtil.showMessage("Ocorreu um problema, tente novamente", "Ocorreu um problema, tente novamente", FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        System.out.println("4444");
        return "login.jsf?faces-redirect=true";
    }

    public String verifyEmail() {

        if (!email.equals(confirmacao)) {
            MessageUtil.showMessage("Emails nao coicidem", "Emails nao coincidem", FacesMessage.SEVERITY_ERROR);

            return "";
        } else if (userService.getByProperty("email", email) == null) {
            MessageUtil.showMessage("Email nao vinculado a nehum cliente", "Email nao vinculado a nehum cliente", FacesMessage.SEVERITY_ERROR);

            return "";
        } else {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

            long time = cal.getTimeInMillis();

            RecoveryPass rp = recoveryPassService.getByProperty("email", email);

            if (rp != null) {
                // recoveryPassService.deletebyEmail(email); ver com o profesor
                recoveryPassService.delete(rp);
                if (time - rp.getTime() > 86400000) {
                    MessageUtil.showMessage("Link expirado", "Link expirado", FacesMessage.SEVERITY_ERROR);

                    return "";
                }
            }
            if (persistForgotUser()) {
                final String fromEmail = "onlineauctionutfpr@gmail.com"; //requires valid gmail id
                final String password = "online123"; // correct password for gmail id
                final String toEmail = email; // can be any email id

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
                System.out.println("DIVISOR");
                System.out.println(getToken());
                EmailUtil.sendEmail(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body ", getToken(), email);

                MessageUtil.showMessage("Instrucoes de troca de senha foram encaminhadas para seu email.", "Instrucoes de troca de senha foram encaminhadas para seu email.", FacesMessage.SEVERITY_INFO);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true); // usado para manter a mensagem apos redirecionar a pagina
                return "login.jsf?faces-redirect=true";
            }
        }
        return "";
    }

}
