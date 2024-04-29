/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class evenement {
    
        private int id_event;
        private int nbrparticipants;
    private String nom_event,type_event,image_event,description_event;
    private Date date;

    public evenement() {
    }

    public evenement(int id_event,int nb_participants, String nom_event, String type_event, String image_event, String description_event, Date date) {
        this.id_event = id_event;
        this.nbrparticipants=nb_participants;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.image_event = image_event;
        this.description_event = description_event;
        this.date = date;
    }
    public evenement(int nb_participants, String nom_event, String type_event, String image_event, String description_event, Date date) {
        this.nbrparticipants=nb_participants;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.image_event = image_event;
        this.description_event = description_event;
        this.date = date;
    }
    
    
     public evenement(int id_event,int nb_participants, String nom_event, String type_event, String image_event, String description_event) {
        this.id_event = id_event;
        this.nbrparticipants=nb_participants;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.image_event = image_event;
        this.description_event = description_event;
        
    }
    
    
     //****************** getters ****************

    public int getId_event() {
        return id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public String getType_event() {
        return type_event;
    }

    public String getImage_event() {
        return image_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public Date getDate() {
        return date;
    }
    
    
    //****************** setters ****************

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public void setImage_event(String image_event) {
        this.image_event = image_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbrparticipants() {
        return nbrparticipants;
    }

    public void setNbrparticipants(int nbrparticipants) {
        this.nbrparticipants = nbrparticipants;
    }
    
    

    @Override
    public String toString() {
        return "evenement{" + "id_event=" + id_event+ ", nbr parti=" + nbrparticipants  + ", nom_event=" + nom_event + ", type_event=" + type_event + ", image_event=" + image_event + ", description_event=" + description_event + ", date=" + date + '}';
    }
    
    
    
    
    
    
}
