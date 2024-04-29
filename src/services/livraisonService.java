/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.livraison;
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
public class livraisonService implements Ilivraison<livraison> {
    

    Connection cnx;

    public livraisonService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(livraison l) throws SQLException {
        String req = "INSERT INTO livraison(date_liv,type_liv,adress_liv) VALUES("
                + " ' " + l.getDate_liv() + "','" + l.getType_liv() + "','" + l.getAdress_liv() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    

    @Override
    public void modifier(livraison l) throws SQLException {
        String req = "UPDATE livraison SET date_liv = ?,type_liv = ?,adress_liv = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setDate(1, l.getDate_liv());
        ps.setString(2, l.getType_liv());
        ps.setString(3, l.getAdress_liv());
        ps.setInt(4, l.getId());
        ps.executeUpdate();

    }

     @Override
    public void supprimer(livraison l) throws SQLException {
         String req = " DELETE FROM livraison where id = ?   ";
       
               PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, l.getId());
             ps.executeUpdate();

    }

    @Override
    public List<livraison> recuperer() throws SQLException {
        List<livraison> livraison = new ArrayList<>();
        String s = "select * from livraison";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            livraison liv=new livraison();
            liv.setDate_liv(rs.getDate("date_liv"));
            liv.setType_liv(rs.getString("type_liv"));
            liv.setAdress_liv(rs.getString("adress_liv"));
            liv.setId(rs.getInt("id"));

            livraison.add(liv);

        }
        return livraison;
    }

   public ObservableList<livraison> searchByLivraison(String type_liv) throws SQLException{
        String qry="SELECT * FROM livraison where type_liv LIKE '%"+type_liv+"%'" ;
                  System.out.println(qry);
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
ObservableList<livraison> data = FXCollections.observableArrayList();       
while(rs.next()){
        livraison liv=new livraison();
            liv.setDate_liv(rs.getDate("date_liv"));
            liv.setType_liv(rs.getString("type_liv"));
            liv.setAdress_liv(rs.getString("adress_liv"));
//            liv.setId(rs.getInt("id"));

            data.add(liv);
        }
         

        return data ;
    } 
public ObservableList<livraison> getAllTriType() {
        ObservableList<livraison> list = FXCollections.observableArrayList();
        try {
         //   String req = "Select * from espacetalent where roles like '%[]%' order by nom";
                String qry = "Select * from livraison  order by type_liv";
                  System.out.println(qry);
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
            //    EspaceTalent u = new EspaceTalent(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"), rs.getString("file"), rs.getInt("etat"), rs.getDate("created_at"));
             //livraison a = new livraison(rs.getString(2), rs.getString(3), rs.getDate(4)); 
      livraison liv=new livraison();
            liv.setDate_liv(rs.getDate("date_liv"));
            liv.setType_liv(rs.getString("type_liv"));
            liv.setAdress_liv(rs.getString("adress_liv"));
            liv.setId(rs.getInt("id"));

            list.add(liv);
   
        //list.add(a) ;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
 


    
  
    
    
    
    
    
}
