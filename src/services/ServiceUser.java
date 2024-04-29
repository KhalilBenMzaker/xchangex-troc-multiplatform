/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Role;
import entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import utils.BCryptPass;
import utils.MyDB;
import entities.Ratingechange;

/**
 *
 * @author Skymil
 */
public class ServiceUser implements IServiceUser<User>{
    Connection cnx;
    public ServiceUser(){
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(User t) {
        try {
            String role =  t.getRole().getSymfonyRole() ;
            String query="INSERT INTO `user`"
                    + "(`nom`, `prenom`,"
                    + " `date_naissance`, "
                    + "`email`, `username`, "
                    + "`password`, `num_tel`, `adresse`, `roles` , `rating`)"
                    + "VALUES ('"+t.getNom()+"',"
                    + "'"+t.getPrenom()+"','"+t.getDate_naissance()+"','"+t.getEmail()+"',"
                    + "'"+t.getUsername()+"','"+BCryptPass.cryptMDP(t.getPassword())+"','"+t.getNumTel()+"',"
                    + "'"+t.getAdresse()+"','"+role+"','"+t.getRating()+"')";
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id, User t) {
        try {
            String role =  t.getRole().getSymfonyRole()  ;
            String query="UPDATE `user` SET "
                    + "`nom`='"+t.getNom()+"',"
                    + "`prenom`='"+t.getPrenom()+"',"
                    + "`date_naissance`='"+t.getDate_naissance()+"',"
                    + "`email`='"+t.getEmail()+"',"
                    + "`username`='"+t.getUsername()+"',"
                    + "`password`='"+BCryptPass.cryptMDP(t.getPassword())+"',"
                    + "`num_tel`='"+t.getNumTel()+"',"
                    + "`adresse`='"+t.getAdresse()+"',"
                    + "`roles`='"+role+"',"
                    + "`rating`='"+t.getRating()+"' WHERE id="+id;
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String query="DELETE FROM `user` WHERE id="+id;
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> afficher() {
    List<User> lu = new ArrayList<>();
    try {
        String query = "SELECT * FROM `user`";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            User u = new User();
            u.setAdresse(rs.getString("adresse"));
            u.setId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setDate_naissance(rs.getDate("date_naissance"));
            u.setRole(mapToRole(rs.getString("roles")));
            u.setNumTel(rs.getInt("num_tel"));
            u.setRating(rs.getInt("rating"));
            lu.add(u);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
    }

    return lu;
}

private Role mapToRole(String roleValue) {
    if (roleValue.equals("[\"ROLE_USER\"]")) {
        return Role.ROLE_USER;
    } else if (roleValue.equals("[\"ROLE_ADMIN\"]")) {
        return Role.ROLE_ADMIN;
    } else if (roleValue.equals("[\"ROLE_PROSELLER\"]")) {
        return Role.ROLE_PROSELLER;
    } else {
        // Handle unrecognized role values
        return null;
    }
}

    public User getById(int id){
        User u=new User();
        try {
            String query="SELECT * FROM `user` where id="+id;
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                
                u.setAdresse(rs.getString("adresse"));
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setRole(mapToRole(rs.getString("roles")));
                u.setNumTel(rs.getInt("num_tel"));
                u.setRating(rs.getInt("rating"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    public User login(String usernameouemail,String mdp){
        return afficher().stream().filter(
                u->u.getEmail().equals(usernameouemail)||
                        u.getUsername().equals(usernameouemail)
        ).filter(u->BCryptPass.checkPass(mdp, u.getPassword().replace("$2y$","$2a$"))).findFirst().orElse(null);
        
    }
    public boolean checkuser(String usernameouemail){
        return afficher().stream().filter(
                u->u.getEmail().equals(usernameouemail)||
                        u.getUsername().equals(usernameouemail)).findFirst().isPresent();
    }
    public User getUserByEmail(String email){
        return afficher().stream().filter(
                u->u.getEmail().equals(email)).findFirst().orElse(null);
    }
    public List<User> triUserparNom(){
        return afficher().stream().sorted((u1,u2)->u1.getNom().compareTo(u2.getNom())).collect(Collectors.toList());
    }
}
