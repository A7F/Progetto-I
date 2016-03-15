/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.ArrayList;
import ristorante.Tavolo;

/**
 *
 * @author federicovitro
 */
public class TestTavoli {
    public static void main(String[] args) {
        ArrayList<Tavolo> tavoli = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            tavoli.add(new Tavolo("Cliente"+i, i+1, 3));
        }
        
        System.out.println(tavoli);
    }
}
