package UIcassa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * finestra per aggiungere e, prossimamente, controllare le ordinazioni ricevute telefonicamente
 * @author Luca
 */
public class PrenotazioneWindow extends JFrame{
    
    JLabel nameLabel = new JLabel("Nome: ");
    JTextField fieldName = new JTextField();
    JLabel oreLabel = new JLabel("Ore: ");
    JLabel dateLabel = new JLabel("Giorno: ");
    JLabel peopleLable = new JLabel("Persone: ");
    JButton addButton = new JButton("ADD");
    JButton undoButton = new JButton("UNDO");
    GridLayout myLayout = new GridLayout(5,2);
    JPanel nuovaPrenotazione = new JPanel();
    JPanel hoursPanel = new JPanel();
    JPanel daysPanel = new JPanel();
    JSpinner spinnerHours,spinnerMinutes,spinnerDay,spinnerMonth;
    
    public PrenotazioneWindow(){
        this.setTitle("Prenotazione");
        init();
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /** 
     * avvia la grafica
     * @author Luca
     */
    private void init() {
        myLayout.setVgap(3);
        myLayout.setHgap(4);
        nuovaPrenotazione.setLayout(myLayout);
        setSpinners();
        addInGrid();
    }

    /**
     * popola la griglia che fisserà il layout, con i vari elementi
     * @author Luca
     */
    private void addInGrid() {
        nuovaPrenotazione.add(nameLabel);
        nuovaPrenotazione.add(fieldName);
        
        nuovaPrenotazione.add(oreLabel);
        nuovaPrenotazione.add(hoursPanel);
        nuovaPrenotazione.add(dateLabel);
        nuovaPrenotazione.add(daysPanel);
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("esegui qui la query per il database");
            }
        });
        
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Chiudi la finestra");
            }
        });
        
        nuovaPrenotazione.add(addButton);
        nuovaPrenotazione.add(undoButton);
        this.add(nuovaPrenotazione);
    }

    /**
     * metodo che imposta le priprietà di tutti gli spinner presenti nellafinestra
     * @author Luca
     */
    private void setSpinners() {
        GridLayout spinnersLayout = new GridLayout(1,2);
        spinnersLayout.setHgap(3);
        hoursPanel.setLayout(spinnersLayout);
        daysPanel.setLayout(spinnersLayout);
        
        SpinnerModel spinnerHoursModel = new SpinnerNumberModel(0,0,24,1);
        spinnerHours = new JSpinner(spinnerHoursModel);
        
        SpinnerModel spinnerMinutesModel = new SpinnerNumberModel(0,0,60,5);
        spinnerMinutes = new JSpinner(spinnerMinutesModel);
        
        SpinnerModel spinnerDaysModel = new SpinnerNumberModel(1,1,31,1);
        spinnerDay = new JSpinner(spinnerDaysModel);
        
        SpinnerModel spinnerMonthsModel = new SpinnerNumberModel(1,1,12,1);
        spinnerMonth = new JSpinner(spinnerMonthsModel);
        
        hoursPanel.add(spinnerHours);
        hoursPanel.add(spinnerMinutes);
        daysPanel.add(spinnerDay);
        daysPanel.add(spinnerMonth);
    }
}
