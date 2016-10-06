package utils;

/**
 * La classe contiene tutte le stringhe query ed update che non richiedono parametri
 * utilizzate nel programma.
 * @author Luca
 */
public class DatabaseStrings{
    public static final String createDatabase = "CREATE DATABASE IF NOT EXISTS Ristorante;";
    public static final String useDatabase = "USE Ristorante;";
    public static final String dropDatabase = "DROP DATABASE IF EXISTS istorante;";
    public static final String createMenu = "CREATE TABLE IF NOT EXISTS Menu(ID int NOT NULL AUTO_INCREMENT,Name VARCHAR(200),Description VARCHAR(1000),Price FLOAT(4,2),Tipo VARCHAR(10), PRIMARY KEY(ID));";
    public static final String createImpiegati = "CREATE TABLE IF NOT EXISTS Impiegati(ID int NOT NULL AUTO_INCREMENT,username VARCHAR(20) UNIQUE,password VARCHAR(20) UNIQUE,ruolo VARCHAR(10),status BIT DEFAULT 0,PRIMARY KEY(ID));";
    public static final String createTavoli = "CREATE TABLE IF NOT EXISTS Tavoli(NUMTAVOLO int NOT NULL PRIMARY KEY, PRENOTATO BOOLEAN DEFAULT 0, PRENOTAZIONE int UNIQUE DEFAULT NULL);";
    public static final String createPrenotazioni = "CREATE TABLE IF NOT EXISTS Prenotazioni(PRENOTAZIONE int DEFAULT NULL,NOME VARCHAR(20) DEFAULT NULL, ORE DATE DEFAULT NULL, PERSONE int DEFAULT 0);";
    public static final String alterPrenotazioni = "ALTER TABLE Prenotazioni ADD FOREIGN KEY(PRENOTAZIONE) REFERENCES Tavoli(PRENOTAZIONE) ON DELETE CASCADE;";
    public static final String getAllMenu = "SELECT * FROM menu;";
    public static final String getAllTavoli = "SELECT * FROM Tavoli;";
    public static final String dropTavoli = "DROP TABLE IF EXISTS Tavoli;";
    public static final String dropMenu = "DROP TABLE IF EXISTS Menu;";
}
