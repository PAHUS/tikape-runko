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
    public Kysymys saveOrUpdate(Kysymys object) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<Kysymys> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
/*
        Connection conn = database.getConnection();
        List<Kysymys> kysymykset = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Kysymys");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Kysymys kysymys = new Kysymys(rs.getInt("id"),rs.getString("kurssi"),rs.getString("aihe"),rs.getString("kysymysteksti"));
            //haetaan liittyvät vastaukset:
            List<Vastaus> vastaukset = new ArrayList<>();
            PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Vastaus WHERE Vastaus.kysymys_id = " + rs.getInt("id"));
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                vastaukset.add(new Vastaus(rs2.getInt("id"),rs2.getString("vastausteksi"),rs2.getBoolean("oikein")));
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
*/
    }

    @Override
    public Kysymys findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
