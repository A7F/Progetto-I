package main;

import UIlogin.LoginUI;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import restaurant.Restaurant;
import snapshotEngine.SnapshotEngine;
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
            if(AppConfig.getInstance().getExitState()!=0){
                SnapshotEngine engine = SnapshotEngine.getInstance();
                engine.restore();
                LoginUI login = new LoginUI(r);
            }else{
                SnapshotEngine engine = SnapshotEngine.getInstance();
                engine.setRestaurant(r);
                engine.startDaemon();
                LoginUI login = new LoginUI(r);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Non è stato trovato un menu. Accedere con le credenziali del capo e specificare il percorso.", "Info", JOptionPane.INFORMATION_MESSAGE);
            LoginUI login = new LoginUI(r);
        }
    }
    
}
