/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.util.List;
import entities.echange;

/**
 *
 * @author msi
 */
public interface iechange <echange> {
        public void ajouter(echange t) throws SQLException;
    public void modifier(echange t) throws SQLException;
    public boolean supprimer(echange t) throws SQLException;
    public List<echange> recuperer(echange t) throws SQLException;
    
}
