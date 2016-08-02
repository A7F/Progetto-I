/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import UIcook.CookMainFrame;
import java.io.IOException;
import menu.MenuElement;
import utils.Cook;
import utils.Order;
import utils.Restaurant;

/**
 *
 * @author fabio
 */
public class TestCookUI {
    public static void main(String[] args) throws IOException {
        
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Spaghetti con carbonara, servita calda col ghiaccio e un pizzico di roccia saporita al ragno.\n");
        Order order = new Order(1,me, "al dente");
        diablo.addOrder(1, order);
        diablo.addOrder(1, order);
        System.out.println(diablo.getTables());
        
        Cook cazzino = new Cook("Gigino",diablo.getOrdersArray());
                
        CookMainFrame cf = new CookMainFrame(cazzino,order);
        
    }
}
