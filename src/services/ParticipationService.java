/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.participation;
import entities.evenement;
import entities.UserDetails;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author asus
 */
public class ParticipationService {

    Connection cnx;
    public Statement ste;
    public PreparedStatement pst;

    public ParticipationService() {

        cnx = MyDB.getInstance().getCnx();
    }

    public void ajouterParticipation(participation p) {
        UserDetails U = new UserDetails();
        EvenementService es = new EvenementService();
        String requete = "INSERT INTO `participation` (`date_part`,`evenement_id` ,`user_id`) VALUES(? ,?,?) ;";

        try {
            evenement tempEvent = es.FetchOneEvent(p.getId_evenement());
            System.out.println("before" + tempEvent);
            tempEvent.setNbrparticipants(tempEvent.getNbrparticipants() - 1);
            es.modifierEvenement(tempEvent);
            int new_id = tempEvent.getId_event();
            p.setEvenement(tempEvent);
            System.out.println("after" + tempEvent);

            pst = (PreparedStatement) cnx.prepareStatement(requete);
            pst.setDate(1, p.getDate_part());
            pst.setInt(2, p.getId_evenement());
            pst.setInt(3, p.getId_user());
            pst.executeUpdate();

            System.out.println("participation with id event = " + p.getId_evenement() + " is added successfully");

        } catch (SQLException ex) {
            System.out.println("error in adding reservation");
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<participation> recupererParticipation() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        List<participation> particip = new ArrayList<>();
        String s = "select * from participation";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            participation pa = new participation();
            pa.setId_part(rs.getInt("id"));
            pa.setId_user(rs.getInt("user_id"));
            pa.setId_evenement(rs.getInt("evenement_id"));
            pa.setDate_part(rs.getDate("date_part"));

            particip.add(pa);

        }
        return particip;
    }

    public void supprimerParticipation(participation p) throws SQLException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "delete from participation where id  = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, p.getId_part());
        ps.executeUpdate();
        System.out.println("participation with id= " + p.getId_part() + "  is deleted successfully");
    }

    public participation FetchOneRes(int id) throws SQLException {
        participation r = new participation();
        String requete = "SELECT * FROM `participation` where id=" + id;

        try {
            ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);

            while (rs.next()) {

                r = new participation(rs.getInt("id"), rs.getDate("date_part"), rs.getInt("user_id"), rs.getInt("evenement_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public void DeleteParticipation(participation p) throws SQLException {
        EvenementService es = new EvenementService();
        ParticipationService rs = new ParticipationService();

        participation r = rs.FetchOneRes(p.getId_part());

        String requete = "delete from participation where id=" + p.getId_part();
        try {
            evenement tempEvent = es.FetchOneEvent(r.getId_evenement());
            System.out.println("before" + tempEvent);
            tempEvent.setNbrparticipants(tempEvent.getNbrparticipants() + 1);
            es.modifierEvenement(tempEvent);
            System.out.println("after" + tempEvent);
            pst = (PreparedStatement) cnx.prepareStatement(requete);
            //pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("participation with id=" + p.getId_part() + " is deleted successfully");
        } catch (SQLException ex) {
            System.out.println("error in delete participation " + ex.getMessage());
        }
    }

    public void modifierParticipation(participation p) throws SQLException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "UPDATE participation SET user_id = ?,evenement_id = ?,date_part=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, p.getId_user());
        ps.setInt(2, p.getId_evenement());
        ps.setDate(3, p.getDate_part());
        ps.setInt(4, p.getId_part());

        ps.executeUpdate();
    }

    
    
     public participation FetchOneEvent(int id) {
        participation part = new participation();
        String requete = "SELECT * FROM `participation` where id =" + id;

        try {
            ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);

            while (rs.next()) {

                part = new participation(rs.getInt("id_participation "),rs.getDate("date_part"), rs.getInt("user_id"), rs.getInt("evenement_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return part;
    }
     
     
     public ObservableList<participation> rechherchePartByEvent(int id)throws SQLException {
        ObservableList<participation> participations = FXCollections.observableArrayList();
        String s = "SELECT * FROM `participation` where id =" + id;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            participation p = new participation();
            p.setId_part((rs.getInt("id_participation")));
            p.setDate_part(rs.getDate("date_part"));
            p.setId_user(rs.getInt("user_id"));
            p.setId_evenement(rs.getInt("evenement_id"));     
            participations.add(p);
        }
        return participations;
    }  
}
