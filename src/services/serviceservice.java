/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.service;
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
public class serviceservice implements gererservice <service> {
    
     
          public Statement ste;
    public PreparedStatement pst;
    Connection cnx;

    public serviceservice() {
               cnx =MyDB.getInstance().getCnx();
    }


    
       @Override
    public void ajouter(service s) throws SQLException {
     
                String requete = "INSERT INTO `service` (`titre_service`,`type_service`,`description_service`,`lieu_service`) "
                + "VALUES (?,?,?,?);";

        try {
            pst = (PreparedStatement) cnx.prepareStatement(requete);
            pst.setString(1, s.getTitre_service());
            pst.setString(2, s.getType_service());
            pst.setString(3, s.getDescription_service());
            pst.setString(4, s.getLieu_service());
            
       
            pst.executeUpdate();
            System.out.println("service " + s.getId_service()+ " added successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    
    public void modifier(service s) throws SQLException {
         String req = " UPDATE service SET titre_service = ? , type_service = ? , description_service = ?  , lieu_service = ?   where id  = ?    ";
            PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, s.getTitre_service());
        ps.setString(2, s.getType_service());
        ps.setString(3, s.getDescription_service());
        ps.setString(4, s.getLieu_service());
        ps.setInt(5, s.getId_service());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(service s) throws SQLException {
         String req = " DELETE FROM service where id  = ?   ";
       
               PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, s.getId_service());
             ps.executeUpdate();

    }

    @Override
    public List<service> recuperer() throws SQLException {
        List<service> serv = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM service";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                service s=new service();
                s.setId_service(rs.getInt("id"));
                s.setTitre_service(rs.getString("titre_service"));
                s.setType_service(rs.getString("type_service"));
                s.setDescription_service(rs.getString("description_service"));  
                    s.setLieu_service(rs.getString("lieu_service"));
                
             serv.add(s);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return serv;
    }

    public int countServicesByType(String selectedServiceType) {
            int count = 0;
    try {
        String req = "SELECT COUNT(*) AS count FROM service WHERE type_service = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, selectedServiceType);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt("count");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return count;
    }

    public List<service> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
       
}
