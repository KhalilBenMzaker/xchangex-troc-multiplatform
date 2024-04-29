/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author yomna
 */
public class commentaire {
   private int id_commentaire, id_service;
   private String contenu_commentaire;

    public commentaire() {
    }

    public commentaire(int id_commentaire, int id_service, String contenu_commentaire) {
        this.id_commentaire = id_commentaire;
        this.id_service = id_service;
        this.contenu_commentaire = contenu_commentaire;
    }

    
     public commentaire( int id_service, String contenu_commentaire) {
        
        this.id_service = id_service;
        this.contenu_commentaire = contenu_commentaire;
    }
    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setContenu_commentaire(String contenu_commentaire) {
        this.contenu_commentaire = contenu_commentaire;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public int getId_service() {
        return id_service;
    }

    public String getContenu_commentaire() {
        return contenu_commentaire;
    }
   
    @Override
    public String toString() {
        return "commentaire{" + "id_commentaaire=" + id_commentaire + ", id_service=" + id_service + ", contenu_commentaire=" + contenu_commentaire + '}';
    }
   
    
}
