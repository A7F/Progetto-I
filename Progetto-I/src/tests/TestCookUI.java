package tests;

import UIcook.CookUI;
import java.io.IOException;
import java.util.ArrayList;
import menu.MenuElement;
import utils.Order;
import utils.Restaurant;

/**
 *
 * @author Luca
 */
public class TestCookUI {
    public static void main(String[] args) throws IOException{
        
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");
        
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Descrizione.");
        MenuElement me2 = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Altra descrizione.");
        Order order = new Order(1,me, "al dente");
        Order order2 = new Order(1,me, "al dente");
        diablo.addOrder(1, order);
        diablo.addOrder(1, order2);
        
        CookUI ui = new CookUI(diablo.getOrdersArray());
    }
}