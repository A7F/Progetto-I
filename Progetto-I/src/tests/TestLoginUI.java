package tests;

import UIlogin.*;
import java.io.IOException;
import utils.Restaurant;

/**
 * mi sembra evidente cosa faccia questa classe
 * @author Luca
 */
public class TestLoginUI {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Restaurant r = new Restaurant("Boh",4,"./data/menu.txt");
        LoginUI login = new LoginUI(r.getRestaurantName());     //qui va passato il ristorante appena creato in modo da poter usare getname e getordersarray
    }
    
}
