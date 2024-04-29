/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author octanet
 */
public class livreur {
    private int id_livr,num;
    private String nom_liv, prenom_liv;
    
     
    public livreur(){}
    
    
 public livreur(int id_livr, int num, String nom_liv,String prenom_liv ) {
        this.id_livr = id_livr;
        this.num = num;
        this.nom_liv = nom_liv;
        this.prenom_liv = prenom_liv;
    }    
 public livreur( int num, String nom_liv,String prenom_liv ) {
        
        this.num = num;
        this.nom_liv = nom_liv;
        this.prenom_liv = prenom_liv;
    }    

    public int getId_livr() {
        return id_livr;
    }

    public void setId_livr(int id_livr) {
        this.id_livr = id_livr;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom_liv() {
        return nom_liv;
    }

    public void setNom_liv(String nom_liv) {
        this.nom_liv = nom_liv;
    }

    public String getPrenom_liv() {
        return prenom_liv;
    }

    public void setPrenom_liv(String prenom_liv) {
        this.prenom_liv = prenom_liv;
    }
 
 

    
    @Override
    public String toString() {
        return "livreur{" + "id_livr=" + id_livr + ", num=" + num + ", nom_liv=" + nom_liv + ", prenom_liv=" + prenom_liv + '}';
    }
    
 
    
    
    
    
    
    
    
    
    
}
