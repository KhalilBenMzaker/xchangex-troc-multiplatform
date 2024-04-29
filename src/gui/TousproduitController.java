/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author sbekr
 */
public class TousproduitController implements Initializable {

    @FXML
    private AnchorPane imageview;
    @FXML
    private Label nomProduirLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label prixlabel;
    @FXML
    private ImageView image_de_produit;
ProduitService ps = new ProduitService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
    
}
              public void setProduit(Produit e) {
        
        nomProduirLabel.setText(e.getNom_produit());
        
        descriptionLabel.setText(e.getDescription_produit());
        prixlabel.setText(String.valueOf(e.getPrix_produit()));
         String path = e.getImage_produit();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                image_de_produit.setImage(img);
        // TODO
    }  
              public String   getData(){return "slm mootaz";}

    @FXML
    private void go_to_reduction(MouseEvent event) {
        
       
                        try {
                            
                            
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("reduction.fxml"));
        Parent root = loader.load();
          ReductionController destController = loader.getController();
//          destController.prodredcu(ps.selectProduitById(ps.getProductIdByName(nomProduirLabel.getText())));
          
         destController.descriptiondata(descriptionLabel.getText());
         destController.nomdata(nomProduirLabel.getText());
         destController.receiveData(Float.parseFloat(prixlabel.getText()));
        
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
}
