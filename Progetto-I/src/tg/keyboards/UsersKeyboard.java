package tg.keyboards;

import java.util.ArrayList;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import tg.BotCommands;

/**
 * tastiera disponibile a tutti gli utenti che accedono al ristorante
 * @author Luca
 */
public class UsersKeyboard extends ReplyKeyboardMarkup{
    
    public UsersKeyboard(){
        this.setResizeKeyboard(true);
        this.setSelective(Boolean.TRUE);
        this.setOneTimeKeyboad(Boolean.TRUE);
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
            firstRow.add(BotCommands.usersAddOrder);
            firstRow.add(BotCommands.usersRemOrder);
        KeyboardRow secondRow = new KeyboardRow();
            secondRow.add(BotCommands.usersGetMenu);
            secondRow.add(BotCommands.aiuto);
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        this.setKeyboard(keyboard);
    }
}
