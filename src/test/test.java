/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import services.CategorieService;

/**
 *
 * @author sbekr
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
CategorieService cs = new CategorieService();
        System.out.println(cs.getCategorieIdByName("homme"));

    }
    
}
