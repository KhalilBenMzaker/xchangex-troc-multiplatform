/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.commentaire;
import entities.service;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.SQLException;

import java.util.List;
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

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.lang.UnsupportedOperationException;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



import services.serviceservice;
import test.mainxchnagex;

/**
 * FXML Controller class
 *
 * @author yomna
 */
public class GererserviceController implements Initializable {

    @FXML
    private TextField saisi_id_s;
    @FXML
    private TextField saisi_type_s;
    @FXML
    private TextField saisi_description_s;
    private TextField saisi_date_s;
    @FXML
    private Button ajoute_s_bt;
    @FXML
    private Button modifier_s_bt;
    @FXML
    private Button supprimer_s_bt;

    serviceservice ss = new serviceservice();
    @FXML
    private TextField saisi_titre_s;
   
    @FXML
    private TextField saisi_lieu_s;
    @FXML
    private TableView<service> servicetable;
    @FXML
    private TableColumn<service, String> titrestv;
    @FXML
    private TableColumn<service, String> typestv;
    @FXML
    private TableColumn<service, String> descriptionstv;
    @FXML
    private TableColumn<service, String> lieustv;
    @FXML
    private Text IDLABEL;
    @FXML
    private Button excel_s_bt;
    @FXML
    private Button stat_s_bt;
    @FXML
    private Button back_bt;
    @FXML
    private Button rapport;
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
    @FXML
    private Pane pnlOverview;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        getServices();
        saisi_id_s.setVisible(false);
        IDLABEL.setVisible(false);
        // TODO
    }

    @FXML
    private void ajouterservice(ActionEvent event) {
        
         service se = new service();
                try {
                   
            se.setTitre_service(saisi_titre_s.getText());
            se.setType_service(saisi_type_s.getText());
            se.setDescription_service(saisi_description_s.getText());
            se.setLieu_service(saisi_lieu_s.getText());         
            
            ss.ajouter(se);
            System.out.println("service  ajouter avec succes");
          getServices();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    


    @FXML
    private void supprimerservice(ActionEvent event) {
        
        
           service e = servicetable.getItems().get(servicetable.getSelectionModel().getSelectedIndex());
      
        try {
            ss.supprimer(e);
        } catch (SQLException ex) {
            Logger.getLogger(GererserviceController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();
        getServices();
        
       
        
    }

    @FXML
    private void modifierservice(ActionEvent event) throws SQLException {
        
        service s1 = new service();
                        try {
            
            
            s1.setId_service(Integer.valueOf(saisi_id_s.getText()));
            s1.setTitre_service(saisi_titre_s.getText());
            s1.setType_service(saisi_type_s.getText());
            s1.setDescription_service(saisi_description_s.getText());
            s1.setLieu_service(saisi_lieu_s.getText());

            ss.modifier(s1);
            System.out.println("service modifier avec succes");
         getServices();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
         
                        
    }

    

    @FXML
    private void choisirService(MouseEvent event) {
        
         service s1 = servicetable.getItems().get(servicetable.getSelectionModel().getSelectedIndex());
        
       
        saisi_id_s.setText(String.valueOf(s1.getId_service()));
        saisi_titre_s.setText(s1.getTitre_service());
        saisi_type_s.setText(s1.getType_service());
        saisi_description_s.setText(s1.getDescription_service());
        saisi_lieu_s.setText(s1.getLieu_service());

        
        
    }
    
 
   public void getServices() {  

         try {
            // TODO
            List<service> evenements = ss.recuperer();
            ObservableList<service> olp = FXCollections.observableArrayList(evenements);
            servicetable.setItems(olp);
            titrestv.setCellValueFactory(new PropertyValueFactory("titre_service"));
            typestv.setCellValueFactory(new PropertyValueFactory("type_service"));
            descriptionstv.setCellValueFactory(new PropertyValueFactory("description_service"));
            lieustv.setCellValueFactory(new PropertyValueFactory("lieu_service"));
           
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }//get events

   
   
    @FXML
    private void excelservice(ActionEvent event) {
        
        try{
    String filename="C:\\xampp\\htdocs\\fichierExcelJava\\DataserviceYomna.xls" ;
    HSSFWorkbook hwb = new HSSFWorkbook();
    HSSFSheet sheet =  hwb.createSheet("new sheet");  
    HSSFRow rowhead=   sheet.createRow((short)0);
    rowhead.createCell((short) 0).setCellValue("type service");
    rowhead.createCell((short) 1).setCellValue("titre service");
    rowhead.createCell((short) 2).setCellValue("description service ");
    rowhead.createCell((short) 3).setCellValue("lieu service ");
    
    serviceservice ss = new serviceservice();
    List<service> services = ss.readAll();
    
    for (int i = 0; i < services.size(); i++) {          
    HSSFRow row=   sheet.createRow((short)(i+1));
    row.createCell((short) 0).setCellValue(services.get(i).getType_service());
    row.createCell((short) 1).setCellValue(services.get(i).getTitre_service());
    row.createCell((short) 2).setCellValue(services.get(i).getDescription_service());
    row.createCell((short) 3).setCellValue(services.get(i).getLieu_service());
//row.createCell((short) 3).setCellValue((evenements.get(i).getDate()));
    i++;
            }
    int i=1;
    FileOutputStream fileOut =  new FileOutputStream(filename);
    hwb.write(fileOut);
    fileOut.close();
    System.out.println("Your excel file has been generated!");
    File file = new File(filename);
        if (file.exists()){ 
        if(Desktop.isDesktopSupported()){
            Desktop.getDesktop().open(file);
        }}       
} catch (Exception ex ) {
    System.out.println(ex);
}
    }

    @FXML
    private void statistiquedescription(ActionEvent event)throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("graphdata.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException{
        
         Parent root = FXMLLoader.load(getClass().getResource("ajouterservice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void rapportonaction(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("rapportservice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void go_to_service(ActionEvent event) {
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
                                try {
          Parent root = FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
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

    

   

    
