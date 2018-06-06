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
import java.util.ArrayList;

/**
 *
 * @author franklin
 */
public class SerieDAO extends AudiovisualDAO {

    private Connection connection;

    public SerieDAO() throws DatabaseException {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(AudioVisual audioVisual) throws DatabaseException, CloseConnectionException {
        String sql = "INSERT INTO telemaco.serie (name, year, status, creator, classification, genre, synopsis, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Series series = (Series) audioVisual;

        try {
            super.startsConnection();

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, series.getName());
            stm.setInt(2, series.getYear());
            stm.setString(3, series.getStatus());
            stm.setString(4, series.getCreator());
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
    public String sqlSellectAllAudioVisual() {
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
            stm.setString(4, series.getCreator());
            stm.setString(5, series.classifToString());
            stm.setString(6, series.getGenre());
            stm.setString(7, series.getSynopsis());
            stm.setString(8, series.getImage());
            stm.setInt(9, series.getId());

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
    public String sqlSearch(String input) {
        return "SELECT * from telemaco.serie WHERE LOWER(name) LIKE '%" + input.toLowerCase() + "%'";
    }

    @Override
    public String sqlSelectId(int id) {
        return "SELECT * FROM telemaco.serie WHERE id='" + id + "'";
    }

    @Override
    public AudioVisual fillAudioVisualAttributes(int id, ResultSet result) throws DatabaseException {
        AudioVisual serie = new Series();
        try {
            String name = result.getString("name");
            int year = result.getInt("year");
            String status = result.getString("status");
            String creator = result.getString("creator");
            String classif = result.getString("classification");
            String genre = result.getString("genre");
            String synopsis = result.getString("synopsis");
            String image = result.getString("image");

            SeasonDAO seasonDAO = new SeasonDAO();
            ArrayList<Season> seasons = seasonDAO.selectAllSeasons(id);
            Classification classification = serie.stringToClassif(classif);
            ArrayList<Rating> ratings = new ArrayList<>();

            serie = new Series(id, name, year, status, creator, classification, ratings, genre, synopsis, image, seasons);
            
            return serie;
        } catch (Exception e) {
            throw new DatabaseException();
        }
        
    }

    @Override
    public String sqlSelectName(String name) {
        return "SELECT * FROM telemaco.serie WHERE name='" + name + "'";
    }
}
