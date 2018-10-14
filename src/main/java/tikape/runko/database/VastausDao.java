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
public class VastausDao implements Dao<Vastaus, Integer>{
    private Database db;
    private Dao<Kysymys, Integer> kysDao;

    public VastausDao(Database database, Dao<Kysymys, Integer> kysymysDao) {
        this.db = database;
        this.kysDao = kysymysDao;
    }

    @Override
    public void delete(Integer key) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<Vastaus> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<Vastaus> findAllFrom(Integer kysKey) throws SQLException {
        List<Vastaus> vastaukset = new ArrayList<>();
        Kysymys kysymys = kysDao.findOne(kysKey);
        
        Connection conn = db.getConnection();
        
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Vastaus WHERE Vastaus.kysymys_id = ?");
        ps.setInt(1,kysKey);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Vastaus vastaus = new Vastaus(rs.getInt("id"),rs.getString("vastausteksti"),rs.getBoolean("oikein"));
            vastaukset.add(vastaus);
        }
        kysymys.setVastaukset(vastaukset);
        
        rs.close();
        ps.close();
        conn.close();
        return vastaukset;
    }
    
    @Override
    public Vastaus findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vastaus saveOrUpdate(Vastaus object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Vastaus saveTo(Vastaus object, int kysInt) throws SQLException{
        Kysymys kys = kysDao.findOne(kysInt);
        List<Vastaus> vastaukset = kys.getVastaukset();
        
        Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Vastaus(vastausteksti, kysymys_id) VALUES (?,?)");
        ps.setString(1, object.getVastausteksti());
        ps.setInt(2, kysInt);
        ps.executeUpdate();
        ps.close();
        
        PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Vastaus WHERE kysymys_id = ? AND vastausteksti = ?");
        ps2.setInt(1,kysInt);
        ps2.setString(2, object.getVastausteksti());
        ResultSet rs = ps2.executeQuery();
        rs.next();
        object.setId(rs.getInt("id"));
        rs.close();
        ps2.close();
        
        
        conn.close();
        vastaukset.add(object);
        kys.setVastaukset(vastaukset);
        return object;
    }
    
    
    
}
