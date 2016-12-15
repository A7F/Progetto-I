package settingsUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import utils.AppConfig;

/**
 * pannello di grafica
 * @author Luca
 */
public class SettingsPanel extends JPanel{
    
    JComboBox tableBox;
    JLabel tableNumber,menuPath,restaurantName,dialog;
    JPanel formatPane = new JPanel();
    JTextField field = new JTextField();
    JTextField file = new JTextField();
    final JFileChooser fc = new JFileChooser();
    JButton browse,apply;
    AppConfig config;
    
    public SettingsPanel(AppConfig conf){
        this.config=conf;
        initGraphics();
        attachListeners();
        this.setLayout(new GridLayout(4,2,5,15));    //righe, colonne,hgap,vgap
        this.add(tableNumber);
        this.add(tableBox);
        this.add(restaurantName);
        this.add(field);
        this.add(menuPath);
        this.add(formatPane);
        this.add(apply);
    }
    
    private void initGraphics(){
        file.setPreferredSize(new Dimension(250, 50));
        file.setText(config.getMenuPath());
        file.setEditable(false);
        field.setText(config.getRestaurantName());
        
        Integer[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        formatPane.setLayout(new FlowLayout());
        tableBox = new JComboBox(numbers);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File di testo", "txt", "text");
        fc.setFileFilter(filter);
        browse = new JButton("Scegli...");
        apply = new JButton("Applica");
        tableNumber = new JLabel("Numero dei tavoli all'avvio: ");
        menuPath = new JLabel("Percorso file menu: ");
        restaurantName = new JLabel("Nome del ristorante: ");
        dialog = new JLabel("Le modifiche saranno applicate dal prossimo avvio dell' applicazione!");
        dialog.setForeground(Color.red);
        dialog.setFont(new Font("Arial", Font.ITALIC,12));
        
        formatPane.add(file);
        formatPane.add(browse);
    }
    
    private void attachListeners() {
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fc.showDialog(new JFrame(), "scegli");
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    file.setText(selectedFile.getAbsolutePath());
                    file.repaint();
                }
            }
        });
        
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                config.setRestaurantName(field.getText());
                
                if(!file.getText().isEmpty()){
                    config.setMenuPath(file.getText());
                }
                
                config.setTableNumber(tableBox.getSelectedIndex());
                config.commitChanges();
            }
        });
    }
        
}
