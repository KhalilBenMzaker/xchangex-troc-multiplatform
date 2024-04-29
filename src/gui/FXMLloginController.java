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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLloginController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfmdp;
    ServiceUser su=new ServiceUser();
    public static int iduserglobal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        User u=su.login(tfusername.getText(), tfmdp.getText());
        
        if(u!=null){
            iduserglobal=u.getId();
           
            if(u.getRole().equals(Role.ROLE_ADMIN)){
                //interface admin
                try {
                    Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
                    stageclose.close();
                    Parent root=FXMLLoader.load(getClass().getResource("/GUI/back.fxml"));
                    Scene scene = new Scene(root);
                    Stage primaryStage=new Stage();
                    primaryStage.setTitle("Gestion utilisateur");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(mainxchnagex.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(u.getRole().equals(Role.ROLE_USER)){
                //interface client
                try {
                    Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
                    stageclose.close();
                    Parent root=FXMLLoader.load(getClass().getResource("/GUI/home.fxml"));
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
                //interface freelancer
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
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("invalide");
            alert.setContentText("Email ou usernamer ou mot de passe invalide!!");
            alert.showAndWait();
        }
    }

    

    @FXML
    private void gotoforgotpassword(MouseEvent event) {
        try {
                Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLforgotpassword.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage=new Stage();
                primaryStage.setTitle("Gestion utilisateur");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(mainxchnagex.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void gotosignup(MouseEvent event) {
        try {
                Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLsignup.fxml"));
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
