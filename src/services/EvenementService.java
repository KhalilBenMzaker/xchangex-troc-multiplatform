/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import com.sun.javafx.iio.ImageStorage.ImageType;
import entities.evenement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.MyDB;
import javafx.collections.ObservableList;

//**************//
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author asus
 */
public class EvenementService implements IEvenementService<evenement> {

    Connection cnx;
    public Statement ste;
    public PreparedStatement pst;

    public EvenementService() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void ajouterEvenement(evenement e) throws SQLException {

        String requete = "INSERT INTO `evenement` (`nom_event`,`type_event`,`image_event`,`description_event`,`date`,`nb_participants`) "
                + "VALUES (?,?,?,?,?,?);";
        try {
            pst = (PreparedStatement) cnx.prepareStatement(requete);
            pst.setString(1, e.getNom_event());
            pst.setString(2, e.getType_event());
            pst.setString(3, e.getImage_event());
            pst.setString(4, e.getDescription_event());
            pst.setDate(5, e.getDate());
            pst.setInt(6, e.getNbrparticipants());
            pst.executeUpdate();
            System.out.println("event " + e.getNom_event() + " added successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifierEvenement(evenement e) throws SQLException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "UPDATE evenement SET nom_event = ?,type_event = ?,image_event=?,description_event = ?,date=?,nb_participants=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, e.getNom_event());
        ps.setString(2, e.getType_event());
        ps.setString(3, e.getImage_event());
        ps.setString(4, e.getDescription_event());
        ps.setDate(5, e.getDate());
        ps.setInt(6, e.getNbrparticipants());
        ps.setInt(7, e.getId_event());
        ps.executeUpdate();
    }

    @Override
    public void supprimerEvenement(evenement e) throws SQLException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "delete from evenement where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, e.getId_event());
        ps.executeUpdate();
        System.out.println("event with id= " + e.getId_event() + "  is deleted successfully");
    }

    @Override
    public List<evenement> recupererEvenement() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        List<evenement> evenements = new ArrayList<>();
        String s = "select * from evenement";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            evenement e = new evenement();
            e.setNom_event(rs.getString("nom_event"));
            e.setType_event(rs.getString("Type_event"));
            e.setImage_event(rs.getString("Image_event"));
            e.setDescription_event(rs.getString("description_event"));
            e.setDate(rs.getDate("date"));
            e.setNbrparticipants(rs.getInt("nb_participants"));
            e.setId_event(rs.getInt("id"));

            evenements.add(e);

        }
        return evenements;
    }

    public evenement FetchOneEvent(int id) {
        evenement event = new evenement();
        String requete = "SELECT * FROM `evenement` where id=" + id;

        try {
            ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);

            while (rs.next()) {

                event = new evenement(rs.getInt("id"), rs.getInt("nb_participants"), rs.getString("nom_event"), rs.getString("type_event"), rs.getString("image_event"), rs.getString("description_event"), rs.getDate("date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }

    public ObservableList<evenement> FetchEvents() {
        ObservableList<evenement> events = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `evenement`";
        try {
            ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);

            while (rs.next()) {
                events.add(new evenement(rs.getInt("id"), rs.getInt("nb_participants"), rs.getString("nom_event"), rs.getString("type_event"), rs.getString("image_event"), rs.getString("description_event"), rs.getDate("date")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events;
    }

    public String GenerateQrEvent(evenement event) throws FileNotFoundException, IOException {
//        String eventName = "event name: " + event.getNom_event() + "\n" + "event date: " + event.getDate() + "\n" + "event description: " + event.getDescription_event() + "\n";
//        ByteArrayOutputStream out = QRCode.from(eventName).to(ImageType.JPG).stream();
//        String filename = event.getNom_event() + "_QrCode.jpg";
//        //File f = new File("src\\utils\\img\\" + filename);
//                File f = new File("C:\\xampp\\htdocs\\integration projet\\exchangex integration\\imgQr\\qrcode" + filename);
//        FileOutputStream fos = new FileOutputStream(f);
//        fos.write(out.toByteArray());
//        fos.flush();
//       
//        System.out.println("qr yemshi");
//        return filename;

 String eventName = "event name: " + event.getNom_event() + "\n" + "event date: " + event.getDate() + "\n" + "event description: " + event.getDescription_event() + "\n";
        ByteArrayOutputStream out = QRCode.from(eventName).to(ImageType.JPG).stream();
        String filename = event.getNom_event() + "_QrCode.jpg";
        //File f = new File("src\\utils\\img\\" + filename);
                File f = new File("C:\\xampp\\htdocs\\xchangex\\imgQr\\qrcode" + filename);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        fos.flush();
       
        System.out.println("qr yemshi");
        return filename;
    }

    public ObservableList<evenement> chercherEvent(String chaine) {
        String sql = "SELECT * FROM evenement WHERE (nom_event LIKE ? or Type_event LIKE ?  ) order by nom_event ";
        //Connection cnx= Maconnexion.getInstance().getCnx();
        String ch = "%" + chaine + "%";
        ObservableList<evenement> myList = FXCollections.observableArrayList();
        try {

            Statement ste = cnx.createStatement();
            // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee = cnx.prepareStatement(sql);
            stee.setString(1, ch);
            stee.setString(2, ch);

            ResultSet rs = stee.executeQuery();
            while (rs.next()) {
                evenement e = new evenement();

                e.setNom_event(rs.getString("nom_event"));
                e.setType_event(rs.getString("Type_event"));
                e.setImage_event(rs.getString("Image_event"));
                e.setDescription_event(rs.getString("description_event"));
                e.setDate(rs.getDate("date"));
                e.setNbrparticipants(rs.getInt("nb_participants"));
                e.setId_event(rs.getInt("id"));

                myList.add(e);
                System.out.println("event trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<evenement> trierEvent()throws SQLException {
        List<evenement> evenements = new ArrayList<>();
        String s = "select * from evenement order by nom_event ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            evenement e = new evenement();
            e.setNom_event(rs.getString("nom_event"));
            e.setType_event(rs.getString("Type_event"));
            e.setImage_event(rs.getString("Image_event"));
            e.setDescription_event(rs.getString("description_event"));
            e.setDate(rs.getDate("date"));
            e.setNbrparticipants(rs.getInt("nb_participants"));
            e.setId_event(rs.getInt("id"));
            evenements.add(e);
        }
        return evenements;
    }  
}
