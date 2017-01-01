package UIsettings;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import utils.AppConfig;

/**
 * frame principale per la gestione delle impostazioni del programma; sarà
 * disponibile solo al gestore del ristorante.
 * @author Luca
 */
public class SettingsMainFrame extends JFrame{
    AppConfig config;
    
    /**
     * costruttore a cui passare il singleton della classe di gestione impostazioni
     * @param conf istanza di AppConfig
     * @author Luca
     */
    public SettingsMainFrame(AppConfig conf){
        this.config=conf;
        this.setTitle("Impostazioni");
        init();
        this.setSize(new Dimension(710,320));
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    /**
     * inizializzazione degli elementi grafici
     * @author Luca
     */
    private void init(){
        SettingsPanel panel = new SettingsPanel(config);
        this.add(panel);
    }
}
