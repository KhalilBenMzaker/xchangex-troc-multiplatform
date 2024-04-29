/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.Redictionservice;

/**
 * FXML Controller class
 *
 * @author sbekr
 */
public class ReductionController implements Initializable {

    @FXML
    private TextField cod;
    @FXML
    private Text newprix;
    @FXML
    private Text description;
    @FXML
    private Text nom_rection;
    @FXML
    private Button bt_reduction;
    Redictionservice rs= new Redictionservice();
    @FXML
    private Text proddd_chyyy;
    @FXML
    private ImageView imageprod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Produit e = new Produit();
//                        nom_rection.setText(e.getNom_produit());
//        
//        description.setText(e.getDescription_produit());
//        newprix.setText(String.valueOf(e.getPrix_produit()));
        // TODO
    }   
        public void  nomdata(String data )
    {
    nom_rection.setText(data);
    }
        public void  descriptiondata(String data )
    {
    description.setText(data);
    }
            public void  receiveData(float data )
    {
    newprix.setText(Float.toString(data));
    }
            public void image(String path)
            {
                  
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageprod.setImage(img);
            }

    @FXML
    private void verivecered(ActionEvent event) {
        float a =Float.parseFloat(newprix.getText());
         
        try {
        boolean exists =rs.checkCodeExists(cod.getText());
        float f=rs.getrductiontcode(cod.getText())/100;
        float prix =Float.parseFloat(newprix.getText())-a*f;
                if(exists)
        {
            newprix.setText(Float.toString(prix));
            rs.suprimerbycode(cod.getText());
        
        }else {proddd_chyyy.setText("code invalide");}
        
           } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }


    }
    

//                  public void prodredcu(Produit e) {
//        
//        nom_rection.setText(e.getNom_produit());
//        
//        description.setText(e.getDescription_produit());
//        newprix.setText(String.valueOf(e.getPrix_produit()));
//         
//        // TODO
//    }
}
