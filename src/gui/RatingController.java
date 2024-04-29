/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class RatingController implements Initializable {

    @FXML
    private Rating ErgoRat;
    @FXML
    private Rating GraviteRat;
    @FXML
    private Rating QualiteRat;
    @FXML
    private Button sub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddRat(ActionEvent event) throws IOException {
        System.out.println("Rating Ergonomie =" +ErgoRat.getRating());
        System.out.println("Rating Gravité =" +GraviteRat.getRating());
        System.out.println("Rating Qualité =" +QualiteRat.getRating());
        Parent root = FXMLLoader.load(getClass().getResource("mainrecech.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
    }
    
}
