package tg;

import java.util.ArrayList;
import menu.MenuElement;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import restaurant.Restaurant;
import utils.Emoji;

/**
 * tastiera riservata agli utenti del ristorante che hanno fatto almeno un ordine; questo
 * per consentire l'uso ai soli clienti.
 * @author Luca
 */
public class OrdersKeyboard extends ReplyKeyboardMarkup{
    
    Restaurant restaurant;
    
    public OrdersKeyboard(Restaurant res){
        this.restaurant=res;
        this.setResizeKeyboard(true);
        this.setSelective(Boolean.TRUE);
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        
        ArrayList<MenuElement> elements = restaurant.getMenu();
        for(int i=0;i<elements.size();i++){
            KeyboardRow firstRow = new KeyboardRow();
            firstRow.add(elements.get(i).getNameElement());
            keyboard.add(firstRow);
        }
        
        KeyboardRow lastRow = new KeyboardRow();
            lastRow.add("Invia ed esci");
        keyboard.add(lastRow);
        
        this.setKeyboard(keyboard);
    }
}
