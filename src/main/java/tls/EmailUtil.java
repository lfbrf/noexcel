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
import br.edu.utfpr.model.PagSeguro;
import br.edu.utfpr.model.User;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

    public static void sendEmailtoAdmin(Session session, String toEmail, String subject, String body, String reference, String email, User u, PagSeguro p) {
        try {
            MimeMessage msg = new MimeMessage(session);
            String text = "<h1>Equipe No Excel</h1>"
                    + "<h3>Usuario confirmou inserao de credito, verifique e confirme na sua conta do pagseguro.</h3>"
                    + "<p>Confira atentamente se o codigo de referencia informado abaixo esta debitado em sua conta no PagSeguro. </p>"
                    + "<p>Caso nao reconheca essa solicitacao aguarde o pagamento e informe o usuario. </p>"
                    + "<h3>Informacoes do usuario: </h3>"
                    + "<p>Nome: " + u.getName() + "</p>"
                    + "<p>Login: " + u.getLogin() + "</p>"
                    + "<p>Email: " + u.getEmail() + "</p>"
                    + "<h3>Informacoes da transacao: </h3>"
                    + "<a href=\"http://localhost:8080/new-no-excel/admin/creditos.jsf?referencia=" + reference + "&email=" + email + "\">Autorizar Credito</a>"
                    + "<p>Valor Solicitado: R$" + p.getValue() + "</p>"
                    + "<p>Codigo de Referencia: " + p.getReferenciaPS() + "</p>"
                    + "<p>Estado: " + p.getStatus() + "</p>"
                    + "<img src=\"https://image.freepik.com/fotos-gratis/textura-verde_1160-721.jpg\" alt=\"Verde\" style=\"width:800px;height:100px;\">";

            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("ru@utfpr.com", "RU-UTFPR Insercao de Credito "));

            msg.setReplyTo(InternetAddress.parse(u.getEmail(), false));

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
