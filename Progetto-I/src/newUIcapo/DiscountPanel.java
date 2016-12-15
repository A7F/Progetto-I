package newUIcapo;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * Pannelo per inserire il valore dello sconto
 * @author Fabio
 */
public class DiscountPanel extends JPanel {

    private JLabel discountLabel;
    private JLabel percentualeLabel;
    private JSpinner spinnerDiscount;

    public DiscountPanel() {
        intComponent();
    }

    private void intComponent() {

        discountLabel = new JLabel("Sconto: ");
        percentualeLabel = new JLabel("%");

        SpinnerModel spinnerDiscountModel = new SpinnerNumberModel(0, 0, 100, 5);
        spinnerDiscount = new JSpinner(spinnerDiscountModel);
        ((DefaultEditor) spinnerDiscount.getEditor()).getTextField().setEditable(false);
        spinnerDiscount.setFont(new Font("Arial",Font.BOLD,50));
        
        this.setLayout(new FlowLayout());
        this.add(discountLabel);
        this.add(spinnerDiscount);
        this.add(percentualeLabel);
    }

    /**
     * Ritorna il valore dello sconto inserito nella casella di testo 
     * @return valore mostrato nello spinner
     * @author Fabio
     */
    public int getSpinnerDiscount() {
        return (Integer) spinnerDiscount.getValue();
    }
}
