package ristorante;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author federicovitro
 */
public class Tavolo {
    private String nomeCliente;
    private int numero, posti;
    private Boolean prenotato;

    public Tavolo(String nomeCliente, int numero, int posti) {
        this.nomeCliente = nomeCliente;
        this.numero = numero;
        this.posti = posti;
        prenotato = false;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        prenotato = true;
    }

    public int getNumero() {
        return numero;
    }

//    public void setNumero(int numero) {
//        this.numero = numero;
//    }

    public int getPosti() {
        return posti;
    }

//    public void setPosti(int posti) {
//        this.posti = posti;
//    }

    public Boolean getPrenotato() {
        return prenotato;
    }

//    public void setPrenotato(Boolean prenotato) {
//        this.prenotato = prenotato;
//    }
    
    @Override
    public String toString(){
        return "Prenotazione: "+nomeCliente+" / numero: "+numero+" / posti: "+posti+"\n";
    }
    
}
