/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import entities.livraison;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import services.livraisonService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import test.mainxchnagex;

/**
 * FXML Controller class
 *
 * @author octanet
 */
public class LivraisonnController implements Initializable {

    @FXML
    private DatePicker dateLivraisonField;
    @FXML
    private TextField typeLivraisonField;
    @FXML
    private TextField adresseLivraisonField;
    @FXML
    private TableView<livraison> tableLivraison;
    @FXML
    private TableColumn<livraison, String> DateColumn;
    @FXML
    private TableColumn<livraison, String> TypeColumn;
    @FXML
    private TableColumn<livraison, String> AdressColumn;
    @FXML
    private Button ButtonAjouter;
    @FXML
    private Button ButtonSupprimer;
    @FXML
    private Button ButtonModifier;
    livraison l = new livraison();
    livraisonService Ls = new livraisonService();
    @FXML
    private Label partError;

    ObservableList<livraison> data = FXCollections.observableArrayList();

    @FXML
    private PasswordField idField;
    @FXML
    private Button ButtonPDF;
    @FXML
    private Button afficher;
    @FXML
    private TextField chercher;
    @FXML
    private Button TriType;
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

        getEvents();
    }

    private boolean NoDate() {

        LocalDate currentDate = LocalDate.now();

        LocalDate myDate = dateLivraisonField.getValue();
        int comparisonResult = myDate.compareTo(currentDate);

        boolean test = true;

        if (comparisonResult < 0) {
            // myDate est antérieure à currentDate
            test = true;
        } else if (comparisonResult > 0) {
            // myDate est postérieure à currentDate
            test = false;
        }
        return test;
    }

    @FXML
    private void ajouterLivraison(ActionEvent event) {
        //int part=0;

        if ((dateLivraisonField.getValue() == null) || (typeLivraisonField.getText().length() == 0) || (adresseLivraisonField.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        } else if (NoDate() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("la date date doit être aprés la date d'aujourd'hui");
            alert.showAndWait();
        } else {

            livraison l = new livraison();

            java.util.Date date_debut = java.util.Date.from(dateLivraisonField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlDate = new Date(date_debut.getTime());
            l.setDate_liv(sqlDate);

            l.setType_liv(typeLivraisonField.getText());
            l.setAdress_liv(adresseLivraisonField.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("livraison add");
            alert.setContentText("livraison added successfully!");
            alert.showAndWait();
 Notifications.create()
              .title("livraison ajouter avec succées")
              .text("Veuillez vérifier votre liste des livraisons ")
              .showWarning();
            try {
                Ls.ajouter(l);
                reset();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            getEvents();
        }
    }

    @FXML
    private void supprimerLivraison(ActionEvent event) {

        livraison e = tableLivraison.getItems().get(tableLivraison.getSelectionModel().getSelectedIndex());

        try {
            Ls.supprimer(e);
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonnController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();
        getEvents();

    }

    @FXML
    private void ModifierLivraison(ActionEvent event) throws SQLException {
        livraison liv = new livraison();

        Date d = Date.valueOf(dateLivraisonField.getValue());
        liv.setDate_liv(d);
        liv.setType_liv(typeLivraisonField.getText());
        liv.setAdress_liv(adresseLivraisonField.getText());
        liv.setId(Integer.valueOf(idField.getText()));

        Ls.modifier(liv);
        System.out.println("livraison modifier avec succes");
        getEvents();
        reset();

    }

    private void reset() {
        dateLivraisonField.setValue(null);
        typeLivraisonField.setText("");
        adresseLivraisonField.setText("");

    }

    public void getEvents() {

        try {
            // TODO
            List<livraison> livraisons = Ls.recuperer();
            ObservableList<livraison> olp = FXCollections.observableArrayList(livraisons);
            tableLivraison.setItems(olp);
            DateColumn.setCellValueFactory(new PropertyValueFactory("date_liv"));
            TypeColumn.setCellValueFactory(new PropertyValueFactory("type_liv"));
            AdressColumn.setCellValueFactory(new PropertyValueFactory("adress_liv"));

            // this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }//get events

    @FXML
    private void choisirLivraison(MouseEvent event) {

        livraison e = tableLivraison.getItems().get(tableLivraison.getSelectionModel().getSelectedIndex());

        idField.setText(String.valueOf(e.getId()));
        typeLivraisonField.setText(e.getType_liv());
        adresseLivraisonField.setText(e.getAdress_liv());
        //dateLivraisonField.setText(e.getDate_liv());
    }

    @FXML
private void PDFLivraison(ActionEvent event) throws DocumentException, FileNotFoundException, IOException {
    long millis = System.currentTimeMillis();
    java.sql.Date DateRapport = new java.sql.Date(millis);

    String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);

    com.itextpdf.text.Document document = new com.itextpdf.text.Document();

    try {
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));
        document.open();

        // Add a background image
        com.itextpdf.text.Image backgroundImage = com.itextpdf.text.Image.getInstance("C:/xampp/htdocs/imageP/image.jpg");
        backgroundImage.scaleAbsolute(document.getPageSize());
        backgroundImage.setAbsolutePosition(0, 0);
        PdfContentByte background = writer.getDirectContentUnder();
        background.addImage(backgroundImage);

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 50, BaseColor.ORANGE);
        Paragraph title = new Paragraph("Certificat de data science", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add empty paragraph to control spacing after title
        Paragraph spacingParagraph = new Paragraph();
        spacingParagraph.setSpacingAfter(50); // Adjust the value as needed
        document.add(spacingParagraph);

        // Adjust Y-coordinate and initial horizontal indentation
        float verticalPosition = document.getPageSize().getHeight() - 600; // Initial Y-coordinate
        float horizontalIndentation = 100; // Initial horizontal indentation
        Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 30);

        // Adjust font size and line spacing
        int fontSize = 22; // Adjust the font size as needed
        float lineSpacing = 40; // Adjust the line spacing as needed

        // Iterate through livraisons and add details as paragraphs
        for (livraison r : Ls.recuperer()) {
            Paragraph eventDetails = new Paragraph();

            // Set font and line spacing
            contentFont.setSize(fontSize);
            eventDetails.setFont(contentFont);
            eventDetails.setLeading(lineSpacing);

            eventDetails.add(new Phrase("Ce certificat approuve les compétences de " + r.getAdress_liv() + " et ses compétences"));
            eventDetails.add(Chunk.NEWLINE);
            eventDetails.add(new Phrase("Dans le " + r.getType_liv()));
            eventDetails.add(Chunk.NEWLINE);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            String formattedDate = dateFormat.format(r.getDate_liv());

            // Add the formatted Date to the Phrase
            eventDetails.add(new Phrase("Ce certificat est valide jusqu'au " + formattedDate));
            eventDetails.add(Chunk.NEWLINE);
            eventDetails.add(Chunk.NEWLINE); // Add extra space between entries
            eventDetails.setSpacingBefore(verticalPosition); // Set spacing before paragraph
            eventDetails.setIndentationLeft(horizontalIndentation);
            float spacing = 20; // Adjust the value as needed
            eventDetails.setSpacingBefore(spacing); // Set horizontal indentation
            document.add(eventDetails);

            verticalPosition -= lineSpacing; // Adjust for line spacing
            horizontalIndentation += 50; // Adjust horizontal indentation for the next paragraph
        }

    } catch (DocumentException | FileNotFoundException | SQLException e) {
        System.out.println(e);
    }
    document.close();

    File file = new File(DateLyoum + ".pdf");
    Desktop desktop = Desktop.getDesktop();
    if (file.exists()) {
        desktop.open(file);
    }
}







    @FXML
    private void afficher_ivreson(ActionEvent event) {
                        try {
          Parent root = FXMLLoader.load(getClass().getResource("afficherTousLivraisonn.fxml"));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
  
    private void Recherche(KeyEvent event) throws SQLException {
        
         livraison p=new livraison();
livraisonService sp = new livraisonService();
       tableLivraison.setItems(sp.searchByLivraison(chercher.getText()))  ;
    }

    @FXML
    private void TriType(ActionEvent event) throws SQLException {
     
   DateColumn.setCellValueFactory(new PropertyValueFactory<livraison,String>("date_liv "));
      TypeColumn.setCellValueFactory(new PropertyValueFactory<livraison,String>("type_liv"));
AdressColumn.setCellValueFactory(new PropertyValueFactory<livraison, String>("adress_liv"));

         livraison p=new livraison();
livraisonService sp = new livraisonService();

  data=sp.getAllTriType();
       tableLivraison.setItems(data)  ;
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

   
   
    


