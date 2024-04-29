/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author yomna
 */
public class service {
   private int id_service;
   private String titre_service,type_service,description_service,lieu_service;
   

    public service() {
    }

    public service(int id_service, String titre_service, String type_service, String description_service, String lieu_service) {
        this.id_service = id_service;
        this.titre_service = titre_service;
        this.type_service = type_service;
        this.description_service = description_service;
        this.lieu_service = lieu_service;
        
    }

    
    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setTitre_service(String titre_service) {
        this.titre_service = titre_service;
    }

    public void setType_service(String type_service) {
        this.type_service = type_service;
    }

    public void setDescription_service(String description_service) {
        this.description_service = description_service;
    }

    public void setLieu_service(String lieu_service) {
        this.lieu_service = lieu_service;
    }

   

   

    public int getId_service() {
        return id_service;
    }

    public String getTitre_service() {
        return titre_service;
    }

    public String getType_service() {
        return type_service;
    }

    public String getDescription_service() {
        return description_service;
    }

    public String getLieu_service() {
        return lieu_service;
    }

    

   @Override
    public String toString() {
        return "service{" + "id_service=" + id_service + ", titre_service=" + titre_service + ", type_service=" + type_service + ", description_service=" + description_service + ", lieu_service=" + lieu_service + "\n" + '}';
    }
    
   
    
    
}
   
   

    