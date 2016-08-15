/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import UIcook.InitGraphics;
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
        
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Spaghetti con carbonara, servita calda col ghiaccio e un pizzico di roccia saporita al ragno.\n");
        MenuElement me2 = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Spaghetti con carbonara, servita calda col ghiaccio e un pizzico di roccia saporita al ragno.\n");
        Order order = new Order(1,me, "al dente");
        Order order2 = new Order(1,me, "al dente");
        diablo.addOrder(1, order);
        diablo.addOrder(1, order2);
        
        InitGraphics ui = new InitGraphics(diablo.getOrdersArray());
    }
}