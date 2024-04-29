/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.echange;
import gui.AjouterReclamationController;
import entities.reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author msi
 */
public class reclamationservice implements ireclamation <reclamation>  {
    Connection cnx;

    public reclamationservice() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
public void ajouterR(reclamation t) throws SQLException {
    String req = "INSERT INTO reclamation(etat_rec,date_reclamation,image_event,description_reclamation) VALUES (DEFAULT,CURRENT_TIMESTAMP,?,?)";
        System.out.println(t.getDescription_reclamation());
    PreparedStatement st1 = cnx.prepareStatement(req);
//    st1.setInt(1, t.getEchange().getId_echange());
    st1.setString(1, t.getImage_event());
    st1.setString(2, t.getDescription_reclamation());
    st1.executeUpdate(); // Use `st1` instead of `req`
    System.out.println("Reclamation envoyée avec succès");
}


    @Override
    public void modifierR(reclamation t) throws SQLException {
                            String mod = "UPDATE reclamation SET etat_rec = ? WHERE id = ?";
                    PreparedStatement st4 = cnx.prepareStatement(mod);
                    st4.setString(1, t.getEtat_rec());
                    st4.setInt(2, t.getId_reclamation());
                    st4.executeUpdate();
                    System.out.println("Mise à jour de la réclamation effectuée avec succès");
    }

    @Override
    public boolean supprimerR(reclamation t) throws SQLException {
                boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from reclamation where id = ? ");
            req.setInt(1, t.getId_reclamation());
            req.executeUpdate();
            System.out.println("Reclamation supprimée");
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;  
        
    }
    
     public void suppetat(reclamation p) {
        try {
            String supp = "DELETE FROM reclamation WHERE etat_rec LIKE 'Traitée' ";
            PreparedStatement st2 = cnx.prepareStatement(supp);
            st2.executeUpdate();
            System.out.println("Reclamation supprimée avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression " + ex.getMessage());
        }
    }

    public List<reclamation> recuperer(reclamation t) throws SQLException {
        List<reclamation> Reclamations = new ArrayList<>();
        String s = "select * from reclamation";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            reclamation r=new reclamation();
            r.setId_reclamation(rs.getInt("id"));
            r.setEchange(new echange(rs.getString("echange_id")));
            r.setEtat_rec(rs.getString("etat_rec"));
            r.setDate_reclamation(rs.getString("date_reclamation"));
            r.setImage_event(rs.getString("image_event"));
            r.setDescription_reclamation(rs.getString("description_reclamation"));
            Reclamations.add(r);
            
        }
        return Reclamations;
    }
    


}
