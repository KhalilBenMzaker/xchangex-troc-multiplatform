/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.util.List;
import entities.reclamation;


/**
 *
 * @author msi
 */
public interface ireclamation <reclamation>{
        public void ajouterR(reclamation t) throws SQLException;
    public void modifierR(reclamation t) throws SQLException;
    public boolean supprimerR(reclamation t) throws SQLException;
    public List<reclamation> recuperer(reclamation t) throws SQLException;
    
    
}
