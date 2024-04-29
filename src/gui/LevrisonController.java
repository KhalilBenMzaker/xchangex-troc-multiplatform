/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.livraison;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author octanet
 */
public class LevrisonController implements Initializable {

    @FXML
    private Text date_liv;
    @FXML
    private Text type_live;
    @FXML
    private Text adresse_liv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
                  public void setlivreson(livraison e) {
        
        date_liv.setText(e.getDate_liv().toString());
        
        type_live.setText(e.getType_liv());
        adresse_liv.setText(String.valueOf(e.getAdress_liv()));

        // TODO
   }
    
}
