package utils;

/**
 * enum dei ruoli che possono essere assunti nel ristorante da un impiegato
 * @author Luca
 */
public enum Ruoli {
    CUOCO("CUOCO"),
    CAMERIERE("CAMERIERE"),
    CASSA("CASSA"),
    CAPO("CAPO");
    
    private String name;
    
    private Ruoli(String name){
        this.name=name;
    }
    
    public static Ruoli getByName(String name){
        return Ruoli.valueOf(name);
    }
}
