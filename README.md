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
3. Bugfix vari
  * Creato package *ristorante* 
  * Spostate le classi legate al ristorante nel relativo package (così è più organizzato)
  * Sistemata classe di gestione login, ora funziona bene
  * Implementato design pattern Singleton per la classe NewUserWindow

###Problemi ultimo commit
* Il database va popolato, per questo però ho fatto un metodo di utility
* Vanno rivisti i try catch, le eccezioni sono gestite ma non benissimo (sarebbe carino usare dei popup)
* Bisogna aggiungere alla classe Ristorante il metodo printTicket (in attesa da Fabio) senza cui la UI cassa non va
* L'utility per il proprietario del ristorante che registra nuovi utenti nel database va testata
* Andrebbe deciso bene cosa (che tipo di dato) mettere nella colonna *ruolo* della tabella *impiegati*: forse è meglio usare valori interi piuttosto che stringhe...

##Usare il database (Win)
per utilizzare il database su windows ho dovuto installare XAMPP in modo da avviare Apache sul pc stesso altrimenti netbeans continua a dare un errore di connessione (0 packet sent). Ho lasciato volontariamente bianca la password per il root user.

Ho aggiunto la libreria SQLDriver che Netbeans non aggiunge (sembra sia un bug della IDE) pur importando nel progetto. Per risolvere:
* click dx su libraries (quelle del progetto)
* add library
* selezionare *MySQL JDBC Driver*

Ovviamente, essendo il database locale, le modifiche non sono sincronizzate con la repository github quindi "ognuno ha il suo".

##Struttura database
L'utility di creazione database crea 2 table: Impiegati e Menu.
L'utility per popolare la tabella degli impiegati inserisce valori di test riportati qui sotto.
###Impiegati
Username | Password | Ruolo | Status
---------|----------|-------|---------
cuoco1 | passwordcuoco1 | CUOCO | false
cuoco2 | passwordcuoco2 | CUOCO | false
cameriere1 | passwordcameriere1 | CAMERIERE | false
cameriere2 | passwordcameriere2 | CAMERIERE | false
cassa1 | passwordcassa1 | CASSA | false
cassa2 | passwordcassa2 | CASSA | false
principale1 | passwordcapo1 | CAPO | false

*NB:* status è una variabile di tipo BIT usata come flag true/false ed indica se quell' utente ha effettuato il login (ed è ancora collegato) o meno.

###Menu
element_id | name | description | price | type
-----------|------|-------------|-------|-------
 | | | |


