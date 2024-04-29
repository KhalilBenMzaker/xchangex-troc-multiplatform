/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.livreur;
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
 * @author octanet
 */
public class livreurService implements Ilivraison<livreur> {
    

    Connection cnx;

    public livreurService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(livreur v) throws SQLException {
        String req = "INSERT INTO livreur(num,nom_liv,prenom_liv) VALUES("
                + "'" + v.getNum() + "','" + v.getNom_liv() + "','" + v.getPrenom_liv() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    
    @Override
    public void modifier(livreur v) throws SQLException {
        String req = "UPDATE livreur SET num = ?,nom_liv = ?,prenom_liv = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, v.getNum());
        ps.setString(2, v.getNom_liv());
        ps.setString(3, v.getPrenom_liv());
        ps.setInt(4, v.getId_livr());
        ps.executeUpdate();

    }
    
    
    
    

     @Override
    public void supprimer(livreur v) throws SQLException {
         String req = " DELETE FROM livreur where id = ?   ";
       
               PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, v.getId_livr());
             ps.executeUpdate();

    }

    @Override
    public List<livreur> recuperer() throws SQLException {
        List<livreur> livreurs = new ArrayList<>();
        String s = "select * from livreur";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while (rs.next()) {
            livreur vr=new livreur();
            vr.setNum(rs.getInt("num"));
            vr.setNom_liv(rs.getString("nom_liv"));
            vr.setPrenom_liv(rs.getString("prenom_liv"));
            vr.setId_livr(rs.getInt("id"));

            livreurs.add(vr);

        }
        return livreurs;
    }

   
    /*public ObservableList<livreur> Syrine(String nom_liv) throws SQLException{
        String qry="SELECT * FROM livreur where nom_liv LIKE '%"+nom_liv+"%'" ;
                  System.out.println(qry);
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
        

            ObservableList<livreur> list = FXCollections.observableArrayList();
         livreur vr=new livreur();
         while(rs.next()){
            vr.setNum(rs.getInt("num"));
            vr.setNom_liv(rs.getString("nom_liv"));
            vr.setPrenom_liv(rs.getString("prenom_liv"));
           // vr.setId_livr(rs.getInt("id_livr"));

            list.add(vr);
          }
          return list ;

        
    }*/
}
 


    
  
    
    
    
    
    

