/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.User;
import br.edu.utfpr.model.service.UserService;
import br.edu.utfpr.util.MessageUtil;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cabrito
 */
@ManagedBean
public class LoginBean implements Serializable {

    private static final String PAGINA_INDEX = "/view/ocorrencias/inicio.xhtml";

    private String usuario;
    private String senha;

    private boolean isShowLoginError = false;

    public LoginBean() {
    }

    @PostConstruct
    public void init() {
        userService = new UserService();
    }

    public String gerarHashMD5(String conteudo) {
        byte[] b;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.reset();
            b = md.digest(conteudo.getBytes());

            return new BigInteger(1, b).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    public String onClickLogar() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        System.out.println("*****************************");
        System.out.println("Login: " + this.usuario + " Senha: " + this.senha);
        boolean isloggedin = false;
        User u = userService.getByProperty("login", this.usuario);
        if (u.isCheckuser()) {
            MessageUtil.showMessage("Por Favor Aguarde!!!", "Voce deve confirmar sua condicao de bolsista no restaurante.", FacesMessage.SEVERITY_ERROR);

            return "";
        }

        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.
                    getCurrentInstance().getExternalContext().getRequest();

            //se usuário estiver logado, faz logout
            Principal userPrincipal = request.getUserPrincipal();
            if (request.getUserPrincipal() != null) {
                request.logout();
            }

            //faz login. Caso as credenciais não confiram, será disparado um exception
            request.login(this.usuario, this.senha);
            setIsShowLoginError(false);

            //com parâmetro true, sempre retorna uma sessão
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
            if (request.isUserInRole("PENDING-USER")) {
                session.setAttribute("loggedIn", true);
                return "pending-user/page?faces-redirect=true";

            } else if (request.isUserInRole("ADMIN")) {
                session.setAttribute("loggedIn", true);
                return "admin/inicio?faces-redirect=true";
            } else if (request.isUserInRole("MANAGER")) {
                session.setAttribute("loggedIn", true);
                return "gerentes/inicio?faces-redirect=true";
            }
            return "user/conta?faces-redirect=true";
        } catch (ServletException e) {
            setIsShowLoginError(true);
            MessageUtil.showMessage("Oppsss!!!", "Ocorreu uma falha na autenticação. Tente novamente!", FacesMessage.SEVERITY_ERROR);
            System.out.println(e);
        } finally {
            //tratar aqui mensagens de segurança que possam ter vindo
            //do Login Module exibindo-as na forma de FacesMessage
        }
//        String uri = "login.jsf";
//        FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
        return "";
    }

    public String logoutB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "/login?faces-redirect=true";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isIsShowLoginError() {
        return isShowLoginError;
    }

    public void setIsShowLoginError(boolean isShowLoginError) {
        this.isShowLoginError = isShowLoginError;
    }

}
