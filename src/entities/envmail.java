
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.util.Properties; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.mail.internet.MimeMessage;

public class envmail {

   public static void sendMail(String recepient,String code) throws MessagingException{
   
       Properties properties = new Properties();
       properties.put("mail.smtp.auth","true");
       properties.put("mail.smtp.starttls.enable","true");
       properties.put("mail.smtp.host","smtp.gmail.com");
       properties.put("mail.smtp.port","587");
       
       String MyEmail = "mootaz.bekri@esprit.tn";
       String passowrd = "mootaz123";
       
       Session session = Session.getInstance(properties, new Authenticator(){
       @Override 
       protected PasswordAuthentication getPasswordAuthentication(){
           return new PasswordAuthentication(MyEmail,passowrd);
       
           
          
       }
       
   });
       
     
      Message message = prepareMessage(session, MyEmail, recepient,code);
      Transport.send(message);
       System.out.println("nice");
    
         
   }

    public static Message prepareMessage(Session session,String MyEmail , String recepient,String code) {
       try {
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(MyEmail));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
           message.setSubject("félicitation Exchngex offre code promo");
           message.setText("vtr code");
           MimeBodyPart attachmentBodyPart = new MimeBodyPart();
          
            // Create a MimeMultipart object to hold the message body parts
            MimeMultipart multipart = new MimeMultipart();

        
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(code);

            multipart.addBodyPart(messageBodyPart);

           

            Path attachmentPath = Paths.get("C:\\Users\\asus\\Desktop\\produitpi\\Exchange.png12.png");
             byte[] attachmentData = Files.readAllBytes(attachmentPath);
            attachmentBodyPart.setContent(attachmentData, Files.probeContentType(attachmentPath));

        
            attachmentBodyPart.setFileName(attachmentPath.getFileName().toString());

         
            attachmentBodyPart.setFileName(attachmentPath.getFileName().toString());

     
            multipart.addBodyPart(attachmentBodyPart);


            message.setContent(multipart);

           return message; 
       } catch (Exception ex) {
           Logger.getLogger(envmail.class.getName()).log(Level.SEVERE, null, ex);
       }return null;
    }


}