package tests;

import graphics.cameriere.CameriereMainFrame;
import java.io.IOException;
import menu.MenuElement;
import restaurant.Order;
import restaurant.Restaurant;

/**
 *
 * @author Fabio
 */
public class TestCameriereUI {
    
    public static void main(String[] args) throws IOException {
        
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");
        
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Descrizione.");
        MenuElement me2 = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Altra descrizione.");
        Order order = new Order(1,me, "al dente");
        Order order2 = new Order(1,me, "al dente");
        diablo.setReserved(3);
        diablo.addOrder(1, order);
        diablo.addOrder(1, order2);
        
        CameriereMainFrame cmf = new CameriereMainFrame(diablo,0);
        
    }
}
