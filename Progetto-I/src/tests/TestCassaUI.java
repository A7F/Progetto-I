package tests;

import graphics.cassa.CassaMainFrame;
import java.io.IOException;
import menu.MenuElement;
import restaurant.Order;
import restaurant.Restaurant;

/**
 *
 * @author Fabio
 */
public class TestCassaUI {
    
    public static void main(String[] args) throws IOException {
        
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");
        
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Spaghetti con uovo.\n");
        MenuElement me2 = new MenuElement("Pasta col Sugo", 5.5, "PRIMO", "Possibili più scelte.\n");
        
        Order order = new Order(2,me, "al dente");
        diablo.addOrder(1, order);
        diablo.addOrder(1, order);
        diablo.addOrder(1, order);
        
        Order order2 = new Order(3,me2, "al dente");
        diablo.addOrder(1, order2);
        
        CassaMainFrame cassaFrame = new CassaMainFrame(diablo,0);
    }
}