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
     * @param scontrino scontrino
     * @see UIcassa.ScontrinoPanel
     * @return prezzo scontato 
     */
    public Double getDiscountedPrice(ScontrinoPanel scontrino);
    
    /**
     * Imposta i parametri presenti nella grafica degli sconti che ti servono per il calcolo dello sconto.
     * Gli attributi potrebbero essere lo sconto o la soglia che devi raggiungere per avere lo sconto
     * @param attribute1 attributo 1
     * @param attribute2  attributo 2
     */
    public void setAttributes(int attribute1,int attribute2);
    
    /**
     * Ritorna gli attributi impostati
     * @return attribute1
     */
    public String getAttributeString();
}
