/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Rediction;
import entities.envmail;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.text.Text;
import javax.mail.MessagingException;
import services.CategorieService;
import services.Redictionservice;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import test.mainxchnagex;

/**
 * FXML Controller class
 *
 * @author sbekr
 */
public class GerecategoriController implements Initializable {

    @FXML
    private TextField saissir_Categorie;
    @FXML
    private Text valid_Categorie;
    @FXML
    private Button ajouter_Categorie;
    @FXML
    private Button modifier_Categorie;
    @FXML
    private TableView<Categorie> table_Categorie;
    @FXML
    private TableColumn<Categorie, Integer> id_Categorie;
    @FXML
    private TableColumn<Categorie, String> nom_Categorie;
    @FXML
    private TableColumn<Categorie, Button> delate_Categorie;
    CategorieService cs = new CategorieService();
    Redictionservice rs = new Redictionservice();
    int index = -1;
    @FXML
    private TextField saisir_code_reduction;
    @FXML
    private TextField saisir_call;
    @FXML
    private Button ajouterec;
    @FXML
    private Button modifier_Categorie1;
    @FXML
    private TableView<Rediction> tab_Reduction;
    @FXML
    private TableColumn<Rediction, String> Reduction_code;
    @FXML
    private TableColumn<Rediction, Float> Reduction_val;
    @FXML
    private TableColumn<Rediction, Button> Reduction_suprimer;
    @FXML
    private ComboBox<String> box_emailuser;
    @FXML
    private ImageView QrCode;
    @FXML
    private Button livrure;
    @FXML
    private Button levrision;
    @FXML
    private Button categbtn;
    @FXML
    private Button prodbt;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<String> emails = cs.getEmails();
            ObservableList<String> olc = FXCollections.observableArrayList(emails);
            box_emailuser.setItems(olc);

            List<Categorie> categories = cs.getAll();
            ObservableList<Categorie> olp = FXCollections.observableArrayList(categories);
            table_Categorie.setItems(olp);
            id_Categorie.setCellValueFactory(new PropertyValueFactory("id_categorie"));
            nom_Categorie.setCellValueFactory(new PropertyValueFactory("nom_categorie"));

            this.delete();
            List<Rediction> Redictions = rs.getAll();
            ObservableList<Rediction> olp1 = FXCollections.observableArrayList(Redictions);
            tab_Reduction.setItems(olp1);
            Reduction_code.setCellValueFactory(new PropertyValueFactory("coder"));
            Reduction_val.setCellValueFactory(new PropertyValueFactory("valr"));
            this.suprimer();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        // TODO
    }

    @FXML
    private void ajouterCategorie(ActionEvent event) {

        try {

            if (saissir_Categorie.getText().length() == 0) {
                valid_Categorie.setText("invalide nom");
            } else {

                Categorie c = new Categorie();

                c.setNom_categorie(saissir_Categorie.getText());

                cs.ajoutercategorie(c);
                vide();
            }

            List<Categorie> categories = cs.getAll();
            ObservableList<Categorie> olp = FXCollections.observableArrayList(categories);
            table_Categorie.setItems(olp);
            id_Categorie.setCellValueFactory(new PropertyValueFactory("id_categorie"));
            nom_Categorie.setCellValueFactory(new PropertyValueFactory("nom_categorie"));

            this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void modifCategorie(ActionEvent event) {
        try {

            if (saissir_Categorie.getText().length() == 0) {
                valid_Categorie.setText("invalide nom");
            } else {

                Categorie c = new Categorie();
                c.setId_categorie(id_Categorie.getCellData(index));
                c.setNom_categorie(saissir_Categorie.getText());

                cs.updatecategorie(c);
                vide();
            }
            List<Categorie> categories = cs.getAll();
            ObservableList<Categorie> olp = FXCollections.observableArrayList(categories);
            table_Categorie.setItems(olp);
            id_Categorie.setCellValueFactory(new PropertyValueFactory("id_categorie"));
            nom_Categorie.setCellValueFactory(new PropertyValueFactory("nom_categorie"));

            this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void select_Categorie(MouseEvent event) {
        index = table_Categorie.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            return;
        }
        saissir_Categorie.setText(nom_Categorie.getCellData(index).toString());

    }

    public void delete() {
        delate_Categorie.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (cs.suprimecategorie(table_Categorie.getItems().get(getIndex()))) {
                                    table_Categorie.getItems().remove(getIndex());
                                    table_Categorie.refresh();

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

    public void vide() {
        saissir_Categorie.setText("");
        valid_Categorie.setText("");
        saisir_code_reduction.setText("");
        saisir_call.setText("");

    }

    @FXML
    private void ajouterreduction(ActionEvent event) {
        try {

            Rediction c = new Rediction();

            c.setCoder(saisir_code_reduction.getText());
            c.setValr(Float.parseFloat(saisir_call.getText()));

            rs.ajouterduction(c);
            vide();

            List<Rediction> Redictions = rs.getAll();
            ObservableList<Rediction> olp1 = FXCollections.observableArrayList(Redictions);
            tab_Reduction.setItems(olp1);
            Reduction_code.setCellValueFactory(new PropertyValueFactory("coder"));
            Reduction_val.setCellValueFactory(new PropertyValueFactory("valr"));
            this.suprimer();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void modifierReduction(ActionEvent event) {
        try {

            Rediction c = new Rediction();

            c.setCoder(saisir_code_reduction.getText());
            c.setValr(Float.parseFloat(saisir_call.getText()));

            rs.updaterduction(c);
            vide();

            List<Rediction> Redictions = rs.getAll();
            ObservableList<Rediction> olp1 = FXCollections.observableArrayList(Redictions);
            tab_Reduction.setItems(olp1);
            Reduction_code.setCellValueFactory(new PropertyValueFactory("coder"));
            Reduction_val.setCellValueFactory(new PropertyValueFactory("valr"));
            this.suprimer();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    public void suprimer() {
        Reduction_suprimer.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (rs.suprimerductiont(tab_Reduction.getItems().get(getIndex()))) {
                                    tab_Reduction.getItems().remove(getIndex());
                                    tab_Reduction.refresh();

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
    private void select_reduction(MouseEvent event) throws IOException {
//                                index =tab_Reduction.getSelectionModel().getSelectedIndex();
//        if (index < 0) {
//    return;
//}

        Rediction e = tab_Reduction.getItems().get(tab_Reduction.getSelectionModel().getSelectedIndex());
        //idLabel.setText(String.valueOf(e.getId_event()));
        saisir_code_reduction.setText(String.valueOf(e.getCoder()));
        saisir_call.setText(String.valueOf(e.getValr()));

//        String filename = rs.GenerateQrRED(e);
//        System.out.println("filename lenaaa " + filename);
//        String path1 = "C:\\xampp\\htdocs\\xchangex\\imgQr\\qrcode" + filename;
//        File file1 = new File(path1);
//        Image img1 = new Image(file1.toURI().toString());
//        //Image image = new Image(getClass().getResourceAsStream("src/utils/img/" + filename));
//        QrCode.setImage(img1);
    }

    @FXML
    private void sentmail(ActionEvent event) throws MessagingException {

        String selectedItem = box_emailuser.getSelectionModel().getSelectedItem();
//    System.out.println(selectedItem);
        String b = saisir_code_reduction.getText();
        envmail.sendMail(selectedItem, b);

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
