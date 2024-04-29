/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Role;
import entities.User;
import test.mainxchnagex;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLsignupController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnumtel;
    @FXML
    private TextField tfadresse;
    @FXML
    private PasswordField tfmdp;
    @FXML
    private DatePicker dpdate;
    @FXML
    private RadioButton rbclient;
    @FXML
    private ToggleGroup role;
    @FXML
    private RadioButton rbadmin;
    ServiceUser su=new ServiceUser();
    public static String emailsignup;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rbclient.setSelected(true);
    }    

    @FXML
    private void signup(ActionEvent event) {
        String erreur=controleDeSaisie();
        if(erreur.length()>0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("invalide");
            alert.setContentText(erreur);
            alert.showAndWait();
        }
        else{
            User u=new User();
            u.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setUsername(tfusername.getText());
            u.setPassword(tfmdp.getText());
            u.setAdresse(tfadresse.getText());
            u.setNumTel(Integer.valueOf(tfnumtel.getText()));
            u.setRole(rbclient.isSelected()?Role.ROLE_USER:Role.ROLE_PROSELLER);
            u.setDate_naissance(Date.valueOf(dpdate.getValue()));
            su.ajouter(u);
            emailsignup=u.getEmail();
            try {
                    Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
                    stageclose.close();
                    Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLverfication.fxml"));
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
        Pattern pattern = Pattern.compile("^[A-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[A-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n" +
				")*@[A-Z0-9-]+(?:\\.[A-Z0-9-]+)*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(tfemail.getText());
        String erreur="";
        if(su.checkuser(tfemail.getText())){
            erreur+="-email existe deja\n";
        }
        if(su.checkuser(tfusername.getText())){
            erreur+="-username existe deja\n";
        }
        if(tfnom.getText().trim().isEmpty()){
            erreur+="-nom vide\n";
        }
        if(tfprenom.getText().trim().isEmpty()){
            erreur+="-prenom vide\n";
        }
        if(tfemail.getText().trim().isEmpty()){
            erreur+="-email vide\n";
        }
        if(tfusername.getText().trim().isEmpty()){
            erreur+="-username vide\n";
        }
        if(tfadresse.getText().trim().isEmpty()){
            erreur+="-adresse vide\n";
        }
        if(tfnumtel.getText().trim().isEmpty()){
            erreur+="-numtel vide\n";
        }
        if(tfmdp.getText().trim().isEmpty()){
            erreur+="-mdp vide\n";
        }
        if(dpdate.getValue()==null){
            erreur+="-date vide\n";
        }
        
        if(!matcher.find()){
            erreur+="-email incorrect\n";
        }
        if(tfmdp.getText().length()<4){
            erreur+="-mot de passe doit etre sup a 4 char\n";
        }
        return erreur;
    }

    @FXML
    private void gotologin(ActionEvent event) {
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
