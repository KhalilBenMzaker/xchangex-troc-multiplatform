/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author sbekr
 */
public class ProduitService implements Iproduit {
    Connection cnx;

    public ProduitService() {
      cnx = MyDB.getInstance().getCnx();
    }


    @Override
    public boolean ajouterproduit(Produit p) throws SQLException {

        String request = "INSERT INTO produit (nom,image_name, decription, prix, nom_categori_id)"
                + " VALUES (?,?,?,?,?)";
        
            PreparedStatement pst = cnx.prepareStatement(request);
            
            pst.setString(1, p.getNom_produit());
            pst.setString(2, p.getImage_produit());
            pst.setString(3, p.getDescription_produit());
            pst.setFloat(4, p.getPrix_produit());
            pst.setInt(5, p.getCategorie().getId_categorie());
            pst.executeUpdate();
            System.out.println("produit Added");
            return true;   
    }

    @Override
    public boolean updateproduit(Produit p) throws SQLException {
       String request = "UPDATE produit SET nom=?, image_name=?, decription=?, prix=?, nom_categori_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, p.getNom_produit());
            pst.setString(2, p.getImage_produit());
            pst.setString(3, p.getDescription_produit());
            pst.setFloat(4, p.getPrix_produit());
            pst.setInt(5, p.getCategorie().getId_categorie());
            pst.setInt(6, p.getId_produit());
            pst.executeUpdate();
            System.out.println("poduit Updated");
            return true;

    
    }

    @Override
    public boolean suprimeproduit(Produit p) throws SQLException {
    String req = "DELETE FROM produit WHERE id = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, p.getId_produit());
    ps.executeUpdate();
                System.out.println("poduit deletd");
            return true;
    }

    @Override
    public List<Produit> getAll()throws SQLException {
                List<Produit> Produits = new ArrayList<>();
        String s = "select * from produit ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Produit p = new Produit();
            p.setId_produit(rs.getInt("id"));
            p.setNom_produit(rs.getString("nom"));
            p.setImage_produit(rs.getString("image_name"));
            p.setDescription_produit(rs.getString("decription"));
            p.setPrix_produit(rs.getFloat("prix"));
             p.setCategorie(new Categorie(rs.getString("nom_categori_id")));
            Produits.add(p);

        }
        return Produits;


    }
    public Produit selectProduitById(int id) throws SQLException {
    String query = "SELECT * FROM produit WHERE id = ?";
    PreparedStatement statement = cnx.prepareStatement(query);
    statement.setInt(1, id);
    ResultSet rs = statement.executeQuery();
    
    Produit produit = null;
    
    if (rs.next()) {
        produit = new Produit();
        produit.setId_produit(rs.getInt("id"));
   
        
    }
    
    rs.close();
    statement.close();
    
    return produit;
}
    public int getProductIdByName(String name) throws SQLException {
    String request = "SELECT id FROM produit WHERE nom = ?";
    PreparedStatement pst = cnx.prepareStatement(request);
    pst.setString(1, name);
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
        return rs.getInt("id");
    } else {
        return -1; // or throw an exception, depending on your needs
    }
}
        public ObservableList<Produit> chercherprduit(String chaine) {
        String sql = "SELECT * FROM produit WHERE (nom LIKE ? or decription LIKE ?  ) order by nom ";
        //Connection cnx= Maconnexion.getInstance().getCnx();
        String ch = "%" + chaine + "%";
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        try {

            Statement ste = cnx.createStatement();
            // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee = cnx.prepareStatement(sql);
            stee.setString(1, ch);
            stee.setString(2, ch);

            ResultSet rs = stee.executeQuery();
            while (rs.next()) {
                Produit e = new Produit();

                e.setNom_produit(rs.getString("nom"));
                e.setImage_produit(rs.getString("image_name"));
                e.setDescription_produit(rs.getString("decription"));
                e.setPrix_produit(rs.getFloat("prix"));

                myList.add(e);
                System.out.println("Produit trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
            public List<Produit> trierprduit()throws SQLException {
        List<Produit> Produits = new ArrayList<>();
        String s = "select * from produit order by nom";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Produit e = new Produit();
            e.setNom_produit(rs.getString("nom"));
            e.setImage_produit(rs.getString("image_name"));
            e.setDescription_produit(rs.getString("decription"));
            e.setPrix_produit(rs.getFloat("prix"));

            Produits.add(e);
        }
        return Produits;
    }

}
