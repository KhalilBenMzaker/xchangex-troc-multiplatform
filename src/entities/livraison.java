/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author octanet
 */
public class livraison {
    private int id;
    private String  type_liv,adress_liv;
    private Date date_liv;

    public livraison() {
    }

    public livraison(int id, String type_liv, String adress_liv, Date date_liv) {
        this.id = id;
        this.type_liv = type_liv;
        this.adress_liv = adress_liv;
        this.date_liv = date_liv;
    }
     public livraison(String type_liv, String adress_liv, Date date_liv) {
        
        this.type_liv = type_liv;
        this.adress_liv = adress_liv;
        this.date_liv = date_liv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_liv() {
        return type_liv;
    }

    public void setType_liv(String type_liv) {
        this.type_liv = type_liv;
    }

    public String getAdress_liv() {
        return adress_liv;
    }

    public void setAdress_liv(String adress_liv) {
        this.adress_liv = adress_liv;
    }

    public Date getDate_liv() {
        return date_liv;
    }

    public void setDate_liv(Date date_liv) {
        this.date_liv = date_liv;
    }

    @Override
    public String toString() {
        return "livraison{" + "id=" + id + ", type_liv=" + type_liv + ", adress_liv=" + adress_liv + ", date_liv=" + date_liv + '}';
    }
    

    
    
    
    
    
    
    
    
    
    
}
