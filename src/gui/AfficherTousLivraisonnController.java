/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.livraisonService;

import entities.livraison;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherTousLivraisonnController implements Initializable {
livraisonService ps = new livraisonService();
boolean can;
    @FXML
    private GridPane gridProduit;
    @FXML
    private TextField cherlivreso;
    @FXML
    private Button butt;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        //        butt.setOnAction(e -> {
//    System.out.println("hi");
//});
//       
afficherliv ();

//
// TODO
// TODO
    } catch (SQLException ex) {
        Logger.getLogger(AfficherTousLivraisonnController.class.getName()).log(Level.SEVERE, null, ex);
    }
    } 
    

    public void afficherliv () throws SQLException{
    
    
                                    try {
            List<livraison> evenements = ps.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("levrison.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                LevrisonController controller = loader.getController();
                controller.setlivreson(evenements.get(i));
              
                gridProduit.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
    }

    @FXML
    private void chercherliiiiv(KeyEvent event) {
        gridProduit.getChildren().clear();
                                                    try {
            List<livraison> evenements = ps.searchByLivraison(cherlivreso.getText());
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("levrison.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                LevrisonController controller = loader.getController();
                controller.setlivreson(evenements.get(i));
              //  controller.setIdevent(evenements.get(i).getId_event());
                gridProduit.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        

    }

    @FXML
    private void trielivve(ActionEvent event) {
        gridProduit.getChildren().clear();
                                            try {
            List<livraison> evenements = ps.getAllTriType();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("levrison.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                LevrisonController controller = loader.getController();
                controller.setlivreson(evenements.get(i));
              //  controller.setIdevent(evenements.get(i).getId_event());
                gridProduit.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
