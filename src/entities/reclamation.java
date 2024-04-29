/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;



/**
 *
 * @author msi
 */
public class reclamation {
    private int id_reclamation;
    private String description_reclamation;
    public String date_reclamation;
    public String etat_rec,image_event;
    private echange echange;

    public reclamation(int id_reclamation, String description_reclamation, String date_reclamation, String etat_rec, String image_event, echange echange) {
        this.id_reclamation = id_reclamation;
        this.description_reclamation = description_reclamation;
        this.date_reclamation = date_reclamation;
        this.etat_rec = etat_rec;
        this.image_event = image_event;
        this.echange = echange;
    }

    public reclamation(String description_reclamation, String date_reclamation, String etat_rec, String image_event, echange echange) {
        this.description_reclamation = description_reclamation;
        this.date_reclamation = date_reclamation;
        this.etat_rec = etat_rec;
        this.image_event = image_event;
        this.echange = echange;
    }

    
    public reclamation(String description_reclamation, String date_reclamation, String etat_rec, echange echange) {
        this.description_reclamation = description_reclamation;
        this.date_reclamation = date_reclamation;
        this.etat_rec = etat_rec;
        this.echange = echange;
    }

    
    public reclamation(String description_reclamation, String date_reclamation, echange echange) {
        this.description_reclamation = description_reclamation;
        this.date_reclamation = date_reclamation;
        this.echange = echange;
    }

    
    public reclamation() {
    }

    public reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    public reclamation(int id_reclamation, String description_reclamation, String date_reclamation, String etat_rec, echange echange) {
        this.id_reclamation = id_reclamation;
        this.description_reclamation = description_reclamation;
        this.date_reclamation = date_reclamation;
        this.etat_rec = etat_rec;
        this.echange = echange;
    }

    

    
    public reclamation(int id_reclamation, String description_reclamation, String date_reclamation, echange echange) {
        this.id_reclamation = id_reclamation;
        this.description_reclamation = description_reclamation;
        this.date_reclamation = date_reclamation;
        this.echange = echange;
    }

    

    public int getId_reclamation() {
        return id_reclamation;
    }

    public String getDescription_reclamation() {
        return description_reclamation;
    }

    public String getDate_reclamation() {
        return date_reclamation;
    }

    public String getEtat_rec() {
        return etat_rec;
    }

    public String getImage_event() {
        return image_event;
    }
    

    
    public echange getEchange() {
        return echange;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setDescription_reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    public void setDate_reclamation(String date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public void setEtat_rec(String etat_rec) {
        this.etat_rec = etat_rec;
    }

    public void setImage_event(String image_event) {
        this.image_event = image_event;
    }
    

    
    public void setEchange(echange echange) {
        this.echange = echange;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id_reclamation=" + id_reclamation + ", description_reclamation=" + description_reclamation + ", date_reclamation=" + date_reclamation + ", echange=" + echange + ", image_event=" + image_event + ", etat_rec=" + etat_rec + '}';
    }
    
    

 
    
    
}
