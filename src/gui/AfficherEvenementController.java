/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import services.EvenementService;
import test.mainxchnagex;
import utils.mailing;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherEvenementController implements Initializable {

    @FXML
    private GridPane gridEvent;

    EvenementService Ev=new EvenementService();
    @FXML
    private TextField chercherEventField;
    @FXML
    private Button ajouterEventBUTTON;
    @FXML
    private Button retourButton;
    @FXML
    private Button service;
    @FXML
    private Button livresion;
    @FXML
    private Button profile;
    @FXML
    private Button eventbuttn;
    @FXML
    private Button porduitbt;
    @FXML
    private Button comainter;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        afficherEvenement();
               
    }    


    @FXML
    private void ajouterEvenement(ActionEvent event) {
      try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("ajouterEvenement.fxml"));
            chercherEventField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void afficherEvenement(){
         try {
            List<evenement> evenements = Ev.recupererEvenement();
            gridEvent.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                EvenementController controller = loader.getController();
                controller.setEvennement(evenements.get(i));
                controller.setIdevent(evenements.get(i).getId_event());
                gridEvent.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
                if(evenements.get(i).getNbrparticipants()<=0)
                {
                 // Ev.supprimerEvenement(evenements.get(i));
                controller.arreterEvent();
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @FXML
    private void rechercherevenement(KeyEvent event) {
        try {
            List<evenement> evenements = Ev.chercherEvent(chercherEventField.getText());
            gridEvent.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
                AnchorPane pane = loader.load();         
                //passage de parametres
                EvenementController controller = loader.getController();
                controller.setEvennement(evenements.get(i));
                controller.setIdevent(evenements.get(i).getId_event());
                gridEvent.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
                if(evenements.get(i).getNbrparticipants()<=0)
                {
                 // Ev.supprimerEvenement(evenements.get(i));
                controller.arreterEvent();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    private void mailingg(ActionEvent event) throws MessagingException, AddressException, IOException {
        
         mailing mail=new mailing();
        mail.sendMail();
    }

    @FXML
    private void trierEvenement(ActionEvent event) throws SQLException {
        try {
            List<evenement> evenements = Ev.trierEvent();
            gridEvent.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
                AnchorPane pane = loader.load();      
                //passage de parametres
                EvenementController controller = loader.getController();
                controller.setEvennement(evenements.get(i));
                controller.setIdevent(evenements.get(i).getId_event());
                gridEvent.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
                if(evenements.get(i).getNbrparticipants()<=0)
                {
                 // Ev.supprimerEvenement(evenements.get(i));
                controller.arreterEvent();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
 
    }

    @FXML
    private void backtomain(ActionEvent event) {
        
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("FXMLuser.fxml"));
            chercherEventField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void go_to_service(ActionEvent event) {
                                try {
          Parent root = FXMLLoader.load(getClass().getResource("gererservice.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
    }

    @FXML
    private void go_to_levrison(ActionEvent event) {
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
    private void go_to_profil(ActionEvent event) {
                                try {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLmodificationprofile.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void got_vebement(ActionEvent event) {
    }

    @FXML
    private void goto_produit(ActionEvent event) {
                                try {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLprduout.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void goto_comanter(ActionEvent event) {
                                try {
          Parent root = FXMLLoader.load(getClass().getResource("gerercommentaire.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void deconnect(ActionEvent event) {
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
