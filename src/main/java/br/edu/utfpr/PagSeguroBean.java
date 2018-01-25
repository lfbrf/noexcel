/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.PagSeguro;
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.service.PagSeguroService;
import br.edu.utfpr.model.service.UserService;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import tls.EmailUtil;

/**
 *
 * @author Luiz
 */
@ManagedBean
@ViewScoped
public class PagSeguroBean {

    public PagSeguroService getPagseguroService() {
        return pagseguroService;
    }

    public String getReferenciaPS() {
        return referenciaPS;
    }

    public void setReferenciaPS(String referenciaPS) {
        this.referenciaPS = referenciaPS;
    }

    private String referenciaPS;

    @PostConstruct
    public void init() {
        pagseguroService = new PagSeguroService();
        userService = new UserService();
        pagSeguro = new PagSeguro();
    }

    public PagSeguro getPagSeguro() {
        return pagSeguro;
    }

    public void setPagSeguro(PagSeguro pagSeguro) {
        this.pagSeguro = pagSeguro;
    }
    private PagSeguro pagSeguro;

    public void setPagseguroService(PagSeguroService pagseguroService) {
        this.pagseguroService = pagseguroService;
    }

    private PagSeguroService pagseguroService;

    public UserService getUserService() {
        return userService;
    }

    public boolean isShowCredits() {
        return showCredits;
    }

    public void setShowCredits(boolean showCredits) {
        this.showCredits = showCredits;
    }
    private boolean showCredits;

    public void transaction() {
        System.out.println("ME CHAMOU");
        System.out.println(referenciaPS + "EIII");
        PagSeguro ps = pagseguroService.getByProperty("referenciaPS", referenciaPS);
        System.out.println(ps.getReferenciaPS() + "EIII");
        setShowCredits(true);
        setPagSeguro(ps);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    private UserService userService;

    public boolean transaction(String code, int value) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        User us = userService.getByProperty("login", code);
        String x = "null";
        BigDecimal total = BigDecimal.valueOf(value);
        String status = "PENDENTE";
        x = (String) sessionMap.get("reference-" + value);
        System.out.println("VALOR DAQUI" + x);
        code = code + "-" + value;
        PagSeguro pag = new PagSeguro(us, total, code, status, x);
        pagseguroService.save(pag);

        return true;
    }

    public boolean isEmergency(String code, int value) {
        code = code + "-" + value;
        PagSeguro pg = pagseguroService.getByProperty("referencia", code);
        if (pg != null && pg.getStatus().equals("PENDENTE-URGENTE")) {

            return true;
        }
        System.out.println("URGENTEEEEEEEEEE");
        return false;
    }

    public boolean confirm(String code, int value) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        String x;
        User us = userService.getByProperty("login", code);
        x = (String) sessionMap.get("reference-" + value);
        PagSeguro pag = pagseguroService.getByProperty("referenciaPS", x);
        pag.setStatus("PENDENTE-URGENTE");
        pagseguroService.update(pag);
        if (us != null && code != null) {
            sendMailtoAdmin(us, code, pag);
            addMessage("Enviado solicitacao com sucesso.", "Em breve nossa equipe compensara o pagamento.");
            return true;
        }
        return false;
    }

    public String generateRandom(int val) {
        Random gerador = new Random();
        int x = 0, y = 0;
        String z;
        x = gerador.nextInt(9999999);
        y = gerador.nextInt(9999999);
        System.out.println("VALA" + x + y);
        z = "" + x + y;
        PagSeguro pag = pagseguroService.getByProperty("referencia", z);
        while (pag != null) {
            x = gerador.nextInt(9999999);
            y = gerador.nextInt(9999999);
            z = "" + y + x;
            pag = pagseguroService.getByProperty("referencia", z);
        }
        z = z + "-" + val;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("reference-" + val, z);
        return z;
    }

    public void destroyInsertion(String code, int value) {
        code = code + "-" + value;
        PagSeguro pag = pagseguroService.getByProperty("referencia", code);
        pagseguroService.delete(pag);

        addMessage("Registr apagado com sucesso.", "Nova possibilidade de compra adicionada.");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public boolean showPayments(String code, int value) {
        User us = userService.getByProperty("login", code);
        String str = Integer.toString(value);
        code = code + "-" + str;
        PagSeguro pg = pagseguroService.getByProperty("referencia", code);

        if (pg != null && pg.getId() != null) {

            return false;
        }
        return true;
    }

    public void sendMailtoAdmin(User u, String r, PagSeguro p) {
        final String fromEmail = "onlineauctionutfpr@gmail.com"; //requires valid gmail id
        final String password = "online123"; // correct password for gmail id
        // can be any email id

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
        EmailUtil.sendEmailtoAdmin(session, fromEmail, "RU-Solicitacao Credito-" + u.getName(), "RU-Solicitacao Credito-" + u.getName(), r, fromEmail, u, p);

    }
}
