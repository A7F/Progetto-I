package tg;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import restaurant.Restaurant;
import utils.Emoji;

/**
 *
 * @author Luca
 */
public class BotHandler extends TelegramLongPollingBot{

    Restaurant r;
    
    public BotHandler(Restaurant diablo) {
        this.r=diablo;
    }

    @Override
    public String getBotToken(){
        return BotConfig.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update){
        Message message=update.getMessage();
        
        if (update.hasMessage()){
            if(message.getText().equals(BotCommands.init)){
                SendMessage messageRequest = new SendMessage();
                messageRequest.setText(MessageStrings.startText).setChatId(message.getChatId().toString());
                if(message.getFrom().getId()==BotConfig.BOT_MASTER){
                    messageRequest.setText(MessageStrings.privateText).setChatId(message.getChatId().toString());
                    messageRequest.setReplyMarkup(new PrivateKeyboard());
                }else{
                    messageRequest.setText(MessageStrings.startText).setChatId(message.getChatId().toString());
                    messageRequest.setReplyMarkup(new OrdersKeyboard(r));
                }
                
                try {
                    sendMessage(messageRequest);
                } catch (TelegramApiException ex) {
                    System.out.println("errore");
                }
            }
            
            if(message.getText().equals(BotCommands.pvtID)){
                if(message.getFrom().getId()==BotConfig.BOT_MASTER){
                    SendMessage messageRequest = new SendMessage();
                    String text1 = "_Il tuo ID: _"+message.getFrom().getId()+"\n_Ti chiami: _"+message.getFrom().getFirstName()+"\n_Username: _"+message.getFrom().getUserName();
                    String text2 = "\n_Gruppo: _"+message.isGroupMessage()+"\n_Supergruppo: _"+message.isSuperGroupMessage()+"\n_User: _"+message.isUserMessage();
                    String text3 = "\n_Data messaggio: _"+message.getDate()+"\n_Id messaggio: _"+message.getMessageId();
                    String text = text1+text2+text3;
                    messageRequest.setText(text).setChatId(message.getChatId().toString());
                    messageRequest.enableMarkdown(true);
                    try {
                        sendMessage(messageRequest);
                    } catch (TelegramApiException ex) {
                        Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            if(message.getText().equals(BotCommands.usersGetMenu)){
                SendMessage messageRequest = new SendMessage();
                StringBuilder str = new StringBuilder();
                String headMenu = "*Menu del ristorante "+r.getRestaurantName()+"*\n\n";
                for(int i=0;i<r.getMenu().size();i++){
                    str.append(r.getMenu().get(i).getPrizeElement()).append("    ").append(r.getMenu().get(i).getNameElement()).append("\n").append("_").append(r.getMenu().get(i).getDescription()).append("_").append("\n\n");
                }
                messageRequest.setText(headMenu+str.toString()).setChatId(message.getChatId().toString());
                messageRequest.enableMarkdown(true);
                try {
                    sendMessage(messageRequest);
                } catch (TelegramApiException ex) {
                    Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(message.getText().equals(BotCommands.pvtBack)){
                SendMessage messageRequest = new SendMessage();
                messageRequest.setText(MessageStrings.usersText).setChatId(message.getChatId().toString());
                messageRequest.setReplyMarkup(new UsersKeyboard());
                messageRequest.enableMarkdown(true);
                try {
                    sendMessage(messageRequest);
                } catch (TelegramApiException ex) {
                    Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(message.getText().equals(BotCommands.aiuto)){
                SendMessage messageRequest = new SendMessage();
                messageRequest.enableMarkdown(true);
                if(message.getFrom().getId()==BotConfig.BOT_MASTER){
                    messageRequest.setText(MessageStrings.privateHelpText).setChatId(message.getChatId().toString());
                }else{
                    messageRequest.setText(MessageStrings.privateHelpText).setChatId(message.getChatId().toString());
                }
                try {
                    sendMessage(messageRequest);
                    } catch (TelegramApiException ex) {
                    Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }
    
}
