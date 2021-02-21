package com.techcenter.backend.services;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class SendEmail {
    public void sendmail(String Recipient,String Subject,String Content) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("moatazdaklaui3@gmail.com", "Tazou1919");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("moatazdaklaui3@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Recipient));
        msg.setSubject(Subject);
        msg.setContent(Content, "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(Content, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
       // MimeBodyPart attachPart = new MimeBodyPart();

       // attachPart.attachFile("/var/tmp/image19.png");
        //multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
