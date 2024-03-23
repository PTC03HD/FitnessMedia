/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.constantServices;

import com.google.gson.Gson;
import com.gym.services.entityForServices.EmailAPIchecker;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.Properties;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 *
 * @author admin
 */
public class MailService {

    public void sentEmail(String toEmail, String subject, String body) {

        // Config
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constant.MAIL, Constant.PASSWORD);
            }
        });

        // Mail info
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Constant.MAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject, "UTF-8");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public EmailAPIchecker getEmailAPIchecker(String email) throws Exception {
        HttpResponse<String> response = Unirest.get("https://emailvalidation.abstractapi.com/v1/?api_key=c35cfbe5f27540639438cd7a2743827c&email="
                + email)
                .asString();
        String responseBody = "";
        if (response.getStatus() == 200){
            responseBody = response.getBody();
        }
        Gson gson = new Gson();
        String json = responseBody;
        EmailAPIchecker emailAPIchecker = gson.fromJson(json, EmailAPIchecker.class);
        return emailAPIchecker;
    }
    
    public boolean isRealMail(String email) throws Exception {
        EmailAPIchecker detail = getEmailAPIchecker(email);
        return detail.getIs_smtp_valid().isValue() && (!detail.getIs_disposable_email().isValue());
    }
}
