/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import entities.livreur;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import services.livreurService;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import test.mainxchnagex;

/**
 * FXML Controller class
 *
 * @author octanet
 */
public class LivreurrController implements Initializable {

    @FXML
    private TextField numLivreurField;
    @FXML
    private TextField nomLivreurField;
    @FXML
    private TextField prenomLivreurField;
        @FXML
    private TableView<livreur> tableLivreur;
    @FXML
    private TableColumn<livreur,Integer> numColumn;
    @FXML
    private TableColumn<livreur, String> nom_livColumn;
    @FXML
    private TableColumn<livreur, String> prenom_livColumn;
    
    livreur v=new livreur();
    livreurService L=new livreurService();
    @FXML
    private Button modifierLivreurB;
    @FXML
    private Button ajouterLivreurB;
    @FXML
    private Button supprimerLivreurB;
    @FXML
    private TextField id_livreur;
    @FXML
    private Button map;
    @FXML
    private Button livrure;
    @FXML
    private Button levrision;
    @FXML
    private Button categbtn;
    @FXML
    private Button prodbt;
    @FXML
    private Button event;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getLivreur();
    }   
    public void setlocal(String ad){
        this.prenomLivreurField.setText(ad);
    }
    
     @FXML
    private void AjouterLivreur(ActionEvent event) {
        
        if ( (numLivreurField.getText().length() == 0) || (nomLivreurField.getText().length() == 0) || (prenomLivreurField.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();}
        
       else{
  
        livreur l = new livreur();
 
        l.setNom_liv(nomLivreurField.getText());
        l.setNum(Integer.valueOf(numLivreurField.getText()));
        l.setPrenom_liv(prenomLivreurField.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("livraison add");
            alert.setContentText("livraison added successfully!");
            alert.showAndWait();
            Notifications.create()
              .title("livreur ajouter avec succées")
              .text("Veuillez vérifier votre liste des livreurs ")
              .showWarning();
        
        
        try {
            L.ajouter(l);
           reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
     getLivreur();
    }
    }
    
    public void reset()
    {
        nomLivreurField.setText("");
            numLivreurField.setText("");
            prenomLivreurField.setText("");
        
    }

    @FXML
    private void modifierLivreur(ActionEvent event) throws SQLException {
        
        
        livreur e = new livreur();
        e.setId_livr(Integer.valueOf(id_livreur.getText()));
        e.setNum(Integer.valueOf(numLivreurField.getText()));
        e.setNom_liv(nomLivreurField.getText());
        e.setPrenom_liv(prenomLivreurField.getText());
      
        L.modifier(e);
        reset();
        getLivreur();
           
    }

    @FXML
    private void supprimerLivreur(ActionEvent event) {
        
           livreur e = tableLivreur.getItems().get(tableLivreur.getSelectionModel().getSelectedIndex());
      
        try {
            L.supprimer(e);
        } catch (SQLException ex) {
            Logger.getLogger(LivreurrController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();
        getLivreur();
    }


public void getLivreur()
{
    
     try {
            // TODO
            List<livreur> evenements = L.recuperer();
            ObservableList<livreur> olp = FXCollections.observableArrayList(evenements);
            tableLivreur.setItems(olp);
            numColumn.setCellValueFactory(new PropertyValueFactory("num"));
            nom_livColumn.setCellValueFactory(new PropertyValueFactory("nom_liv"));         
            prenom_livColumn.setCellValueFactory(new PropertyValueFactory("prenom_liv"));
          
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

}

    @FXML
    private void choisirLivreur(MouseEvent event) {
         livreur e = tableLivreur.getItems().get(tableLivreur.getSelectionModel().getSelectedIndex());
        
        //idLabel.setText(String.valueOf(e.getId_event()));
        id_livreur.setText(String.valueOf(e.getId_livr()));
        numLivreurField.setText(String.valueOf(e.getNum()));
        nomLivreurField.setText(e.getNom_liv());
        prenomLivreurField.setText(e.getPrenom_liv());
        
       
        
        
    }

   /* @FXML
    private void Recheche(ActionEvent event) throws SQLException {
        
         livreur p=new livreur();
             livreurService L=new livreurService();

       tableLivreur.setItems(L.Syrine(chercher.getText()))  ;
    }*/

    @FXML
    private void map(ActionEvent event) throws IOException {
        Parent previousScene = FXMLLoader.load(getClass().getResource("Map.fxml"));
    Scene scene = new Scene(previousScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void go_to_livreuru(ActionEvent event) {
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