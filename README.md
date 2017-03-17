![build engine](https://img.shields.io/badge/build%20engine-ANT-blue.svg)
![build status](https://img.shields.io/badge/build-passing-green.svg)
![jdk release](https://img.shields.io/badge/JDK-v1.8-blue.svg)
![hibernate version](https://img.shields.io/badge/Hibernate-4.3.x-blue.svg)
# Progetto-I
Ordinazioni al ristorante

Per favore fate riferimento alla sezione wiki per qualsiasi cosa. Non prevedo di proseguire lo sviluppo di questa applicazione ma chissà che un giorno possa decidermi di farne qualcosa di ben più serio... ad ogni modo sono disposto a corregger eventuali problemi e rispondere a domande eventuali.

## Cosa contiene l'ultimo commit?

1. impostazioni del programma
 - create le impostazioni del programma impostabili dal capo del ristorante
2. terminata stesura javadoc
 - javadoc finita e revisionata
3. migliorata la grafica del gestore ristorante
 - ora può aggiornare e rimuovere entrate nelle tabelle del database
 - può applicare sconti
 - può modificare le impostazioni del programma
4. Finalmente, Hibernate!
 - implementato uso di hibernate per gestire in modo Object Oriented gli snapshot del programma
 - il programma adesso può salvare il suo stato (scattando snapshot) in modo da ripristinarsi in caso di errori fatali
 - il programma riconosce quando è avvenuto un errore fatale e si ripristina automaticamente
  
### Problemi ultimo commit
* resta un problema di renderizzazione grafica non uniforme su tutte le piattaforme

## Usare il database (Win+Mac)
per utilizzare il database su windows ho dovuto installare XAMPP in modo da avviare Apache sul pc stesso altrimenti netbeans continua a dare un errore di connessione (0 packet sent). Ho lasciato volontariamente bianca la password per il root user.

Ho aggiunto la libreria SQLDriver che Netbeans non aggiunge (sembra sia un bug della IDE) pur importando nel progetto. Per risolvere:
* click dx su libraries (quelle del progetto)
* add library
* selezionare *MySQL JDBC Driver*

La procedura è la stessa su Mac, siccome XAMPP è ugualmente disponibile.
Ovviamente, essendo il database locale, le modifiche non sono sincronizzate con la repository github quindi "ognuno ha il suo". Ciò non toglie che si potrebbe cambiare l'indirizzo del database in modo da renderlo remoto ma non è una necessità di questa applicazione.

##Struttura database
Rimando alla relativa [pagina wiki](https://github.com/claudio-unipv/Progetto-I/wiki/Database-SQL) per la descrizione della struttura del database

## Dipendenze
Questo progetto ha le seguenti dipendenze da includere in modo da eliminare ogni genere di errore:
- Hibernate 4.3.x (la libreria è inclusa nell' IDE)
- JDBC Drivers, usata per il database MySQL (la libreria è inclusa nell' IDE)
- JSON, usata per le configurazioni del programma (disponibile [qui](http://search.maven.org/remotecontent?filepath=org/json/json/20160810/json-20160810.jar) per il download)
- telegrambots, la libreria usata per interfacciare il programma a Telegram (disponibile [qui](http://central.maven.org/maven2/org/telegram/telegrambots/2.4.4.2/telegrambots-2.4.4.2.jar))
