package menu;

public class MenuElement {
    private String name;
    private double price;
    private Boolean available;
    private String typeElement;
    private String description;
    
    public MenuElement(String name, double price, String typeElement, String description){
        this.name = name;
        this.price = price;
        this.typeElement = typeElement;
        this.description = description;
        available = true;
    }
    
    @Override
    public String toString(){
        return name+" "+price + " " + description + " " + available;
    }
    
    /**
     * Ritorna il nome dell' elemento di menu (=nome del piatto)
     * @author Luca
     * @return String typeElement
     */
    public String getNameElement(){
        return name;
    }
    
    /**
     * metodo per ottenere il tipo di elemento (primo, secondo, dolce, bevanda)
     * @author Luca
     * @return  tipo dell' elemento
     */
    public String getTypeElement(){
        return typeElement;
    }
    
    /**
     * metodo per ottenere il prezzo dell' elemento.
     * @return  prezzo dell' elemento
     * @author Luca
     */
    public double getPrizeElement(){
        return price;
    }
    
    /**
     * metodo per ottenere la descrizione dell' elemento
     * @author Luca
     * @return   descrizione dell' elemento
     */
    public String getDescription(){
        return description;
    }
    
    
}
