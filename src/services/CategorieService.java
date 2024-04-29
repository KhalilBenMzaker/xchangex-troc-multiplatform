/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author sbekr
 */
public class CategorieService implements Icategorie{
    Connection cnx;

    public CategorieService() {
        cnx =MyDB.getInstance().getCnx();
    }

    @Override
    public boolean ajoutercategorie(Categorie c) throws SQLException {
        String request = "INSERT INTO categorie	 (nom_categorie)"
                + " VALUES (?)";
        
            PreparedStatement pst = cnx.prepareStatement(request);
            
            pst.setString(1, c.getNom_categorie());

            pst.executeUpdate();
            System.out.println("categorie Added");
            return true;     }

    @Override
    public boolean updatecategorie(Categorie c) throws SQLException {
       String request = "UPDATE categorie SET nom_categorie=? WHERE id =?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, c.getNom_categorie());

            pst.setInt(2, c.getId_categorie());
            pst.executeUpdate();
            System.out.println("categorie Updated");
            return true;    }

    @Override
    public boolean suprimecategorie(Categorie c) throws SQLException {
    String req = "DELETE FROM categorie WHERE id = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, c.getId_categorie());
    ps.executeUpdate();
                System.out.println("categorie delated");
            return true;    }

    @Override
    public List<Categorie> getAll() throws SQLException {
              List<Categorie> Categories = new ArrayList<>();
        String s = "select * from categorie ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Categorie p = new Categorie();
            p.setId_categorie(rs.getInt("id"));
            p.setNom_categorie(rs.getString("nom_categorie"));
            


            Categories.add(p);

        }
        return Categories;    }
    public List<String> getEmails() {
    List<String> emails = new ArrayList<>();
    try {
        String query = "SELECT email FROM `user`";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            String email = rs.getString("email");
            emails.add(email);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
    }
    return emails;
}
        public List<String> getnamecat() {
    List<String> emails = new ArrayList<>();
    try {
        String query = "SELECT nom_categorie FROM `categorie`";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            String email = rs.getString("nom_categorie");
            emails.add(email);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
    }
    return emails;
}

 public List<Integer> getIdCat() {
    List<Integer> ids = new ArrayList<>();
    try {
        String query = "SELECT id FROM `categorie`";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            int id = rs.getInt("id");
            ids.add(id);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ids;
}
 public int getCategorieIdByName(String nomCategorie) {
    int idCategorie = 0;
    try {
        String query = "SELECT id FROM categorie WHERE nom_categorie = ?";
        PreparedStatement ps = cnx.prepareStatement(query);
        ps.setString(1, nomCategorie);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            idCategorie = rs.getInt("id");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
    }
    return idCategorie;
}


}
