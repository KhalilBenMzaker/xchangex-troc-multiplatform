/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author khali
 */
public class Ratingechange {
    
    private int id_echange,note,id_user_notant,id_user_note;

    public Ratingechange() {
    }

    public Ratingechange(int id_echange, int note, int id_user_notant, int id_user_note) {
        this.id_echange = id_echange;
        this.note = note;
        this.id_user_notant = id_user_notant;
        this.id_user_note = id_user_note;
    }

    public int getId_echange() {
        return id_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getId_user_notant() {
        return id_user_notant;
    }

    public void setId_user_notant(int id_user_notant) {
        this.id_user_notant = id_user_notant;
    }

    public int getId_user_note() {
        return id_user_note;
    }

    public void setId_user_note(int id_user_note) {
        this.id_user_note = id_user_note;
    }

    @Override
    public String toString() {
        return "Rating{" + "id_echange=" + id_echange + ", note=" + note + ", id_user_notant=" + id_user_notant + ", id_user_note=" + id_user_note + '}';
    }
    
    
    
}
