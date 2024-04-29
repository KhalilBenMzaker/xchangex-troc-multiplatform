/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.reclamation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import services.reclamationservice;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.SMSSender;
import test.mainxchnagex;


/**
 * FXML Controller class
 *
 * @author msi
 */
public class MainController implements Initializable {

    @FXML
    private TableView<reclamation> reclamationsTv;
    @FXML
    private TableColumn<reclamation, String> DateTv;
    @FXML
    private TableColumn<reclamation, String> DescriptionTv;
    @FXML
    private TableColumn<reclamation, Button> delete;
    reclamationservice rs = new reclamationservice();
    @FXML
    private Button ajouterrec;
    @FXML
    private TableColumn<reclamation, String> etat;
    @FXML
    private TableColumn<reclamation, String> imageEventTv;
    @FXML
    private Button livrure;
    @FXML
    private Button levrision;
    @FXML
    private Button categbtn;
    @FXML
    private Button prodbt;
    @FXML
    private Button echhnaggeee;
    @FXML
    private Button reclammmationbt;
    @FXML
    private Button event;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private ImageView imageview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            reclamation t = null;
           
            // TODO
            List<reclamation> Reclamations = rs.recuperer(t);
            ObservableList<reclamation> olr = FXCollections.observableArrayList(Reclamations);
            reclamationsTv.setItems(olr);
            etat.setCellValueFactory(new PropertyValueFactory<reclamation, String>("etat_rec"));
            DateTv.setCellValueFactory(new PropertyValueFactory<reclamation, String>("date_reclamation"));
            imageEventTv.setCellValueFactory(new PropertyValueFactory<reclamation, String>("image_event"));
            DescriptionTv.setCellValueFactory(new PropertyValueFactory<reclamation, String>("description_reclamation"));
            reclamationservice srec = new reclamationservice();
            
//             String path = t.getImage_event();
//               File file=new File(path);
//              Image img = new Image(file.toURI().toString());
//                imageview.setImage(img);
            
            //Edit Table
            reclamationsTv.setEditable(true);
            etat.setCellFactory(TextFieldTableCell.forTableColumn());
            ContextMenu cm = new ContextMenu();
            
            this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }  
    
    public void delete() {
        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (rs.supprimerR(reclamationsTv.getItems().get(getIndex()))) {
                                    reclamationsTv.getItems().remove(getIndex());
                                    reclamationsTv.refresh();

                                }
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    
}
    @FXML
     private void EditStatusRec(TableColumn.CellEditEvent edittedCell) throws SQLException {
        if(edittedCell.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Please fill in the empty field");
            a.showAndWait();
        }
        else {
        reclamation re = reclamationsTv.getSelectionModel().getSelectedItem();
        re.setEtat_rec(edittedCell.getNewValue().toString());
        reclamationservice srec = new reclamationservice();
        srec.modifierR(re);
        SMSSender SS = new SMSSender() ; 
        SS.SMSSender();
        JOptionPane.showMessageDialog(null, "Success !!");}  
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Updated successfully !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
    } 

    private void go_to_echnage(ActionEvent event) {
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
    private void go_to_ajoutR(ActionEvent event) {
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
    private void go_to_livreuru(ActionEvent event) {
                                                        try {
          Parent root = FXMLLoader.load(getClass().getResource("../gui/Livreurr.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void go_to_livridipon(ActionEvent event) {
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
    private void go_to_user(ActionEvent event) {
                                        try {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLgstuser.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void go_to_cat(ActionEvent event) {
                                try {
          Parent root = FXMLLoader.load(getClass().getResource("gerecategori.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void go_to_prod(ActionEvent event) {
                                try {
          Parent root = FXMLLoader.load(getClass().getResource("gereproduit.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void goo_to_echnnga(ActionEvent event) {
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
    private void go_to_reclamation(ActionEvent event) {
    }

    @FXML
    private void go_to_event(ActionEvent event) {
                                                try {
          Parent root = FXMLLoader.load(getClass().getResource("../gui/ajouterEvenement.fxml"));
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
