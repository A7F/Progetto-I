package tg;

import java.util.ArrayList;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import utils.Emoji;

/**
 * tastiera riserva al solo capo del ristorante: permette di registrare o cancellare utenti
 * per abilitarli o meno all' uso del bot.
 * @author Luca
 */
public class PrivateKeyboard extends ReplyKeyboardMarkup{
    
    public PrivateKeyboard(){
        this.setResizeKeyboard(true);
        this.setSelective(Boolean.TRUE);
        this.setOneTimeKeyboad(Boolean.TRUE);
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
            firstRow.add(BotCommands.pvtDelete);
            firstRow.add(BotCommands.pvtRegister);
        KeyboardRow secondRow = new KeyboardRow();
            secondRow.add(BotCommands.pvtID);
            secondRow.add(BotCommands.aiuto);
            secondRow.add(BotCommands.pvtBack);
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        this.setKeyboard(keyboard);
    }
}
