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
 * @author yomna
 */
public interface gererservice <S> {
     public void ajouter(S s) throws SQLException;
     public void modifier(S s) throws SQLException;
     public void supprimer(S s) throws SQLException;
     public List<S> recuperer() throws SQLException;
    
    
}
