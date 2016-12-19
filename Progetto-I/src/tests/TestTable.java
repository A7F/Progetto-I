/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import menu.Menu;
import menu.MenuElement;
import static menu.MenuElementType.DOLCE;
import restaurant.Order;
import restaurant.Restaurant;

/**
 *
 * @author federicovitro
 */
public class TestTable {
    public static void main(String[] args) throws IOException {
        Restaurant diablo2 = new Restaurant("Diablo2", 5, "./data/menu.txt");
        Menu menu = new Menu("./data/menu.txt");
        
        /**
         * 1) test metodo addOrder() e getOrdersArray()
         */
        System.out.println("___________1____________\n");
        
        MenuElement e1 = new MenuElement("Banana", 1, "DOLCE", "banana con banane");
        Order o1 = new Order(2, e1);
        diablo2.addOrder(1, o1);
        
        MenuElement e2 = new MenuElement("Pasta col Sugo", 7, "PRIMO", "Si può scegliere tra tortiglioni, penne e spaghetti, conditi con sugo di pomodoro fresco fatto in giornata.");
        Order o2 = new Order(2, e2, "al dente");
        diablo2.addOrder(2, o2);
        
        System.out.println(diablo2.getOrdersArray());
        
        System.out.println("------------------------");
        
        Order o3 = new Order(3, e1);
        diablo2.addOrder(2, o3);
        
        System.out.println(diablo2.getOrdersArray());
        
        //nota: converrebbe mettere nell'oggetto Ordin, il parametro che indica
        //il numero del tavolo al quale è collegato
        
        
    }
}
