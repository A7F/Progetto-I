package tests;

import UICassa.CassaMainFrame;
import java.io.IOException;
import menu.MenuElement;
import utils.Order;
import utils.Restaurant;

/**
 *
 * @author Fabio
 */
public class TestUICassa {
    
    public static void main(String[] args) throws IOException {
        
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");

        // System.out.println(diablo.getMenu());
        
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Spaghetti con carbonara, servita calda col ghiaccio e un pizzico di roccia saporita al ragno.\n");
        MenuElement me2 = new MenuElement("Pasta col Sugo", 5.5, "PRIMO", "Si può scegliere tra tortiglioni, penne e spaghetti, conditi con sugo di pomodoro fresco fatto in giornata.\n");
        
        Order order = new Order(2,me, "al dente");
        diablo.addOrder(1, order);
        diablo.addOrder(1, order);
        diablo.addOrder(1, order);
        
        Order order2 = new Order(3,me2, "al dente");
        diablo.addOrder(1, order2);
       
        System.out.println(diablo.getTables());
        CassaMainFrame cassaFrame = new CassaMainFrame(diablo);
    }
}
