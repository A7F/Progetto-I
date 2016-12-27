package restaurant;

/**
 * classe che modellizza la figura dell' impiegato di ristorante, di qualunque tipologia esso sia
 * @author Luca
 */
public class Employee {
    private int id = 0;
    private String username = "";
    private String password = "";
    private String ruolo = "";
    private boolean status;
    
    public Employee(int id,String username,String ruolo,boolean status,String password){
        this.ruolo=ruolo;
        this.id=id;
        this.password=password;
        this.status=status;
        this.username=username;
    }
    
    /**
     * @author Luca
     * @return id dell' impiegato
     */
    public int getId() {
        return id;
    }

    /**
     * @author Luca
     * @return username dell' impiegato
     */
    public String getUsername() {
        return username;
    }

    /**
     * @author Luca
     * @return ruolo dell' impiegato
     */
    public String getRuolo() {
        return ruolo;
    }

    /**
     * @author Luca
     * @return stato dell' impiegato: true se connesso.
     */
    public boolean isStatus() {
        return status;
    }
    
}
