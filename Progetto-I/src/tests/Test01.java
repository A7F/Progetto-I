/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import menu.Menu;
import utils.Restaurant;
import utils.Room;

/**
 * test per verificare il funzionamento della classe Restaurant
 * @author federicovitro
 */
public class Test01 {

    public static void main(String[] args) throws IOException {
        Restaurant diablo = new Restaurant("Diablo", 5, "./data/menu.txt");

   

        
        diablo.printFreeTable();
        
        diablo.setReserved(2);
        diablo.setReserved(3);
        
        diablo.printFreeTable();
        
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
