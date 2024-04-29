/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sbekr
 */
public interface Icategorie {
            public boolean ajoutercategorie(Categorie c)throws SQLException;
        public boolean updatecategorie(Categorie c)throws SQLException;
    public boolean suprimecategorie(Categorie c)throws SQLException;
    public List<Categorie> getAll()throws SQLException;
    
}
