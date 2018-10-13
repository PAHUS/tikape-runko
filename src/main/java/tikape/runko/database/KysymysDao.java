/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.*;
import java.util.*;
import tikape.runko.Kysymys;


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
        


    }

    @Override
    public List<Kysymys> findAll() throws SQLException {
        Connection conn = database.getConnection();
        List<Kysymys> kysymykset = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Kysymys");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            kysymykset.add(new Kysymys(rs.getInt("id"),rs.getString("kurssi"),rs.getString("aihe"),rs.getString("kysymysteksti")));
        }
        throw new UnsupportedOperationException("Not supported yet."); 
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
