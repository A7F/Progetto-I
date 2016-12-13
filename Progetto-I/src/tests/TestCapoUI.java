/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import menu.MenuElement;
import newUIcapo.CapoUI;
import restaurant.Order;
import restaurant.Restaurant;

/**
 *
 * @author Fabio
 */
public class TestCapoUI {
    
    public static void main(String[] args) throws IOException{
        
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");
        
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Spaghetti con carbonara, servita calda col ghiaccio e un pizzico di roccia saporita al ragno.\n");
        MenuElement me2 = new MenuElement("Pasta col Sugo", 5.5, "PRIMO", "Si può scegliere tra tortiglioni, penne e spaghetti, conditi con sugo di pomodoro fresco fatto in giornata.\n");
        
        Order order = new Order(2,me, "al dente");
        diablo.addOrder(1, order);
        diablo.addOrder(1, order);
        diablo.addOrder(1, order);
        
        Order order2 = new Order(3,me2, "al dente");
        diablo.addOrder(1, order2);
       
        System.out.println(diablo.getTables());
        
        CapoUI capoUI = new CapoUI(diablo,0);
    
    }
    
}
