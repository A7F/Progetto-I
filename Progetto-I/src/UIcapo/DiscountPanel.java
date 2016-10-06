package UIcapo;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
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

        this.setLayout(new FlowLayout());
        this.add(discountLabel);
        this.add(spinnerDiscount);
        this.add(percentualeLabel);
    }

    /**
     * Ritorna il valore dello sconto inserito nella casella di testo 
     * @return 
     * @author Fabio
     */
    public int getSpinnerDiscount() {
        return (Integer) spinnerDiscount.getValue();
    }
}
