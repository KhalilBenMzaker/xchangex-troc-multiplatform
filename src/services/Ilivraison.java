/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author octanet
 */
public interface Ilivraison  <T> {
    
     public void ajouter(T l) throws SQLException;
     public void modifier(T l) throws SQLException;
     public void supprimer(T l) throws SQLException;
     public List<T> recuperer() throws SQLException;
    
    
}