/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import UIlogin.*;
import java.io.IOException;
import utils.Restaurant;

/**
 *
 * @author Luca
 */
public class TestLoginUI {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Restaurant r = new Restaurant("Something",4,"./data/menu.txt");
        LoginUI login = new LoginUI(r.getRestaurantName());
    }
    
}
