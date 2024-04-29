/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.echange;
import entities.reclamation;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import services.echangeservice;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.SqlDateConverter;
import services.echangeservice;
import test.mainxchnagex;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class AfficheechnageController implements Initializable {

    @FXML
    private TextField keyword;
    @FXML
    private TableColumn<echange, Button> delete;
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
   
    
    public AfficheechnageController() {
        cnx = MyDB.getInstance().getCnx();
    }

    @FXML
    private TableView<echange> reclamationsTv;
    @FXML
    private TableColumn<echange, String> DateTv;
    @FXML
    private TableColumn<echange, String> LieuTv;
    @FXML
    private TableColumn<echange, String> TypeVf;
    echangeservice rs = new echangeservice();
    @FXML
    private TableColumn<echange, String> UsernameTv;
    @FXML
    private Button ajouterech;
    Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        try {
            echange t = null;
            // TODO
            TableColumn<echange, Date> Date = new TableColumn<>("Date");
            
            List<echange> Echanges = rs.recuperer(t);
            ObservableList<echange> olr = FXCollections.observableArrayList(Echanges);
            FilteredList<echange> filteredData = new FilteredList<>(olr, b-> true);
            reclamationsTv.setItems(olr);
            UsernameTv.setCellValueFactory(new PropertyValueFactory<echange, String>("username"));
//            Date.setCellValueFactory(new PropertyValueFactory<>("date_echange"));
            DateTv.setCellValueFactory(new PropertyValueFactory<echange, String>("date_echange"));
            LieuTv.setCellValueFactory(new PropertyValueFactory<echange, String>("lieu_echange"));
            TypeVf.setCellValueFactory(new PropertyValueFactory<echange, String>("type_echange"));
            
            keyword.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(productSearchModel -> {
                    if (newValue.isEmpty()){
                        return true;
                    }
                    
                    String searchKeyword = newValue.toLowerCase();
                    if(productSearchModel.getUsername().toLowerCase().indexOf(searchKeyword)> -1){      
                        return true;
                    }else if(productSearchModel.getLieu_echange().toLowerCase().indexOf(searchKeyword)> -1){
                        return true;                        
                    }else if(productSearchModel.getType_echange().toLowerCase().indexOf(searchKeyword)> -1){
                        return true;                        
                    }else if(productSearchModel.getDate_echange().toLowerCase().indexOf(searchKeyword)> -1){
                        return true;                        
                    }else 
                        return false;                        
                    
                });
                });
            SortedList<echange> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(reclamationsTv.comparatorProperty());
            reclamationsTv.setItems(sortedData);
            
            //Edit Table
             reclamationsTv.setEditable(true);
            DateTv.setCellFactory(TextFieldTableCell.forTableColumn());
            LieuTv.setCellFactory(TextFieldTableCell.forTableColumn());
            TypeVf.setCellFactory(TextFieldTableCell.forTableColumn());
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
                                if (rs.supprimer(reclamationsTv.getItems().get(getIndex()))) {
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
     private void EditE(TableColumn.CellEditEvent edittedCell) throws SQLException {
        if(edittedCell.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Please fill in the empty field");
            a.showAndWait();
        }
        else {
        echange es = reclamationsTv.getSelectionModel().getSelectedItem();
        es.setDate_echange(edittedCell.getNewValue().toString());
        es.setType_echange(edittedCell.getNewValue().toString());
        es.setLieu_echange(edittedCell.getNewValue().toString());
        echangeservice srec = new echangeservice();
        srec.modifier(es);
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
    private void go_to_rec(ActionEvent event) {try {
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
    private void go_to_ajoutE(ActionEvent event) {
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

    private void CreatePDF(ActionEvent event) throws SQLException {
        try {
       Document doc = new Document();
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\msi\\Desktop\\projet yocef\\reclamation\\src\\com\\pdf\\Echange.pdf"));
       doc.open();
       
       Image img = Image.getInstance("C:\\Users\\msi\\Desktop\\projet yocef\\reclamation\\src\\com\\img\\Exchange.png12.png");
       img.scaleAbsoluteHeight(60);
       img.scaleAbsoluteWidth(100);
       img.setAlignment(Image.ALIGN_RIGHT);
       doc.add(img);
       
       doc.add(new Paragraph(" "));
       Font font = new Font(FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("Liste des Echanges", font);
       p.setAlignment(Element.ALIGN_CENTER);
       doc.add(p);
       doc.add(new Paragraph(" "));
       doc.add(new Paragraph(" "));
       
       PdfPTable tabpdf = new PdfPTable(4);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
          
       cell = new PdfPCell(new Phrase("Username", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Date", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Lieu", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Type", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       
       Statement st = cnx.createStatement();
       ResultSet rs=st.executeQuery("SELECT username, date_echange, lieu_echange, type_echange FROM echange");
       while(rs.next()){
                    
           cell = new PdfPCell(new Phrase(rs.getString("username"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("date_echange"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("lieu_echange"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("type_echange"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
       }
       
       doc.add(tabpdf);
       JOptionPane.showMessageDialog(null, "Success !!");
       doc.close();
       Desktop.getDesktop().open(new File("C:\\Users\\msi\\Desktop\\projet yocef\\reclamation\\src\\com\\pdf\\Echange.pdf"));
       
       Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your document has been saved as PDF !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }

    @FXML
   private void OnEditChanged(TableColumn.CellEditEvent edittedCell) throws SQLException {
        echange e=reclamationsTv.getSelectionModel().getSelectedItem();
       e.setUsername(edittedCell.getNewValue().toString());
       e.setDate_echange(edittedCell.getNewValue().toString());
       e.setLieu_echange(edittedCell.getNewValue().toString());
       e.setType_echange(edittedCell.getNewValue().toString());
       echangeservice srec = new echangeservice();
      srec.modifier(e);
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
    }

    @FXML
    private void go_to_reclamation(ActionEvent event) {
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
    

    
    
    