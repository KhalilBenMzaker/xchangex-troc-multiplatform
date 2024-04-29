/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author sbekr
 */
public class AffichertousproduitController implements Initializable {

    @FXML
    private GridPane gridProduit;
    ProduitService ps = new ProduitService();
    private TextField chercherprduit;
    @FXML
    private Button butt;
    @FXML
    private TextField cherlivreso;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        
                try {
            List<Produit> evenements = ps.getAll();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tousproduit.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                TousproduitController controller = loader.getController();
                controller.setProduit(evenements.get(i));
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
        // TODO
    }    

    private void rechercherprduit(KeyEvent event) {
        
                try {
            List<Produit> evenements = ps.chercherprduit(chercherprduit.getText());
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tousproduit.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                TousproduitController controller = loader.getController();
                controller.setProduit(evenements.get(i));
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

    private void trierprduit(ActionEvent event) throws SQLException {
        
                                try {
            List<Produit> evenements = ps.trierprduit();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tousproduit.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                TousproduitController controller = loader.getController();
                controller.setProduit(evenements.get(i));
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

    @FXML
    private void trielivve(ActionEvent event) {
    }

    @FXML
    private void chercherliiiiv(KeyEvent event) {
    }

    
}
