/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.service;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.serviceservice;

/**
 * FXML Controller class
 *
 * @author yomna
 */
public class GraphdataController implements Initializable {

    @FXML
    private AnchorPane descpane;
    @FXML
    private BarChart<?, ?> barchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         serviceservice ss = new serviceservice();
        service s = new service();
        BarChart.Series set1 = new BarChart.Series<>();
        
     
        XYChart.Series serie = new XYChart.Series();
        
        serie.setName("nbr d'occurrence");
        serie.getData().add(new XYChart.Data("facile", 5));
        serie.getData().add(new XYChart.Data("difficile", 2));
        
        barchart.getData().addAll(serie);
        // TODO
    }    

    
   
    }    
    

