/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import services.ServiceBadWords;
import entities.reclamation;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import services.reclamationservice;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.controlsfx.control.Notifications;
import test.mainxchnagex;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextArea textsuj;
    @FXML
    private Button sub;
    @FXML
    private Button Nav;
    @FXML
    private TextField imageEventField;
    @FXML
    private ImageView imageview;
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
    private Button echannge;
    @FXML
    private Button reclamation;
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
        // TODO
        ServiceBadWords.loadConfigs(); 
    }    

    @FXML
    private void AddRec(ActionEvent event) throws SQLException, IOException {
       
         if(textsuj.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Please fill in the empty fields");
            a.showAndWait();
        }
         
             else {
              ArrayList<String> badWordsList = ServiceBadWords.badWordsFound(textsuj.getText());
              if (badWordsList.size()>0)
              {
                ServiceBadWords.badWordsFound(textsuj.getText());
           Notifications notificationBuilder = Notifications.create()
            .title("No way !").text("Inappropriate word(s) has been detected, please rewrite it").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();}
              else{
        reclamationservice srec = new reclamationservice();
        reclamation r=new reclamation(textsuj.getText());
        r.setImage_event(imageEventField.getText());
             System.out.println(r.getDescription_reclamation());
        srec.ajouterR(r);
        String path = r.getImage_event();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your report has been added !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
       Parent root = FXMLLoader.load(getClass().getResource("Rating.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
              }
              
    }
         
    }
    

    
    private void go_to_ech(ActionEvent event) {
        try {
          Parent root = FXMLLoader.load(getClass().getResource("afficheechnage.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void go_to_rec(ActionEvent event) {
        try {
          Parent root = FXMLLoader.load(getClass().getResource("mainrecech.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException, IOException {
         Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\imageP\\"  + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
//            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
//            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            imageview.setImage(img);
            /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
            imageEventField.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
//                bou.write(b);
            }
            bin.close();
//            bou.close();
            
        } else {
            System.out.println("error");

        }
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
    private void go_to_echnage(ActionEvent event) {
                                                                try {
          Parent root = FXMLLoader.load(getClass().getResource("AjouterEchange.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void go_to_reclamation(ActionEvent event) {

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
