/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.AudioVisual;
import com.imd.telemaco.entity.Rating;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Series;
import com.imd.telemaco.entity.enums.Classification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author franklin
 */
public class SerieDAO extends AudioVisualDAO {
    private Connection connection;
    private static SerieDAO serieDAO = null;
    
    public SerieDAO() throws DatabaseException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    /**
     * 
     * @return 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     */
    public static synchronized SerieDAO getInstance() throws DatabaseException {
        if(serieDAO == null)
            serieDAO = new SerieDAO();
        return serieDAO;
    }
    
    @Override
    public void insert (AudioVisual audioVisual) throws DatabaseException, CloseConnectionException {
        String sql = "INSERT INTO telemaco.serie (name, year, status, creator, classification, genre, synopsis, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Series series = (Series) audioVisual;
        
        try {
            super.startsConnection();
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, series.getName());
            stm.setInt(2, series.getYear());
            stm.setString(3, series.getStatus());
            stm.setString(4,  series.getCreator());
            stm.setString(5, series.classifToString());
            stm.setString(6, series.getGenre());
            stm.setString(7, series.getSynopsis());
            stm.setString(8, series.getImage());
            
            stm.execute();
        } catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }
    
    @Override
    public Series select (int id) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.serie WHERE id='" + id + "'";
        Series serie = new Series();
        
        try {
            this.startsConnection();
            
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()) {
                String name     = result.getString("name");
                int    year     = result.getInt("year");
                String status   = result.getString("status");
                String creator  = result.getString("creator");
                String classif  = result.getString("classification");
                String genre    = result.getString("genre");
                String synopsis = result.getString("synopsis");
                String image    = result.getString("image");
                
                SeasonDAO seasonDAO = new SeasonDAO();
                ArrayList<Season> seasons = seasonDAO.selectAllSeasons(id);
                Classification classification = serie.stringToClassif(classif);
                ArrayList<Rating> ratings = new ArrayList<>();
                
                serie = new Series(id, name, year, status, creator, classification, ratings, genre, synopsis, image, seasons);
            } else
                serie = null;
            
            return serie;
        } catch(SQLException e) {
            throw new DatabaseException();
        } finally {
            /*try { connection.close(); } 
            catch (SQLException e) { throw new CloseConnectionException(); }*/
        }
    }
    

    @Override
    public Series select (String name) throws DatabaseException, CloseConnectionException {
    	String sql = "SELECT * FROM telemaco.serie WHERE name='" + name + "'";
    	Series serie = null;    	

    	try {
            this.startsConnection();
            
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);
    		
            if (result.next()) {
    		int id = result.getInt("id");
                serie = (Series) select(id);
            }
            
            return serie;
    	} catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            try { connection.close(); } 
            catch (SQLException e) { throw new CloseConnectionException(); }
        }
    }
    
    @Override
    public String sqlSellectAllAudioVisual () {
    	return "SELECT * FROM telemaco.serie";
    }

    @Override
    public String sqlDelete(int id) {
    	return "DELETE FROM telemaco.user WHERE id='" + id + "'";
    }
   
    @Override
    public void update(AudioVisual audioVisual) throws DatabaseException, CloseConnectionException {
    	Series series = (Series) audioVisual;
        String sql = "UPDATE telemaco.user SET "
                + "name=?, "
                + "year=?, "
                + "status=?, "
                + "creator=?, "
                + "classification=?, "
                + "genre=?, "
                + "synopsis=? ,"
                + "image=? "
                + "WHERE id=?";
        try {
            this.startsConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, series.getName());
            stm.setInt(2, series.getYear());
            stm.setString(3, series.getStatus());
            stm.setString(4,  series.getCreator());
            stm.setString(5, series.classifToString());
            stm.setString(6, series.getGenre());
            stm.setString(7, series.getSynopsis());
            stm.setString(8, series.getImage());
            stm.setInt(9, series.getId());
            
            stm.execute();
        } catch(SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }

    @Override
    public String sqlSearch (String input) {
        return "SELECT * from telemaco.serie WHERE LOWER(name) LIKE '%" + input.toLowerCase() + "%'";        
    }
}
