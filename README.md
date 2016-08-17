# Progetto-I
Ordinazioni al ristorante

I casi d'uso si trovano nella sezione wiki.

##Cosa fare ancora?
- [x] creare UI cuoco
- [x] sistemare UI cuoco con ListCellRender "custom"
- [x] implementare database
- [ ] UI della cassa
- [x] UI di login
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
4. UI di login
  * implementata gestione login utenti
  * implementata classe per controllare i dati immessi per il login
  * rimosso il jpanel della cassa e relativa classe test, in attesa di commit siccome è stata implementata senza builder

###Problemi ultimo commit
* Il database va popolato. Il metodo per caricare da file ed aggiungere entrate al database è pronto, va solo testato.
* Non ho ancora testato la classe di login UI (la grafica funziona) però *DOVREBBE* funzionare
* Vanno rivisti i try catch, le eccezioni sono gestite abbastanza a caso
* Da finire l'utility per il proprietario del ristorante che registra nuovi utenti nel database (vanno aggiunti i listener dei pulsanti)

##Usare il database (Win)
per utilizzare il database su windows ho dovuto installare XAMPP in modo da avviare Apache sul pc stesso altrimenti netbeans continua a dare un errore di connessione (0 packet sent). Ho lasciato volontariamente bianca la password per il root user.

Ho aggiunto la libreria SQLDriver che Netbeans non aggiunge (sembra sia un bug della IDE) pur importando nel progetto (click dx su libraries).

