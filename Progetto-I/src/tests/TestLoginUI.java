package tests;

import UIlogin.*;
import java.io.IOException;
import menu.MenuElement;
import restaurant.Order;
import restaurant.Restaurant;

/**
 * classe di test globale del funzionamento del programma.
 * dovrebbe diventare il nuovo main che farà partire tutto il programma.
 * @author Luca
 */
public class TestLoginUI{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Restaurant r = new Restaurant("Boh",4,"./data/menu.txt");
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Descrizione.");
        MenuElement me2 = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Altra descrizione.");
        Order order = new Order(1,me, "al dente");
        Order order2 = new Order(1,me2, "al dente");
        r.addOrder(1, order);
        r.addOrder(1, order2);
        LoginUI login = new LoginUI(r);     //passo il ristorante appena creato in modo da poter usare getname e getordersarray
    }
}