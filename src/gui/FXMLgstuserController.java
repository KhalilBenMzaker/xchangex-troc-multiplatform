/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Role;
import entities.User;
import test.mainxchnagex;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLgstuserController implements Initializable {

    @FXML
    private TableView<User> tvuser;
    @FXML
    private TableColumn<User, Integer> cid;
    @FXML
    private TableColumn<User, String> cnom;
    @FXML
    private TableColumn<User, String> cprenom;
    @FXML
    private TableColumn<User, String> cemail;
    @FXML
    private TableColumn<User, String> cusername;
    @FXML
    private TableColumn<User, String> cmdp;
    @FXML
    private TableColumn<User, String> cadresse;
    @FXML
    private TableColumn<User, Integer> ctel;
    @FXML
    private TableColumn<User, Date> cdate;
    @FXML
    private TableColumn<User, Role> crole;
    @FXML
    private TableColumn<User, Integer> crating;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField pfmdp;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfnumtel;
    @FXML
    private TextField tfrating;
    @FXML
    private ComboBox<Role> cbrole;
    @FXML
    private DatePicker dpdate;
    ServiceUser su=new ServiceUser();
    ObservableList<User> data=FXCollections.observableArrayList();
    @FXML
    private TextField tfrecherche;
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
        cbrole.getItems().setAll(Role.values());
        refreshList();
        recherche_avance();
        // TODO
    }    
    public void refreshList(){
        data.clear();
        data=FXCollections.observableArrayList(su.afficher());
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cdate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        cusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        cmdp.setCellValueFactory(new PropertyValueFactory<>("password"));
        crole.setCellValueFactory(new PropertyValueFactory<>("role"));
        ctel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        crating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tvuser.setItems(data);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String erreur=controleDeSaisie();
        if(erreur.length()>0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("invalide");
            alert.setContentText(erreur);
            alert.showAndWait();
        }
        else{
            User u=new User();
            u.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setUsername(tfusername.getText());
            u.setPassword(pfmdp.getText());
            u.setAdresse(tfadresse.getText());
            u.setNumTel(Integer.valueOf(tfnumtel.getText()));
            u.setRole(cbrole.getValue());
            u.setDate_naissance(Date.valueOf(dpdate.getValue()));
            u.setRating(Integer.valueOf(tfrating.getText()));
            su.ajouter(u);
            refreshList();
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        if(tvuser.getSelectionModel().getSelectedItem()!=null){
            User u=new User();
            int id=tvuser.getSelectionModel().getSelectedItem().getId();
            u.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setUsername(tfusername.getText());
            u.setPassword(pfmdp.getText());
            u.setAdresse(tfadresse.getText());
            u.setNumTel(Integer.valueOf(tfnumtel.getText()));
            u.setRole(cbrole.getValue());
            u.setDate_naissance(Date.valueOf(dpdate.getValue()));
            u.setRating(Integer.valueOf(tfrating.getText()));
            su.modifier(id,u);
            refreshList();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if(tvuser.getSelectionModel().getSelectedItem()!=null){
            int id=tvuser.getSelectionModel().getSelectedItem().getId();
            su.supprimer(id);
            refreshList();
        }
        
    }

    @FXML
    private void fillforum(MouseEvent event) {
        User u=tvuser.getSelectionModel().getSelectedItem();
        tfnom.setText(u.getNom());
        tfprenom.setText(u.getPrenom());
        tfadresse.setText(u.getAdresse());
        tfemail.setText(u.getEmail());
        tfusername.setText(u.getUsername());
        pfmdp.setText(u.getPassword());
        tfnumtel.setText(String.valueOf(u.getNumTel()));
        cbrole.setValue(u.getRole());
        tfrating.setText(String.valueOf(u.getRating()));
        
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
  

  //convert String to LocalDate
  LocalDate localDate = LocalDate.parse(String.valueOf(u.getDate_naissance()), formatter);
        dpdate.setValue(localDate);
        
    }
    public String controleDeSaisie(){
        Pattern pattern = Pattern.compile("^[A-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[A-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n" +
				")*@[A-Z0-9-]+(?:\\.[A-Z0-9-]+)*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(tfemail.getText());
        String erreur="";
        if(tfnom.getText().trim().isEmpty()){
            erreur+="-nom vide\n";
        }
        if(tfprenom.getText().trim().isEmpty()){
            erreur+="-prenom vide\n";
        }
        if(tfemail.getText().trim().isEmpty()){
            erreur+="-email vide\n";
        }
        if(tfusername.getText().trim().isEmpty()){
            erreur+="-username vide\n";
        }
        if(tfadresse.getText().trim().isEmpty()){
            erreur+="-adresse vide\n";
        }
        if(tfnumtel.getText().trim().isEmpty()){
            erreur+="-numtel vide\n";
        }
        if(pfmdp.getText().trim().isEmpty()){
            erreur+="-mdp vide\n";
        }
        if(dpdate.getValue()==null){
            erreur+="-date vide\n";
        }
        if(cbrole.getValue()==null){
            erreur+="-role vide\n";
        }
        if(!matcher.find()){
            erreur+="-email incorrect\n";
        }
        if(pfmdp.getText().length()<4){
            erreur+="-mot de passe doit etre sup a 4 char\n";
        }
        return erreur;
    }
    public void recherche_avance(){
        //remplire lobservablelist
        data.clear();
        data.addAll(su.afficher());
        //liste filtrer
        FilteredList<User> filtreddata=new FilteredList<>(data,u->true);
        //creation du listenere a partire du textfield
        tfrecherche.textProperty().addListener((observable,oldValue,newValue)->{
            filtreddata.setPredicate(user->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                if(user.getAdresse().indexOf(newValue)!=-1){
                    return true;
                }
                else if(user.getEmail().indexOf(newValue)!=-1){
                    return true;
                }
                else if(user.getNom().indexOf(newValue)!=-1){
                    return true;
                }
                else if(user.getPrenom().indexOf(newValue)!=-1){
                    return true;
                }
                else if(user.getUsername().indexOf(newValue)!=-1){
                    return true;
                }
                else if(String.valueOf(user.getDate_naissance()).indexOf(newValue)!=-1){
                    return true;
                }
                else if(String.valueOf(user.getNumTel()).indexOf(newValue)!=-1){
                    return true;
                }
                else if(String.valueOf(user.getRole()).indexOf(newValue)!=-1){
                    return true;
                }
                
                else{
                    return false;
                }
            });
            tvuser.setItems(filtreddata);
        });
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
    
}
