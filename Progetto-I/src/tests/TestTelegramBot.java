package tests;

import java.io.IOException;
import menu.MenuElement;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.logging.BotLogger;
import restaurant.Order;
import restaurant.Restaurant;
import tg.BotHandler;

/**
 * classe di test per l'integrazione con telegram
 * @author Luca
 */
public class TestTelegramBot{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException file di menu non trovato
     */
    public static void main(String[] args) throws IOException{
        String LOGTAG = "BOT >> ";
        
        Restaurant diablo = new Restaurant("Diablo", 5,"./data/menu.txt");
        
        MenuElement me = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Descrizione.");
        MenuElement me2 = new MenuElement("Pasta alla Carbonara", 7, "PRIMO", "Altra descrizione.");
        Order order = new Order(1,me, "al dente");
        Order order2 = new Order(1,me, "al dente");
        diablo.setReserved(3);
        diablo.addOrder(1, order);
        diablo.addOrder(1, order2);
        
        TelegramBotsApi tgBotApi = new TelegramBotsApi();
        try {
            tgBotApi.registerBot(new BotHandler(diablo));
        } catch (TelegramApiException ex){
            BotLogger.error(LOGTAG, ex);
        }
    }
    
}
