package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("DROP TABLE IF EXISTS Kysymys;");
        lista.add("DROP TABLE IF EXISTS Vastaus;");
        
        lista.add("CREATE TABLE Kysymys(\n" +
                    "kurssi varchar(200),\n" +
                    "aihe varchar(200),\n" +
                    "kysymysteksti varchar(500),\n" +
                    "id Integer PRIMARY KEY);");
        lista.add("CREATE TABLE Vastaus(\n" +
                    "id Integer PRIMARY KEY,\n" +
                    "vastausteksti varchar(500), \n"+
                    "oikein Boolean,\n" +
                    "kysymys_id integer,\n" +
                    "FOREIGN KEY (kysymys_id) REFERENCES Kysymys(id));");

        return lista;
    }
}
