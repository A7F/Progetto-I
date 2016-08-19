# Progetto-I
Ordinazioni al ristorante

I casi d'uso si trovano nella sezione wiki.

##Cosa fare ancora?
- [x] creare UI cuoco
- [x] sistemare UI cuoco con ListCellRender "custom"
- [x] implementare database
- [x] UI della cassa
- [x] UI di login
- [ ] UI del cameriere
- [ ] gestire eccezioni
- [ ] refactoring finale

##Cosa contiene l'ultimo commit?
1. UI di cassa
  * aggiunta la UI della cassa *(by Fabio)*
2. UI di login
  * implementata gestione login utenti
  * implementata classe per controllare i dati immessi per il login
  * aggiunta gestione eccezioni (da finire)
  * unificate tutte le grafiche in modo da gestirle con un solo metodo

###Problemi ultimo commit
* Il database va popolato. Il metodo per caricare da file ed aggiungere entrate al database è pronto, va solo testato.
* testata la classe di login UI, la grafica funziona.
* Vanno rivisti i try catch, le eccezioni sono gestite ma non benissimo (sarebbe carino usare dei popup)
* finita l'utility per il proprietario del ristorante che registra nuovi utenti nel database ma non so se funzioni (va testata)

##Usare il database (Win)
per utilizzare il database su windows ho dovuto installare XAMPP in modo da avviare Apache sul pc stesso altrimenti netbeans continua a dare un errore di connessione (0 packet sent). Ho lasciato volontariamente bianca la password per il root user.

Ho aggiunto la libreria SQLDriver che Netbeans non aggiunge (sembra sia un bug della IDE) pur importando nel progetto. Per risolvere:
* click dx su libraries (quelle del progetto)
* add library
* selezionare *MySQL JDBC Driver*

Ovviamente, essendo il database locale, le modifiche non sono sincronizzate con la repository github quindi "ognuno ha il suo".

