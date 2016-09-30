
package utils;

/**
 *
 * @author Luca
 */
public class DatabaseStrings{
    public static final String createDatabase = "CREATE DATABASE IF NOT EXISTS Ristorante;";
    public static final String useDatabase = "USE Ristorante;";
    public static final String createMenu = "CREATE TABLE IF NOT EXISTS Menu(ID int NOT NULL AUTO_INCREMENT,Name VARCHAR(200),Description VARCHAR(1000),Price FLOAT(4,2),Tipo VARCHAR(10), PRIMARY KEY(ID));";
    public static final String createImpiegati = "CREATE TABLE IF NOT EXISTS Impiegati(ID int NOT NULL AUTO_INCREMENT,username VARCHAR(20) UNIQUE,password VARCHAR(20) UNIQUE,ruolo VARCHAR(10),status BIT DEFAULT 0,PRIMARY KEY(ID));";
    public static final String createTavoli = "CREATE TABLE IF NOT EXISTS Tavoli(NUMTAVOLO int NOT NULL, PRENOTATO BOOLEAN DEFAULT 0, PRENOTAZIONE int NOT NULL UNIQUE PRIMARY KEY);";
    public static final String createPrenotazioni = "CREATE TABLE IF NOT EXISTS Prenotazioni(PRENOTAZIONE int NOT NULL,NOME VARCHAR(20) DEFAULT NULL, ORE DATE DEFAULT NULL);";
    public static final String alterPrenotazioni = "ALTER TABLE Prenotazioni ADD FOREIGN KEY(PRENOTAZIONE) REFERENCES Tavoli(PRENOTAZIONE) ON DELETE CASCADE;";
    public static final String getAllMenu = "SELECT * FROM menu;";
}
