/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import UICameriere.CameriereMainFrame;
import java.io.IOException;
import menu.MenuElement;
import restaurant.Order;
import restaurant.Restaurant;

/**
 *
 * @author Fabio
 */
public class TestUICameriere {
    
    public static void main(String[] args) throws IOException {
        
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");
        
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Descrizione.");
        MenuElement me2 = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Altra descrizione.");
        Order order = new Order(1,me, "al dente");
        Order order2 = new Order(1,me, "al dente");
        diablo.addOrder(1, order);
        diablo.addOrder(1, order2);
        
        CameriereMainFrame cmf = new CameriereMainFrame(diablo);
        
        
    }
}
