/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tls;

/**
 *
 * @author Luiz
 */
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class TLSEmail {

    /**
     * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use
     * authentication) Use Authentication: Yes Port for TLS/STARTTLS: 587
     */
    public static void main(String[] args) {
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

        EmailUtil.sendEmail(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body ", "");

    }

}
