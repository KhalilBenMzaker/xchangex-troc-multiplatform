/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Ratingechange;
import entities.User;
import test.mainxchnagex;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.ServiceUser;



/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLmodificationprofileController implements Initializable {

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
    private Rating userratingprofile;
    
    ServiceUser su=new ServiceUser();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        User u=su.getById(FXMLloginController.iduserglobal);
        tfnom.setText(u.getNom());
        tfprenom.setText(u.getPrenom());
        tfadresse.setText(u.getAdresse());
        tfemail.setText(u.getEmail());
        tfusername.setText(u.getUsername());
        tfmdp.setText(u.getPassword());
        tfnumtel.setText(String.valueOf(u.getNumTel()));
        userratingprofile.setRating(u.getRating());
        userratingprofile.setDisable(true);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
  

  //convert String to LocalDate
  LocalDate localDate = LocalDate.parse(String.valueOf(u.getDate_naissance()), formatter);
        dpdate.setValue(localDate);
        
    }    

    @FXML
    private void modifierprofile(ActionEvent event) {
        
        User u=su.getById(FXMLloginController.iduserglobal);
            u.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setUsername(tfusername.getText());
            u.setPassword(tfmdp.getText());
            u.setAdresse(tfadresse.getText());
            u.setNumTel(Integer.valueOf(tfnumtel.getText()));
            u.setDate_naissance(Date.valueOf(dpdate.getValue()));
            su.modifier(u.getId(),u);
            
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
                    Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
                    stageclose.close();
                    Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLuser.fxml"));
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
