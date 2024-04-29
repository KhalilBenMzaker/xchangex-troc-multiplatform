/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.serviceservice;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author yomna
 */
public class RapportserviceController implements Initializable {

    @FXML
    
    private ChoiceBox<String> type_service;
    @FXML
    
    private Button generate_report_button;

 
    @FXML
    private TextField service_count_textfield;
    
    private final serviceservice serviceService = new serviceservice();
    @FXML
    private Label service_count_label;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialiser les choix disponibles dans la ChoiceBox
        type_service.getItems().addAll("nettoyage", "normal", " proffesionel", " jardinage");
    }

    @FXML
    public void generateReport(ActionEvent event) {
       
        String selectedServiceType = type_service.getSelectionModel().getSelectedItem();

        // get count of services of the selected type
        int serviceCount = serviceService.countServicesByType(selectedServiceType);

        // update the label to display the service count
        service_count_textfield.setText("Number of " + selectedServiceType + " services: " + serviceCount);
    }
    }

    
    

