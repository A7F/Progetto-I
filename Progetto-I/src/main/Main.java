package main;

import UIlogin.LoginUI;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import restaurant.Restaurant;
import snapshotEngine.SnapshotEngine;
import utils.AppConfig;
import utils.commonGraphics.MyFileChooser;

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
            JOptionPane.showMessageDialog(new JFrame(), "Non è stato trovato un menu. Scegline uno...", "Info", JOptionPane.INFORMATION_MESSAGE);
            MyFileChooser fc = new MyFileChooser();
            fc.show();
            if (fc.getSelected() == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                AppConfig.getInstance().setMenuPath(selectedFile.getAbsolutePath());
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Nessun menu selezionato. Il programma terminerà.", "Info", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            LoginUI login = new LoginUI(r);
        }
    }
    
}
