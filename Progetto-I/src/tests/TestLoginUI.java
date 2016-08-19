package tests;

import UIlogin.*;
import java.io.IOException;
import utils.Restaurant;

/**
 * mi sembra evidente cosa faccia questa classe
 * dovrebbe diventare il nuovo main che farà partire tutto il programma
 * @author Luca
 */
public class TestLoginUI {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Restaurant r = new Restaurant("Boh",4,"./data/menu.txt");
        LoginUI login = new LoginUI(r);     //passo il ristorante appena creato in modo da poter usare getname e getordersarray
    }
    
}
