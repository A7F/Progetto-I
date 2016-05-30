/*
 * 
 */
package tests;

import java.io.IOException;
import menu.Menu;
import menu.MenuElement;
import utils.Cook;
import utils.Order;
import utils.Restaurant;

/**
 * Test per il funzionamento di addOrder e removeOrder.
 * @author fabio
 */
public class Test02 {
    public static void main(String[] args) throws IOException {
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");

        // System.out.println(diablo.getMenu());
        
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Spaghetti con carbonara, servita calda col ghiaccio e un pizzico di roccia saporita al ragno.\n");
        Order order = new Order(1,me, "al dente");
        diablo.addOrder(1, order);
        diablo.addOrder(1, order);
        
        System.out.println(diablo.getTables());
        
        //diablo.removeOrder(1, order);
        
        Cook cazzino = new Cook("Gigino",diablo.getOrdersArray());
        
        
        //System.out.println(diablo.getTables());
    }
    
}
