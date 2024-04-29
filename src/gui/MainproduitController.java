/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sbekr
 */
public class MainproduitController implements Initializable {

    @FXML
    private Button prodbt;
    @FXML
    private Button catbtn;
    @FXML
    private Button event;
    @FXML
    private Button levrision;
    @FXML
    private Button livrure;
    @FXML
    private Button dsfdsdfssdf;
    @FXML
    private Button dffdfd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void go_to_prod(ActionEvent event) {
                try {
          Parent root = FXMLLoader.load(getClass().getResource("gereproduit.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void go_to_cat(ActionEvent event) {
        
                try {
          Parent root = FXMLLoader.load(getClass().getResource("gerecategori.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    @FXML
    private void go_to_user(ActionEvent event) {
                        try {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLgstuser.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    
    
    
    
    }

    @FXML
    private void go_to_event(ActionEvent event) {
                                try {
          Parent root = FXMLLoader.load(getClass().getResource("../gui/ajouterEvenement.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
    }

    @FXML
    private void go_to_livridipon(ActionEvent event) {
                                        try {
          Parent root = FXMLLoader.load(getClass().getResource("Livraisonn.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void go_to_livreuru(ActionEvent event) {
                                        try {
          Parent root = FXMLLoader.load(getClass().getResource("../gui/Livreurr.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void dsfffsd(ActionEvent event) {
    }
    
}
