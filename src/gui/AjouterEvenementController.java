/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.UserDetails;
import entities.evenement;
import entities.participation;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import services.ParticipationService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.EvenementService;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import test.mainxchnagex;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private TextField descriptionEventField;
    @FXML
    private DatePicker dateEventField;
    @FXML
    private TextField typeEventField;
    @FXML
    private TextField imageEventField;
    @FXML
    private TextField nomEventField;
    @FXML
    private Button supprimerBoutton;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button afficherBoutton;
  
    @FXML
    private TableView<evenement> evenementTv;
    @FXML
    private TableColumn<evenement, String> nomEventTv;
    @FXML
    private TableColumn<evenement, String> typeEventTv;
    @FXML
    private TableColumn<evenement, String> imageEventTv;
    @FXML
    private TableColumn<evenement, String> dateEventTv;
    @FXML
    private TableColumn<evenement, String> descriptionEventTv;
     @FXML
    private TableColumn<evenement, Integer> nbrpartTv;
    @FXML
    private TextField nbparticipantsField;
    
    private Date date1;
    @FXML
    private Label partError;
    @FXML
    private Label idLabel;
 
    ObservableList<evenement> events;
    EvenementService Ev=new EvenementService();
    ParticipationService Pservice =new ParticipationService();
    
    @FXML
    private TextField idmodifierField;
    @FXML
    private Button participerbutton;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField rechercher;
    @FXML
    private ImageView QrCode;
    @FXML
    private Button musicButton;
    @FXML
    private Button pauseMusicButton;
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
        // TODO          
        partError.setVisible(false);
        //idLabel.setText("");
        getEvents(); 
        }
  
    
        
        String path = "C:\\xampp\\htdocs\\music\\Alok & Alan Walker.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
     private boolean NoDate() {
         LocalDate currentDate = LocalDate.now();     
         LocalDate myDate = dateEventField.getValue(); 
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
    private void ajouterEvenement(ActionEvent event) {
   
         int part=0;
        if ((nomEventField.getText().length() == 0) || (typeEventField.getText().length() == 0) || (imageEventField.getText().length() == 0) || (nbparticipantsField.getText().length() == 0)|| (descriptionEventField.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }
       else if (NoDate() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("la date date doit être aprés la date d'aujourd'hui");
            alert.showAndWait();
        }
       else{     
            try {
                part = Integer.parseInt(nbparticipantsField.getText());
                partError.setVisible(false);
            } catch (Exception exc) {
                System.out.println("Number of participants int");
                partError.setVisible(true);
                return;
            }
            if(part<=0)
            {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("le nombre de participants doit être supp à 0");
            alert.showAndWait();
            partError.setVisible(true);}
            else
            {
        evenement e = new evenement();
        e.setNom_event(nomEventField.getText());
        e.setType_event(typeEventField.getText());
        e.setDescription_event(descriptionEventField.getText());
        java.util.Date date_debut=java.util.Date.from(dateEventField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqlDate = new Date(date_debut.getTime());
        e.setDate(sqlDate);
        e.setNbrparticipants(Integer.valueOf(nbparticipantsField.getText()));
        //lel image
        e.setImage_event(imageEventField.getText());      
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event add");
            alert.setContentText("Event added successfully!");
            alert.showAndWait();      
        try {
            Ev.ajouterEvenement(e);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      
        getEvents();
    }}}
    
    //fin d ajout d'un evenement
    private void reset() {
        nomEventField.setText("");
        typeEventField.setText("");
        descriptionEventField.setText("");
        imageEventField.setText("");
        nbparticipantsField.setText("");
        dateEventField.setValue(null);    
    }
    
   public void getEvents() {  
         try {
            // TODO
            List<evenement> evenements = Ev.recupererEvenement();
            ObservableList<evenement> olp = FXCollections.observableArrayList(evenements);
            evenementTv.setItems(olp);
            nomEventTv.setCellValueFactory(new PropertyValueFactory("nom_event"));
            typeEventTv.setCellValueFactory(new PropertyValueFactory("type_event"));
            imageEventTv.setCellValueFactory(new PropertyValueFactory("image_event"));
            dateEventTv.setCellValueFactory(new PropertyValueFactory("date"));
            descriptionEventTv.setCellValueFactory(new PropertyValueFactory("description_event"));
            nbrpartTv.setCellValueFactory(new PropertyValueFactory("nb_participants"));
           // this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }//get events

    @FXML
    private void supprimerEvenement(ActionEvent event) {
           evenement e = evenementTv.getItems().get(evenementTv.getSelectionModel().getSelectedIndex());
        try {
            Ev.supprimerEvenement(e);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();        
        getEvents();    
    }

    @FXML
    private void afficherEvenement(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));
            typeEventField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @FXML
   private void modifierEvenement(ActionEvent event) throws SQLException {
        evenement e = new evenement();
        e.setId_event(Integer.valueOf(idmodifierField.getText()));
        e.setNom_event(nomEventField.getText());
        e.setType_event(typeEventField.getText());
        e.setDescription_event(descriptionEventField.getText()); 
        Date d=Date.valueOf(dateEventField.getValue());
        e.setDate(d);
        e.setImage_event(imageEventField.getText());
        e.setNbrparticipants(Integer.valueOf(nbparticipantsField.getText()));         
        Ev.modifierEvenement(e);
        reset();
        getEvents();         
    }
    @FXML
    //ta3 tablee bch nenzel 3ala wehed ya5tarou w yet3abew textfield
    private void choisirEvent(MouseEvent event) throws IOException {
        evenement e = evenementTv.getItems().get(evenementTv.getSelectionModel().getSelectedIndex());     
        //idLabel.setText(String.valueOf(e.getId_event()));
        idmodifierField.setText(String.valueOf(e.getId_event()));
        nomEventField.setText(e.getNom_event());
        typeEventField.setText(e.getType_event());
        imageEventField.setText(e.getImage_event());
        descriptionEventField.setText(e.getDescription_event());
        //dateEventField.setValue((e.getDate()));
        nbparticipantsField.setText(String.valueOf(e.getNbrparticipants()));       
        //lel image
        String path = e.getImage_event();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
        //////////////      
 String filename = Ev.GenerateQrEvent(e);
            System.out.println("filename lenaaa " + filename);
            String path1="C:\\xampp\\htdocs\\xchangex\\imgQr\\qrcode"+filename;
             File file1=new File(path1);
              Image img1 = new Image(file1.toURI().toString());
              //Image image = new Image(getClass().getResourceAsStream("src/utils/img/" + filename));
            QrCode.setImage(img1);  
    }

    private void participer(ActionEvent event) {

        UserDetails u=new UserDetails();
        LocalDate dateActuelle = LocalDate.now();
        Date dateSQL = Date.valueOf(dateActuelle);
        participation p=new participation();
        p.setDate_part(dateSQL);
        //p.setEvenement();
        p.setId_evenement(Integer.parseInt(idmodifierField.getText()));
        p.setId_user(u.getId());
        Pservice.ajouterParticipation(p);
    }

    @FXML
    private void afficherParticipations(ActionEvent event) {     
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficherParticipation.fxml"));
            typeEventField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @FXML
    private void uploadImage(ActionEvent event)throws FileNotFoundException, IOException  {

        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\imageP\\\\"  + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            imageview.setImage(img);    
            imageEventField.setText(DBPath);
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
    private void excelEvent(ActionEvent event) {
           
try{
String filename="C:\\xampp\\htdocs\\fichierExcelJava\\dataEvent.xls" ;
    HSSFWorkbook hwb=new HSSFWorkbook();
    HSSFSheet sheet =  hwb.createSheet("new sheet");
    HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("nom evenement");
rowhead.createCell((short) 1).setCellValue("type d'evenement");
rowhead.createCell((short) 2).setCellValue("description ");
List<evenement> evenements = Ev.recupererEvenement();
  for (int i = 0; i < evenements.size(); i++) {          
HSSFRow row=   sheet.createRow((short)i);
row.createCell((short) 0).setCellValue(evenements.get(i).getNom_event());
row.createCell((short) 1).setCellValue(evenements.get(i).getType_event());
row.createCell((short) 2).setCellValue(evenements.get(i).getDescription_event());
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
    private void pdfEvent(ActionEvent event) throws FileNotFoundException, SQLException, IOException {        
       // evenement tab_Recselected = evenementTv.getSelectionModel().getSelectedItem();
               long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("Date d'aujourdhui : " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Voici un rapport détaillé de notre application qui contient tous les événements . Pour chaque événement, nous fournissons des informations telles que la date d'aujourdhui :" + DateRapport );
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(3);
            //On créer l'objet cellule.
            PdfPCell cell;
            //contenu du tableau.
            table.addCell("nom_event");
            table.addCell("type_event");
            table.addCell("description_event");
            evenement r = new evenement();
            Ev.recupererEvenement().forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getNom_event()));
                table.addCell(String.valueOf(e.getType_event()));
                table.addCell(String.valueOf(e.getDescription_event()));    
            }
            );
            
//            Image img = Image.getInstance("C:\\Users\\msi\\Desktop\\projet yocef\\reclamation\\src\\com\\img\\Exchange.png12.png");
//       img.scaleAbsoluteHeight(60);
//       img.scaleAbsoluteWidth(100);
//       img.setAlignment(Image.ALIGN_RIGHT);
//       document.add(img);
            document.add(ph1);
            document.add(ph2);
            document.add(table);
             } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File(DateLyoum + ".pdf");
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) //checks file exists or not  
        {
            desktop.open(file); //opens the specified file   
        }
    } 
    @FXML
    private void rechercherEvent(KeyEvent event) {
        
        EvenementService bs=new EvenementService(); 
        evenement b= new evenement();
        ObservableList<evenement>filter= bs.chercherEvent(rechercher.getText());
        populateTable(filter);
    }
     private void populateTable(ObservableList<evenement> branlist){
       evenementTv.setItems(branlist);
   
       }

    @FXML
    private void playMusic(ActionEvent event) {
        
        mediaPlayer.play();
       // Image img = new Image("C:\\xampp\\htdocs\\music\\ala.jpg");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Musique Jouée");
//         Notifications notificationBuilder = Notifications.create()
//                .title("Musique")
//                .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void pauseMusic(ActionEvent event) {
        
               mediaPlayer.pause();
        //Image img = new Image("fllogo.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Musique Arrêtée");
        notificationBuilder.darkStyle();
        notificationBuilder.show();
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
    private void go_to_event(ActionEvent event) {
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

 


    





    

