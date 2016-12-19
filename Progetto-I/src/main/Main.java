package main;

import UIlogin.LoginUI;
import java.io.IOException;
import restaurant.Restaurant;
import utils.AppConfig;

/**
 * classe main dell'applicazione
 * @author Luca
 */
public class Main {

    public static void main(String[] args) {
        Restaurant r = null;
        try {
            r = new Restaurant(AppConfig.getInstance().getRestaurantName(),AppConfig.getInstance().getTableNumber(),AppConfig.getInstance().getMenuPath());
            LoginUI login = new LoginUI(r);
        } catch (IOException ex) {
            LoginUI login = new LoginUI(r);
        }
    }
    
}
