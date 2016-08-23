/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import menu.Menu;
import restaurant.Restaurant;

/**
 * test che verifica le funzionalità principali del progetto
 * @author federicovitro
 */
public class Test_Completo {
    public static void main(String[] args) throws IOException {
        
        Restaurant diablo = new Restaurant("Diablo", 10, "./data/menu.txt");
        
        Menu menu = new Menu("./data/menu.txt");
        
        // verifica metodo gettables()
        
        System.out.println(diablo.getTables()+"\n");
        System.out.println("_______________________\n");
        
        // verifica metodo getFreeTable() + getTakenTable() + setReserved()
        
        System.out.println(diablo.getFreeTable()+"\n");
        System.out.println("_______________________\n");
        
        diablo.setReserved(4); //TODO try catch per evitare la prenotazione di un tavolo già prenotato
        diablo.setReserved(5);
        diablo.setReserved(6);
        diablo.setReserved(9);
        
        diablo.setReserved(4);
        System.out.println("_______________________\n");
        
        System.out.println(diablo.getFreeTable()+"\n");
        System.out.println("_______________________\n");
        //System.out.println(diablo.getTakenTable()+"\n");
        System.out.println("_______________________\n");
        
        // verifica ricerca elementi nel menu per caratteristica
        //TODO mettere a posto il problema dell'arraylist
        
        System.out.println(diablo.getMenuByName("Pasta col Sugo"));
        System.out.println(diablo.getMenuByName("banana")); 
        System.out.println("_______________________\n");
        
        System.out.println(diablo.getElementByPrice(1.0));
        System.out.println(diablo.getElementByPrice(60)); 
        System.out.println("_______________________\n");
        
        System.out.println(diablo.getElementByType("PRIMO"));
        
        
    }
}
