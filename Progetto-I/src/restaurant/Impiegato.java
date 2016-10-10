package restaurant;

import utils.Ruoli;

/**
 * classe che modellizza la figura dell' impiegato di ristorante, di qualunque tipologia esso sia
 * @author Luca
 */
public class Impiegato {
    private int id = 0;
    private String username = "";
    private String password = "";
    private Ruoli ruolo;
    private boolean status;

    public Impiegato(){}
    
    public Impiegato(int id,String username,Ruoli ruolo,boolean status,String password){
        this.ruolo=ruolo;
        this.id=id;
        this.password=password;
        this.status=status;
        this.username=username;
    }
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Ruoli getRuolo() {
        return ruolo;
    }

    public boolean isStatus() {
        return status;
    }
    
}
