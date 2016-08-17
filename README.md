# Progetto-I
Ordinazioni al ristorante

I casi d'uso si trovano nella sezione wiki.

##Cosa fare ancora?
- [x] creare UI cuoco
- [x] sistemare UI cuoco con ListCellRender "custom"
- [x] implementare database
- [ ] UI della cassa
- [ ] UI del cameriere
- [ ] gestire eccezioni
- [ ] refactoring finale

##Cosa contiene l'ultimo commit?
1. implementato il database
  * creata classe di gestione collegamento
  * aggiunte configurazioni per eseguire database in localhost
  * aggiunti metodi di gestione database
2. iniziato design UI di cassa
  * creato jpanel con UI builder di netbeans
3. UI cuoco
  * finita l'implementazione
  * sistemato il customListCellRenderer
  * creata classe di test

###Problemi ultimo commit
* Il database va popolato. Il metodo per caricare da file ed aggiungere entrate al database è pronto, va solo testato.
* Forse non è stata una buona idea fare un jpanel della cassa con builder netbeans, siccome (pare) non consenta di gestire gli scrollpanel senza rendere caotico il codice

##Usare il database (Win)
per utilizzare il database su windows ho dovuto installare XAMPP in modo da avviare Apache sul pc stesso altrimenti netbeans continua a dare un errore di connessione (0 packet sent). Ho lasciato volontariamente bianca la password per il root user.

Ho aggiunto la libreria SQLDriver che Netbeans non aggiunge (sembra sia un bug della IDE) pur importando nel progetto (click dx su libraries).

