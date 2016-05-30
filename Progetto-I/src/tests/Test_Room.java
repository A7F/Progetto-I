/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import menu.Menu;
import utils.Order;
import utils.Restaurant;

/**
 * test specifico delle funzionalità della classe room
 * @author federicovitro
 */
public class Test_Room {
    public static void main(String[] args) throws IOException {
        Restaurant diablo1 = new Restaurant("diablo1", 10, "./data/menu.txt");
        Menu menu = new Menu("./data/menu.txt");
        
        /**
         * 1) test del metodo addTables()
         */
        System.out.println("___________1____________\n");
        System.out.println(diablo1.getTables());
        System.out.println("------------------------");
        
        diablo1.addtables();
        System.out.println(diablo1.getTables());
        
        /**
         * 2) test del metodo setReserved()
         */
        System.out.println("___________2____________\n");
        diablo1.setReserved(3);
        diablo1.setReserved(7);
        diablo1.setReserved(10);
        diablo1.setReserved(11);
        System.out.println(diablo1.getTables());
        
        //TODO: ho riscontrato un problema. se si aggiunge un tavolo prima di
        //eseguire le prenotazioni, il metodo non scala l'aray quindi prenotando
        //il tavolo 3 viene effettuata la prenotazione al tavolo 4 ecc.
        
        diablo1.setReserved(3);
        
        /**
         * 3) test dei metodi addOrder() e getOrdersArray()
         */
        System.out.println("___________3____________\n");
        //Order o1 = new Order(2, "", notes)
        //diablo1.addOrder(1, order);
        
        /**
         * 4) test del metodo removeOrder()
         */
        System.out.println("___________4____________\n");
        
        /**
         * 5) test del metodo getFreeTables()
         */
        System.out.println("___________5____________\n");
        System.out.println(diablo1.getFreeTable());
        
        /**
         * 6) test del metodo getTakenTables()
         */
        System.out.println("___________6____________\n");
        System.out.println(diablo1.getTakenTable());
    }
}
