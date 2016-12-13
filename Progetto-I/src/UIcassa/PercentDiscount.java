/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcassa;

/**
 * Calcola lo sconto percentuale sul totale
 * @author Fabio
 */
public class PercentDiscount implements IDiscountStrategy{

    private int discount;
    
    @Override
    public Double getDiscountedPrice(ScontrinoPanel scontrino) { 
        double tot = scontrino.calculateCurrentTot();        
        tot = tot-(tot*discount/100);
        return tot;
    }

    @Override
    public void setAttributes(int attribute1, int attribute2) {
        this.discount = attribute1;
    }

    @Override
    public String getAttributeString() {
        return "- " + discount + " %";
    }
    
    
}
