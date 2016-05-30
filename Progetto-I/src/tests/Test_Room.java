/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import menu.Menu;
import utils.Restaurant;

/**
 * test specifico delle funzionalità della classe room
 * @author federicovitro
 */
public class Test_Room {
    public static void main(String[] args) throws IOException {
        Restaurant diablo1 = new Restaurant("diablo1", 10, "./data/menu.txt");
        Menu menu = new Menu("./data/menu.txt");
        
        
    }
}
