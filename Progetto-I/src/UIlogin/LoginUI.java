package UIlogin;

import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import utils.LoginManager;
import restaurant.Restaurant;

/**
 * questa classe avvia il login al programma: accesso con scelta fra tipi di impiegati
 * @author Luca
 */
public class LoginUI {
    
    String name = new String();
    Restaurant restaurant;
    LoginManager manager;
    ButtonLayout buttons;
    JFrame frame;
    
    public LoginUI(Restaurant res){
        this.restaurant = res;
        this.manager = new LoginManager(restaurant);
        this.name = res.getRestaurantName();
        initGraphics();
    }
    
    /**
     * metodo per avviare la grafica di login
     * @author Luca
     */
    private void initGraphics(){
        frame = new JFrame("Ristorante "+name);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();    //serve per centrare allo schermo
        
        MidPanel panel = new MidPanel();
        buttons = new ButtonLayout(panel.getRadioPanel(),panel.getFormPanel(),manager);
        
        frame.setLayout(new BorderLayout());        
        frame.add(panel,BorderLayout.CENTER);
        frame.add(buttons,BorderLayout.SOUTH);
        
        frame.setVisible(true);
        frame.setSize(380,250);  
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        
        checkFlagLogin();
    }
    
    /**
     * metodo per chiudere la finestra di login quando l'accesso viene confermato con successo
     * @author Luca
     */
    protected void checkFlagLogin(){
        while(buttons.getFlag()==false){}
        frame.dispose();
    }
}
