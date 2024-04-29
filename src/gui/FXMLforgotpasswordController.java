/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import test.mainxchnagex;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import services.ServiceUser;
import utils.MailAPI;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLforgotpasswordController implements Initializable {

    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfmdp;
    @FXML
    private PasswordField tfcmdp;
    @FXML
    private TextField tfcode;
    @FXML
    private Button btnupdate;
    ServiceUser su=new ServiceUser();
    int code=(int)Math.floor(Math.random()*(999999-100000+1)+100000);
    @FXML
    private Button btnchercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tfcode.setVisible(false);
        tfmdp.setVisible(false);
        tfcmdp.setVisible(false);
        btnupdate.setVisible(false);
        // TODO
    }    

    @FXML
    private void chercherutilisateur(ActionEvent event) {
        
        if(su.checkuser(tfemail.getText())){
            try {
                MailAPI.sendMail(tfemail.getText(), "Demande de changement mot de passe", "Veuillez saisire ce code pour que vous pouvez modifier votre mot de passe:"+code);
                tfemail.setVisible(false);
                btnchercher.setVisible(false);
                tfcode.setVisible(true);
                tfmdp.setVisible(true);
                tfcmdp.setVisible(true);
                btnupdate.setVisible(true);
            } catch (MessagingException ex) {
                Logger.getLogger(FXMLforgotpasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("invalide");
            alert.setContentText("email introuvable!");
            alert.showAndWait();
        }
    }

    @FXML
    private void modifiermotdepasse(ActionEvent event) {
        String erreur=controleDeSaisie();
        if(erreur.length()>0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("invalide");
            alert.setContentText(erreur);
            alert.showAndWait();
        }
        else{
            User u=su.getUserByEmail(tfemail.getText());
            u.setPassword(tfmdp.getText());
            su.modifier(u.getId(), u);
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
    }
    public String controleDeSaisie(){
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher matcher = pattern.matcher(tfmdp.getText());
        String erreur="";
        if(!tfcode.getText().equals(code+"")){
            erreur+="-Veuillez verfier le code envoyer\n";
        }
        if(!tfmdp.getText().equals(tfcmdp.getText())){
            erreur+="-Veuillez verfier les mot de passes saisie\n";
        }
        if(!matcher.find()){
            erreur+="-Mot de passe doit etre au minimum 8 char, un char majus et un numero\n";
        }
        return erreur;
    }

    @FXML
    private void retour(ActionEvent event) {
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
    
}
