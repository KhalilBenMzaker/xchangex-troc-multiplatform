/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import test.mainxchnagex;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import utils.MailAPI;
import utils.SmsAPI;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLverficationController implements Initializable {

    @FXML
    private TextField tfcode;
    int code=(int)Math.floor(Math.random()*(999999-100000+1)+100000);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void envoyercodesms(ActionEvent event) {
        SmsAPI.send("+21699195169", "Veuiller saisire ce code pour verifier votre compte:"+code);
    }

    @FXML
    private void envoyercodeemail(ActionEvent event) {
        try {
            
            MailAPI.sendMail(FXMLsignupController.emailsignup, "Verfication du compte par mail", "Veuiller saisire ce code pour verifier votre compte:"+code);
            //
        } catch (MessagingException ex) {
            Logger.getLogger(FXMLverficationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verification(ActionEvent event) {
        if(Integer.valueOf(tfcode.getText())==code){
            try {
                    Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
                    stageclose.close();
                    Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLlogin.fxml"));
                    Scene scene = new Scene(root);
                    Stage primaryStage=new Stage();
                    primaryStage.setTitle("Gestion utilisateur");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(mainxchnagex.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("invalide code");
            alert.setContentText("veuillez saisire le code correct");
            alert.showAndWait();
        }
    }
    
}
