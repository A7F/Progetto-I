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

1. impostazioni del programma
 - iniziata creazione delle impostazioni del programma: creato file json e relativa classe di gestione, implementata come Singleton.
2. iniziata stesura javadoc
 - javadoc inserita ma da perfezionare
3. bugfix vari
 - sistemato l'outOfBoundsException che avrebbe terminato inaspettatamente il programma al tentativo di eliminare l'ultimo tavolo, corrispondente a quello selezionato
 - la rimozione di un tavolo occupato richiede una ulteriore conferma tramite popup di warning
4. migliorata la grafica del gestore ristorante
 - ora può aggiornare e rimuovere entrate nelle tabelle del database
 - può applicare sconti
  
###Problemi ultimo commit
* Vanno rivisti i try catch, le eccezioni sono gestite ma non benissimo (sarebbe carino usare dei popup)
* Va terminata la gestione impostazioni del programma, da rendere disponibili al capo tramite menu (si deve inserire l'entrata nella JMenuBar)
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
