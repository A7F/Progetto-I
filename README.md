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

1. UI cassa
  * migliorata la grafica della cassa che ora rispecchia il caso d'uso
2. Sincronizzazione fra cameriere, cuoco e cassa
3. UI capo
  * aggiunta la possibilità di applicare uno sconto sul totale
4. Bugfix delle query e degli update sul database
  * migliorata utility di creazione del database e di popolazione automatica delle table
5. Iniziata stesura della javadoc
  
###Problemi ultimo commit
* Vanno rivisti i try catch, le eccezioni sono gestite ma non benissimo (sarebbe carino usare dei popup)
* L'utility per il proprietario del ristorante che registra nuovi utenti nel database va testata
* Sistemare gestione dei tavoli in grafiche
* Javadoc da terminare e da revisionare

##Usare il database (Win+Mac)
per utilizzare il database su windows ho dovuto installare XAMPP in modo da avviare Apache sul pc stesso altrimenti netbeans continua a dare un errore di connessione (0 packet sent). Ho lasciato volontariamente bianca la password per il root user.

Ho aggiunto la libreria SQLDriver che Netbeans non aggiunge (sembra sia un bug della IDE) pur importando nel progetto. Per risolvere:
* click dx su libraries (quelle del progetto)
* add library
* selezionare *MySQL JDBC Driver*

La procedura è la stessa su Mac, siccome XAMPP è ugualmente disponibile.
Ovviamente, essendo il database locale, le modifiche non sono sincronizzate con la repository github quindi "ognuno ha il suo".

##Struttura database
Rimando alla relativa [pagina wiki](https://github.com/claudio-unipv/Progetto-I/wiki/Database-SQL) per la descrizione della struttura del database
