/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Produit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CategorieService;
import services.ProduitService;
import test.mainxchnagex;

/**
 * FXML Controller class
 *
 * @author sbekr
 */
public class GereproduitController implements Initializable {

    @FXML
    private Button ajouter_produit;
    @FXML
    private TextField sai_nom;
    @FXML
    private TextField sai_image;
    @FXML
    private TextField sai_description;
    @FXML
    private TextField sai_prix;
    @FXML
    private ComboBox<String> sai_categorie;
    @FXML
    private Text valid_ajou_nom;
    @FXML
    private Text valid_ajou_image;
    @FXML
    private Text valid_ajou_descriptin;
    @FXML
    private Text valid_ajou_prix;
    @FXML
    private Text valid_ajou_categorie;
    @FXML
    private Button modifier_poduit;
    @FXML
    private TextField modif_nom;
    @FXML
    private Text valid_modif_nom;
    @FXML
    private TextField modif_image;
    @FXML
    private Text valid_modif_image;
    @FXML
    private TextField modif_description;
    @FXML
    private Text valid_modif_description;
    @FXML
    private TextField modif_prix;
    @FXML
    private Text valid_modif_prix;
    @FXML
    private ComboBox<String> modif_categorie;
    @FXML
    private Text valid_modif_categorie;
    @FXML
    private TableView<Produit> tab_produit;
    @FXML
    private TableColumn<Produit, Integer> tab_produit_id;
    @FXML
    private TableColumn<Produit, String> tab_produit_image;
    @FXML
    private TableColumn<Produit, String> tab_produit_description;
    @FXML
    private TableColumn<Produit, Float> tab_produit_prix;
    @FXML
    private TableColumn<Produit, String> tab_produit_categorie;
    @FXML
    private TableColumn<Produit, Button> tab_produit_suprimer;
ProduitService ps =new ProduitService();
CategorieService cs = new CategorieService();
    @FXML
    private TableColumn<Produit,String> tab_produit_nom;
    int index = -1;
    @FXML
    private ImageView ImageViw;
    @FXML
    private ImageView imade_modif;
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
                                   List<String> categories=cs.getnamecat();
                       ObservableList<String> olc = FXCollections.observableArrayList(categories);
                       sai_categorie.setItems(olc);
                       modif_categorie.setItems(olc);
            // TODO
            List<Produit> produits = ps.getAll();
            ObservableList<Produit> olp = FXCollections.observableArrayList(produits);
            tab_produit.setItems(olp);
            tab_produit_id.setCellValueFactory(new PropertyValueFactory("id_produit"));
            tab_produit_nom.setCellValueFactory(new PropertyValueFactory("nom_produit"));
            tab_produit_image.setCellValueFactory(new PropertyValueFactory("image_produit"));
            tab_produit_description.setCellValueFactory(new PropertyValueFactory("description_produit"));
            tab_produit_prix.setCellValueFactory(new PropertyValueFactory("prix_produit"));
            tab_produit_categorie.setCellValueFactory(new PropertyValueFactory("nom_categorie"));
            this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        // TODO
    }    

    @FXML
    private void ajouterprod(ActionEvent event) {

                try {
                       List<String> categories=cs.getnamecat();
                       
                       List<Integer> cate = cs.getIdCat();
                       ObservableList<String> olc = FXCollections.observableArrayList(categories);
                       sai_categorie.setItems(olc);
                       String a=sai_categorie.getValue();
                       if(sai_nom.getText().length()==0){valid_ajou_nom.setText("invalide nom");}else{
                           
                           if(sai_image.getText().length()==0){valid_ajou_image.setText("invalide image");}else{
                               
                               if(sai_description.getText().length()==0){valid_ajou_descriptin.setText("invalide descriptin");}else{
                                   
                                   if(sai_prix.getText().length()==0){valid_ajou_prix.setText("invalide prix");}else{
                                       
      
                   Categorie c =new Categorie(cs.getCategorieIdByName(a));
                   
            Produit p = new Produit();
            p.setNom_produit(sai_nom.getText());
            p.setImage_produit(sai_image.getText());
            p.setDescription_produit(sai_description.getText());
            p.setPrix_produit(Float.parseFloat(sai_prix.getText()));
            p.setCategorie(c);
            
             ps.ajouterproduit(p);
            vide();}}}}
           
            
                        List<Produit> produits = ps.getAll();
            ObservableList<Produit> olp = FXCollections.observableArrayList(produits);
            tab_produit.setItems(olp);
            tab_produit_id.setCellValueFactory(new PropertyValueFactory("id_produit"));
            tab_produit_nom.setCellValueFactory(new PropertyValueFactory("nom_produit"));
            tab_produit_image.setCellValueFactory(new PropertyValueFactory("image_produit"));
            tab_produit_description.setCellValueFactory(new PropertyValueFactory("description_produit"));
            tab_produit_prix.setCellValueFactory(new PropertyValueFactory("prix_produit"));
            tab_produit_categorie.setCellValueFactory(new PropertyValueFactory("nom_categorie"));
            this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void modifprod(ActionEvent event) {
        
                try {
                       List<String> categories=cs.getnamecat();
                       ObservableList<String> olc = FXCollections.observableArrayList(categories);
                       modif_categorie.setItems(olc);
                       String a= modif_categorie.getValue();
                                              if(modif_nom.getText().length()==0){valid_modif_nom.setText("invalide nom");}else{
                           
                           if(modif_image.getText().length()==0){valid_modif_image.setText("invalide image");}else{
                               
                               if(modif_description.getText().length()==0){valid_modif_description.setText("invalide descriptin");}else{
                                   
                                   if(modif_prix.getText().length()==0){valid_modif_prix.setText("invalide prix");}else{
                   Categorie c =new Categorie(cs.getCategorieIdByName(a));
            Produit p = new Produit();
            p.setId_produit(tab_produit_id.getCellData(index));
            p.setNom_produit(modif_nom.getText());
            p.setImage_produit(modif_image.getText());
            p.setDescription_produit(modif_description.getText());
            p.setPrix_produit(Float.parseFloat(modif_prix.getText()));
            p.setCategorie(c);
            ps.updateproduit(p);
            vide();}}}}
            
                        List<Produit> produits = ps.getAll();
            ObservableList<Produit> olp = FXCollections.observableArrayList(produits);
            tab_produit.setItems(olp);
            tab_produit_id.setCellValueFactory(new PropertyValueFactory("id_produit"));
            tab_produit_nom.setCellValueFactory(new PropertyValueFactory("nom_produit"));
            tab_produit_image.setCellValueFactory(new PropertyValueFactory("image_produit"));
            tab_produit_description.setCellValueFactory(new PropertyValueFactory("description_produit"));
            tab_produit_prix.setCellValueFactory(new PropertyValueFactory("prix_produit"));
            tab_produit_categorie.setCellValueFactory(new PropertyValueFactory("nom_categorie"));
            this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }
public void vide (){
            sai_nom.setText("");
           sai_image.setText("");
           sai_description.setText("");
           sai_prix.setText("");
                       modif_nom.setText("");
            modif_image.setText("");
           modif_description.setText("");
            modif_prix.setText("");
                                                   valid_ajou_prix.setText("");
                               valid_ajou_nom.setText("");
                       valid_ajou_image.setText("");
                       valid_ajou_descriptin.setText("");
                              valid_modif_prix.setText("");
                               valid_modif_nom.setText("");
                       valid_modif_image.setText("");
                       valid_modif_description.setText("");
}



    @FXML
    private void select_items(MouseEvent event) {
       
                index =tab_produit.getSelectionModel().getSelectedIndex();
        if (index < 0) {
    return;
}
modif_nom.setText(tab_produit_nom.getCellData(index).toString());
modif_image.setText(tab_produit_image.getCellData(index).toString());
modif_description.setText(tab_produit_description.getCellData(index).toString());
modif_prix.setText(tab_produit_prix.getCellData(index).toString());
String path = modif_image.getText();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imade_modif.setImage(img);


    }
    public void delete() {
        tab_produit_suprimer.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (ps.suprimeproduit(tab_produit.getItems().get(getIndex()))) {
                                    tab_produit.getItems().remove(getIndex());
                                    tab_produit.refresh();

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
    private void UplodImage(ActionEvent event) throws FileNotFoundException, IOException {
        
  
           Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
     String DBPath = "C:\\\\Users\\\\sbekr\\\\OneDrive\\\\Desktop\\\\projetSymfony\\\\piSymfony\\\\public\\\\images\\\\"+ x +".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            ImageViw.setImage(img);
            /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
             sai_image.setText(""+ x +".jpg");
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
            
        } else {
            System.out.println("error");

        }
        
        
    }

    @FXML
    private void afficher_tous_les_prod(ActionEvent event) {
                        try {
          Parent root = FXMLLoader.load(getClass().getResource("affichertousproduit.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void modifier_image(ActionEvent event) throws FileNotFoundException, IOException{
                   Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\Users\\\\sbekr\\\\OneDrive\\\\Desktop\\\\projetSymfony\\\\piSymfony\\\\public\\\\images\\\\"+ x +".jpg";
        
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            imade_modif.setImage(img);
            /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
             modif_image.setText(""+ x +".jpg");
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
            
        } else {
            System.out.println("error");

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
    
    

