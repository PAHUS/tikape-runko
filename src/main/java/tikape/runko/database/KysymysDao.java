/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.*;
import java.util.*;
import tikape.runko.Kysymys;
import tikape.runko.Vastaus;


/**
 *
 * @author Tande
 */
public class KysymysDao implements Dao<Kysymys,Integer>{
    
    private Database database;
    public KysymysDao(Database database) {
        this.database = database;
    }

    @Override
    public Kysymys saveOrUpdate(Kysymys k) throws SQLException {
        //HUOM MITÄ TEHDÄÄN JOS ID EI NULL??? ELI TÄSSÄ VAIN SAVE /// ENTÄ JOS SYÖTETÄÄN IDENTTISET ARVOT 
        Connection conn = database.getConnection();
        
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Kysymys(kurssi,aihe,kysymysteksti) VALUES (?,?,?)");
        ps.setString(1, k.getKurssi());
        ps.setString(2,k.getAihe());
        ps.setString(3, k.getKysymysteksti());
        ps.executeUpdate();
        ps.close();
        
        //haetaan ID:
        PreparedStatement ps2 = conn.prepareStatement("SELECT id FROM Kysymys WHERE kurssi = ? AND aihe = ? AND kysymysteksti = ?");
        ps2.setString(1, k.getKurssi());
        ps2.setString(2, k.getAihe());
        ps2.setString(3, k.getKysymysteksti());
        ResultSet rs = ps2.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        k.setId(id);
        
        conn.close();
        return k;
    }

    @Override
    public List<Kysymys> findAll() throws SQLException {
        
        Connection conn = database.getConnection();
        List<Kysymys> kysymykset = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Kysymys");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Kysymys kysymys = new Kysymys(rs.getString("kurssi"),rs.getString("aihe"),rs.getString("kysymysteksti"));
            kysymys.setId(rs.getInt("id"));
            //haetaan liittyvät vastaukset:
            List<Vastaus> vastaukset = new ArrayList<>();
            PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Vastaus WHERE Vastaus.kysymys_id = " + rs.getInt("id"));
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                vastaukset.add(new Vastaus(rs2.getInt("id"),rs2.getString("vastausteksti"),rs2.getBoolean("oikein")));
            }
            kysymys.setVastaukset(vastaukset);
            kysymykset.add(kysymys);
            rs2.close();
            ps2.close();
        }
        rs.close();
        ps.close();
        conn.close();
        return kysymykset; 

    }

    @Override
    public Kysymys findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Kysymys WHERE id = ?");
        ps.setInt(1, key);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    
    
}
