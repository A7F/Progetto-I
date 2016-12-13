/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcassa;

/**
 * Astrazione sui vari tipi di sconto
 * @author Fabio
 */
public interface IDiscountStrategy {
    
    /**
     * Calcola il prezzo totale scontato
     * @param scontrino
     * @see ScontrinoPanel
     * @return 
     */
    public Double getDiscountedPrice(ScontrinoPanel scontrino);
    
    /**
     * Imposta i parametri da utilizzare nel calcolo dello sconto
     * @param attribute1
     * @param attribute2 
     */
    public void setAttributes(int attribute1,int attribute2);
    
    /**
     * Ritorna gli attributi impostati
     * @return attribute1
     */
    public String getAttributeString();
}
