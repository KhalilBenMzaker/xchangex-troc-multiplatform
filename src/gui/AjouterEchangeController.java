package gui;

import services.echangeservice;
import entities.echange;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import test.mainxchnagex;

public class AjouterEchangeController implements Initializable {

    @FXML
    private Button sub;
    @FXML
    private ComboBox<String> area_recBox;
    private final ObservableList<String> area_recList = FXCollections.observableArrayList("Produit", "Service", "Argent");
    @FXML
    private TextField LieuE;
    @FXML
    private TextField usernameE;
    @FXML
    private DatePicker DateE;
    @FXML
    private Button Nav;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        area_recBox.setItems(area_recList);
    }

    @FXML
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
    private void AddEch(ActionEvent event) throws SQLException {
        if (usernameE.getText().isEmpty() | area_recBox.getValue() == null | LieuE.getText().isEmpty() | DateE.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Please fill in the empty fields");
            a.showAndWait();
        } else {

            echangeservice srec = new echangeservice();
            
            LocalDate date = DateE.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = date.format(formatter);
           srec.ajouter(new echange(usernameE.getText(), LieuE.getText(), (String) area_recBox.getValue(), formattedDate));
            Notifications notificationBuilder = Notifications.create()
                    .title("Succes").text("Your report has been added !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.CENTER_LEFT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("clicked ON ");
                        }
                    });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
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
    }

    @FXML
    private void go_to_reclamation(ActionEvent event) {
                                                try {
          Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
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
