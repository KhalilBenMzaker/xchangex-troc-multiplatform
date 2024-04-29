/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sbekr
 */
public interface Iproduit {
        public boolean ajouterproduit(Produit p)throws SQLException;
        public boolean updateproduit(Produit p)throws SQLException;
    public boolean suprimeproduit(Produit p)throws SQLException;
    public List<Produit> getAll()throws SQLException;
    
}
