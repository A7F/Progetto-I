/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.ArrayList;

/**
 *
 * @author federicovitro
 */
public class TestMenu {
    public static void main(String[] args) {
        ArrayList<String> menu = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            menu.add("Cibo"+i+"\n");  
        }
        
        System.out.println(menu);
    }
}
