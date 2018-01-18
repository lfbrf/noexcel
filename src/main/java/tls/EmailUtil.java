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
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

    /**
     * Utility method to send simple HTML email
     *
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendEmail(Session session, String toEmail, String subject, String body, String token, String email) {
        try {
            MimeMessage msg = new MimeMessage(session);
            String text = "<h1>Equipe No Excel</h1>"
                    + "<h3>Voce solicitou mudanca de senha, para confirmar clique no link abaixo</h3>"
                    + "<a href=\"http://localhost:8080/new-no-excel/recoveryform.jsf?repetir=" + token + "&email=" + email + "\">Resetar senha</a>"
                    + "<p>Caso nao reconheca essa solicitacao entre em contato conosco; e desconsidere esse email. </p>"
                    + "<img src=\"https://image.freepik.com/fotos-gratis/textura-verde_1160-721.jpg\" alt=\"Verde\" style=\"width:800px;height:100px;\">";

            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("ru@utfpr.com", "RU-UTFPR Mudanca de Senha "));

            msg.setReplyTo(InternetAddress.parse("ru@utfpr.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(text, "utf-8", "html");
            msg.setContent(text, "text/html; charset=utf-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
