# Progetto-I
Ordinazioni al ristorante

I casi d'uso si trovano nella sezione wiki.

##Cosa fare ancora?
- [x] creare UI cuoco
- [x] sistemare UI cuoco con ListCellRender "custom"
- [x] implementare database
- [x] UI della cassa
- [x] UI di login
- [x] UI del cameriere
- [ ] gestire eccezioni
- [ ] refactoring finale

##Cosa contiene l'ultimo commit?

1. UI cameriere
  * aggiunta gestione degli ordini ai tavoli: possibilità di aggiunta e rimozione ordine
2. Sincronizzazione fra cameriere e cuoco
3. Utility di gestione table Tavoli del database
  * aggiunti metodi di utility in metodi della classe Ristorante: aggiungendo o togliendo un tavolo si aggiornerà automaticamente anche la tabella Tavoli
  
###Problemi ultimo commit
* Vanno rivisti i try catch, le eccezioni sono gestite ma non benissimo (sarebbe carino usare dei popup)
* L'utility per il proprietario del ristorante che registra nuovi utenti nel database va testata
* La UI del cameriere andrebbe resa più guardabile aggiungendo anche le info riguardo l'elemento menu selezionato

##Usare il database (Win)
per utilizzare il database su windows ho dovuto installare XAMPP in modo da avviare Apache sul pc stesso altrimenti netbeans continua a dare un errore di connessione (0 packet sent). Ho lasciato volontariamente bianca la password per il root user.

Ho aggiunto la libreria SQLDriver che Netbeans non aggiunge (sembra sia un bug della IDE) pur importando nel progetto. Per risolvere:
* click dx su libraries (quelle del progetto)
* add library
* selezionare *MySQL JDBC Driver*

Ovviamente, essendo il database locale, le modifiche non sono sincronizzate con la repository github quindi "ognuno ha il suo".

##Struttura database
Rimando alla relativa [pagina wiki](https://github.com/claudio-unipv/Progetto-I/wiki/Database-SQL) per la descrizione della struttura del database
