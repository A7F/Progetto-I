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
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRuolo() {
        return ruolo;
    }

    public boolean isStatus() {
        return status;
    }
    
}
