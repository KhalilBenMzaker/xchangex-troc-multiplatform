/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author yomna
 */
public class commentaireservice implements gererservice <commentaire>{
     
          public Statement ste;
    public PreparedStatement pst;
    Connection cnx;


    public commentaireservice() {
       cnx =MyDB.getInstance().getCnx();
    }
    

       @Override
    public void ajouter(commentaire c) throws SQLException {
     
        String requete = "INSERT INTO `commentaire` (`id_service`,`contenu_commentaire`) "
                + "VALUES (?,?);";

        try {
            pst = (PreparedStatement) cnx.prepareStatement(requete);
            pst.setInt(1, c.getId_service());
            pst.setString(2, c.getContenu_commentaire());
       
            pst.executeUpdate();
            System.out.println("comment " + c.getId_commentaire()+ " added successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    
    public void modifier(commentaire c) throws SQLException {
         String req = " UPDATE commentaire SET id_service = ? , contenu_commentaire = ?    where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setInt(1, c.getId_service());
        ps.setString(2, c.getContenu_commentaire());
        ps.setInt(3, c.getId_commentaire());
        
        ps.executeUpdate();
        
            
    }

    @Override
    public void supprimer(commentaire c) throws SQLException {
         String req = " DELETE FROM commentaire where id= ?   ";
       
               PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, c.getId_commentaire());
             ps.executeUpdate();

    }

    @Override
    public List<commentaire> recuperer() throws SQLException {
        List<commentaire> com = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM commentaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                commentaire c=new commentaire();
                c.setId_commentaire(rs.getInt("id"));
                c.setId_service(rs.getInt("id_service"));
                c.setContenu_commentaire(rs.getString("contenu_commentaire"));
               
                
                
                com.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return com;
    }

    
    
    
 
   

}
