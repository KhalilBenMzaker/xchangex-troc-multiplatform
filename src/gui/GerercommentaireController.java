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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import services.commentaireservice;
import test.mainxchnagex;

/**
 * FXML Controller class
 *
 * @author yomna
 */
public class GerercommentaireController implements Initializable {


    @FXML
    private TextField saisi_contenu_cmnt;
    @FXML
    private Button ajoute_cmnt_bt;
    @FXML
    private Button modif_cmnt_bt;
    
    
    commentaireservice cs = new commentaireservice();
    
    @FXML
    private Button suprimer_cmnt;
    @FXML
    private TextField id_cmnt;
    @FXML
    private TextField saisi_id_service;
    @FXML
    private TableView<commentaire> commentTable;
    @FXML
    private TableColumn<commentaire, Integer> idServiceTv;
    @FXML
    private TableColumn<commentaire, String> contenuTv;
    @FXML
    private Button excel_c_bt;
    @FXML
    private Button back_c_bt;
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
        try {

         commentaire c = new commentaire();
           getComment();
           id_cmnt.setVisible(false);

            
        } catch (SQLException ex) {
            System.out.println(ex);
        // TODO
    } 
    }

    @FXML
    private void ajoutercmnt(ActionEvent event) {
        commentaire c = new commentaire();
                try {
            
            c.setId_service(Integer.parseInt(saisi_id_service.getText()));
            c.setContenu_commentaire(saisi_contenu_cmnt.getText());
            
            cs.ajouter(c);
            System.out.println("comment  ajouter avec succes");
           getComment();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void modifiercmnt(ActionEvent event) throws SQLException {
        commentaire c1 = new commentaire();
                        try {
            
            c1.setId_commentaire(Integer.valueOf(id_cmnt.getText()));
            c1.setId_service(Integer.valueOf(saisi_id_service.getText()));
            c1.setContenu_commentaire(saisi_contenu_cmnt.getText());
            
            cs.modifier(c1);
            System.out.println("cmnt modifier avec succes");
         getComment();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
                        getComment();
                        

        
    }

    @FXML
    private void suprimercmnt(ActionEvent event) throws SQLException {
        
               
                           commentaire e = commentTable.getItems().get(commentTable.getSelectionModel().getSelectedIndex());
      
        try {
            cs.supprimer(e);
        } catch (SQLException ex) {
            Logger.getLogger(GerercommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        getComment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();
        
                        
                        
    }
    
    
    public void getComment() throws SQLException
    {               List<commentaire> commentaires = cs.recuperer();
            ObservableList<commentaire> olp = FXCollections.observableArrayList(commentaires);
            commentTable.setItems(olp);
           // id_cmment.setCellValueFactory(new PropertyValueFactory("id_commentaire"));
            idServiceTv.setCellValueFactory(new PropertyValueFactory("id_service"));
            contenuTv.setCellValueFactory(new PropertyValueFactory("contenu_commentaire"));

    }

 

    @FXML
    private void choisirComment(MouseEvent event) {
        
        commentaire com = commentTable.getItems().get(commentTable.getSelectionModel().getSelectedIndex());

        id_cmnt.setText(String.valueOf(com.getId_commentaire()));
        saisi_id_service.setText(String.valueOf(com.getId_service()));
        saisi_contenu_cmnt.setText(com.getContenu_commentaire());
    }

    @FXML
    private void excelcommentaire(ActionEvent event) {
        
         try{
    String filename="C:\\xampp\\htdocs\\fichierExcelJava\\datacommentaire.xls" ;
    HSSFWorkbook hwb;
    hwb = new HSSFWorkbook();
    HSSFSheet sheet =  hwb.createSheet("new sheet");  
    HSSFRow rowhead=   sheet.createRow((short)0);
    rowhead.createCell((short) 0).setCellValue("type service");
    rowhead.createCell((short) 1).setCellValue("titre service");
    rowhead.createCell((short) 2).setCellValue("description service ");
    rowhead.createCell((short) 3).setCellValue("lieu service ");
    List<commentaire> commentaires = cs.recuperer();
    for (int i = 0; i < commentaires.size(); i++) {          
    HSSFRow row=   sheet.createRow((short)i);
    row.createCell((short) 0).setCellValue(commentaires.get(i).getId_service());
    row.createCell((short) 1).setCellValue(commentaires.get(i).getContenu_commentaire());
   
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
} catch ( Exception ex ) {
    System.out.println(ex);
}
    }

    @FXML
    private void back2(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("ajouterservice.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
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
