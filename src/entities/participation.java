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
public class participation extends evenement{
    private int id_part;        
private Date date_part;    
private int id_user;
public int id_evenement; 
public evenement evenement;

    public participation() {
    }

    public participation(int id_part, Date date_participation, int id_user, int id_evenement) {
        this.id_part = id_part;
        this.date_part = date_participation;
        this.id_user = id_user;
        this.id_evenement = id_evenement;
    }
    public participation(Date date_participation, int id_user, int id_evenement) {
        this.date_part = date_participation;
        this.id_user = id_user;
        this.id_evenement = id_evenement;
    }

    public participation(int id_part, Date date_participation, int id_user, int id_evenement, evenement evenement) {
        this.id_part = id_part;
        this.date_part = date_participation;
        this.id_user = id_user;
        this.id_evenement = id_evenement;
        this.evenement = evenement;
    }

    public int getId_part() {
        return id_part;
    }

    public Date getDate_part() {
        return date_part;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public evenement getEvenement() {
        return evenement;
    }

    public void setId_part(int id_part) {
        this.id_part = id_part;
    }

    public void setDate_part(Date date_participation) {
        this.date_part = date_participation;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setEvenement(evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "participation{" + "id_part=" + id_part + ", date_participation=" + date_part + ", id_user=" + id_user + ", id_evenement=" + id_evenement +  '}';
    }
    
    
    




}
