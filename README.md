![build engine](https://img.shields.io/badge/build%20engine-ANT-blue.svg)
![build status](https://img.shields.io/badge/build-passing-green.svg)
![jdk release](https://img.shields.io/badge/JDK-v1.8-blue.svg)
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
 - sistemata la variabile selectedIndex, che non era allineata con l'arraylist: ora indica direttamente il tavolo selezionato mantenendo la corrispondenza con l'arraylist tavoli, in modo da non far confusione
4. migliorata la grafica del gestore ristorante
 - ora può aggiornare e rimuovere entrate nelle tabelle del database
 - può applicare sconti
 - può modificare le impostazioni del programma
  
###Problemi ultimo commit
* Vanno rivisti i try catch, le eccezioni sono gestite ma non benissimo (sarebbe carino usare dei popup)
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

##Dipendenze
Questo progetto ha le seguenti dipendenze da includere in modo da eliminare ogni genere di errore:
- JDBC Drivers, usata per il database MySQL (è presente nella libreria dell' IDE)
- JSON, usata per le configurazioni del programma (disponibile [qui](http://search.maven.org/remotecontent?filepath=org/json/json/20160810/json-20160810.jar) per il download)
- telegrambots, la libreria usata per interfacciare il programma a Telegram (disponibile [qui](http://central.maven.org/maven2/org/telegram/telegrambots/2.4.4.2/telegrambots-2.4.4.2.jar))
