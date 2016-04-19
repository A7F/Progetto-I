/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import utils.Restaurant;
import utils.Room;

/**
 * test per verificare il funzionamento della classe Room
 * @author federicovitro
 */
public class Test01 {
    public static void main(String[] args) {
//        Restaurant diablo = new Restaurant("Diablo", 5);
        Room r1 = new Room(5);
        
        System.out.println(r1.getFreeTable());
        
        r1.addtables();
        
        System.out.println(r1.getFreeTable());
        
    }
}
