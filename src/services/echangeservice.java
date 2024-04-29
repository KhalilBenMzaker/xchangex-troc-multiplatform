/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.echange;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author msi
 */
public class echangeservice implements iechange <echange>{
    Connection cnx;

    public echangeservice() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
public void ajouter(echange t) throws SQLException {
              
        String req = "INSERT INTO echange(username,date_echange,lieu_echange,type_echange) VALUES(?,?,?,?)";
        PreparedStatement st1 = cnx.prepareStatement(req);
            
        st1.setString(1, t.getUsername());
        st1.setString(2, t.getDate_echange());
        st1.setString(3, t.getLieu_echange());
        st1.setString(4, t.getType_echange());
        
        st1.executeUpdate(); // Use `st1` instead of `req`
        System.out.println("Echange ajouté avec succès");
}


    @Override
    public void modifier(echange t) throws SQLException {
        String req = "UPDATE echange SET username= ?,date_echange = ?,lieu_echange = ?,type_echange = ? WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getUsername());
        ps.setString(2, t.getDate_echange());
        ps.setString(3, t.getLieu_echange());
        ps.setString(4, t.getType_echange());
        ps.setInt(5, t.getId_echange());
        ps.executeUpdate();
        System.out.println("Mise à jour de l'echange effectuée avec succès");
    }

    @Override
    public boolean supprimer(echange t) throws SQLException {
                 boolean ok = false;
        try {
            String req = "delete from echange where id = ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId_echange());
        ps.executeUpdate();
            System.out.println("Echange supprimé");
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok; 
    }

    @Override
    public List<echange> recuperer(echange t) throws SQLException {
             List<echange> Echanges = new ArrayList<>();
//             Personne c1 = new Personne();

        String s = "select * from echange";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            echange e = new echange();
//            e.setId_echange(rs.getInt("id_echange"));
//            e.setId_user(rs.getInt("id_user"));
            e.setUsername(rs.getString("username"));
            e.setDate_echange(rs.getString("date_echange"));
            e.setLieu_echange(rs.getString("lieu_echange"));
            e.setType_echange(rs.getString("type_echange"));
            
            
            Echanges.add(e);
            
        }
        return Echanges;
    }
    


    
}
