package tests;

import java.io.IOException;
import menu.*;
import restaurant.Restaurant;

/**
 * test per verificare il funzionamento della classe Restaurant
 * @author federicovitro
 */
public class Test01 {

    public static void main(String[] args) throws IOException {
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");

        Menu menu = new Menu("./data/menu.txt");
        
        
        System.out.println(diablo.getTables());
        
        diablo.setReserved(2);
        diablo.setReserved(3);
        
        System.out.println("------------------- TESTING PRO -----------------\n");
        
        diablo.setReserved(3);
        
        //System.out.println(diablo.getFreeTable());
        
        //System.out.println(menu.toString());
        
        //System.out.println(diablo.getMenuByName("Banana"));
        
        //System.out.println(diablo.getElementByType("primo"));
        
//        Room r1 = new Room(5);
//        
//        System.out.println(r1.getFreeTable());
//        
//        r1.addtables();
        
//        System.out.println(r1.getFreeTable());


//QUA FACCIO IL TEST DEL SOLO MENU
//enu menu = new Menu("C:\\Users\\Javier\\Google Drive\\UNIVERSITA\'\\Fondamenti di informatica II\\ingegneria del software\\Progetto I\\menu.txt");
//System.out.println(menu.toString());



    }
}
