/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.Date;


/**
 *
 * @author msi
 */
public class echange {
        private int id_echange;
    private String username,lieu_echange,type_echange;
    private String date_echange;

    public echange() {
    }

    public echange(String username) {
        this.username = username;
    }

    public echange(int id_echange) {
        this.id_echange = id_echange;
    }

    public echange(int id_echange, String username, String lieu_echange, String type_echange, String date_echange) {
        this.id_echange = id_echange;
        this.username = username;
        this.lieu_echange = lieu_echange;
        this.type_echange = type_echange;
        this.date_echange = date_echange;
    }

    public echange(String username, String lieu_echange, String type_echange, String date_echange) {
        this.username = username;
        this.lieu_echange = lieu_echange;
        this.type_echange = type_echange;
        this.date_echange = date_echange;
    }

    public int getId_echange() {
        return id_echange;
    }

    public String getUsername() {
        return username;
    }

    public String getLieu_echange() {
        return lieu_echange;
    }

    public String getType_echange() {
        return type_echange;
    }

    public String getDate_echange() {
        return date_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLieu_echange(String lieu_echange) {
        this.lieu_echange = lieu_echange;
    }

    public void setType_echange(String type_echange) {
        this.type_echange = type_echange;
    }

    public void setDate_echange(String date_echange) {
        this.date_echange = date_echange;
    }

    @Override
    public String toString() {
        return "echange{" + "id_echange=" + id_echange + ", username=" + username + ", lieu_echange=" + lieu_echange + ", type_echange=" + type_echange + ", date_echange=" + date_echange + '}';
    }
    
}
