package tg;

import utils.Emoji;

/**
 * testi in output del bot
 * @author Luca
 */
public class MessageStrings{
    public static String startText = "Ciao! Benvenuto nel ristorante.\nCon me potrai prenotare un tavolo al ristorante! "+Emoji.SMILING_FACE_WITH_OPEN_MOUTH;
    public static String privateText = "Questo è il pannello *gestione*.\nSe hai bisogno di una spiegazione dei comandi, premi il tasto _help_.";
    public static String endText = "Speriamo di rivederti presto! "+Emoji.SMILING_CAT_FACE_WITH_OPEN_MOUTH;
    public static String privateHelpText = "_Elimina_: disabilita un impiegato all' uso di questo bot.\n\n_Registra_: abilita un impiegato ad usare il bot e quindi di poter usare telegram per lavorare\n\n_ID_: ottieni il tuo id, o di un messaggio qui inoltrato; usa questo comando rispondendo al messaggio, è utile se devi registrare un nuovo utente\n\n_Back_: esci dal pannello. Per rientrare usa ancora /start .";
    public static String usersText = "Ciao! Benvenuto nel ristorante.\nCon me potrai prenotare un tavolo al ristorante! "+Emoji.SMILING_FACE_WITH_OPEN_MOUTH;
    public static String publicHelpText = "_Menu_: mostra il menu del ristorante\n\n_Aggiungi prenotazione_: prenota un posto al ristorante\n\n:_Cancella prenotazione_: cancella la tua prenotazione\n\n_Help_: mostra questo testo";
}
