/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.sql.Connection;
import utils.MyDB;

/**
 *
 * @author msi
 */
public class SMSSender {
    
    Connection cnx ; 
    public SMSSender() {
    cnx = MyDB.getInstance().getCnx(); 
}
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "ACda8a095f2e33a437b224a4367ec7c448";
  public static final String AUTH_TOKEN = "67553f0c06fda15e3f9cfb15369d113e";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        PhoneNumber clientPhoneNumber = new PhoneNumber("+21626733139");
        Message message = Message.creator(clientPhoneNumber, new PhoneNumber(""), "Votre réclamation a été traitée").create();
        System.out.println(message.getSid());
  }
  
      public void SMSSender() {
    // Remplacez les informations de compte et de numéro de téléphone par les vôtres
    String accountSid = "ACda8a095f2e33a437b224a4367ec7c448";
    String authToken = "67553f0c06fda15e3f9cfb15369d113e";
 
      try {
        // Récupérer le numéro de téléphone de l'utilisateur à partir de la base de données
        
        
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
            new PhoneNumber("+21626733139"),
            new PhoneNumber("+15076088948"),
            "Votre réclamation a été traitée"
        ).create();
        
        System.out.println("SID du message : " + message.getSid());
    } catch (Exception ex) {
        System.out.println("Erreur : " + ex.getMessage());
    }

     }
}
