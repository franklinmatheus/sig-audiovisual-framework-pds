package com.imd.telemaco.data;

import java.util.ArrayList;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.AudioVisual;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 5 de jun de 2018 | 09:52:54
 */
public abstract class AudiovisualDAO implements DAOAudioVisualSpecialOperations {

    private Connection connection;
    private static AudiovisualDAO audiovisualDAO = null;
    
    /*private static AudioVisualDAO audioVisualDAO = null;*/
    /**
     * Default Constructor
     */
    public AudiovisualDAO() throws DatabaseException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    public static synchronized AudiovisualDAO getInstanceAudioVisual() throws DatabaseException {
        if (audiovisualDAO == null) {
            audiovisualDAO = new SerieDAO();
        }
        return audiovisualDAO;
    }

    /**
     * Method to know if already exists a connection with the db
     *
     * @throws DatabaseException
     */
    protected void startsConnection() throws DatabaseException {
        try {
            if (this.connection.isClosed()) {
                this.connection = ConnectionFactory.getConnection();
            }
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    @Override
    public abstract void insert(AudioVisual audioVisual) throws DatabaseException, CloseConnectionException;

    public abstract String sqlSelectId(int id);

    public abstract AudioVisual fillAudioVisualAttributes(int id, ResultSet result) throws DatabaseException;

    @Override
    public AudioVisual select(int id) throws DatabaseException, CloseConnectionException {
        String sql = sqlSelectId(id);
        AudioVisual audioVisual;
        try {
            this.startsConnection();

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                audioVisual = fillAudioVisualAttributes(id, result);
            } else {
                audioVisual = null;
            }

            return audioVisual;
        } catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            /*try { connection.close(); } 
            catch (SQLException e) { throw new CloseConnectionException(); }*/
        }
    }

    public abstract String sqlSelectName(String name);

    @Override
    public AudioVisual select(String name) throws DatabaseException, CloseConnectionException {
        String sql = sqlSelectName(name);
        AudioVisual audioVisual = null;

        try {
            this.startsConnection();

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            if (result.next()) {
                int id = result.getInt("id");
                audioVisual = select(id);
            }

            return audioVisual;
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

    public abstract String sqlDelete(int id);

    @Override
    public void delete(AudioVisual audioVisual) throws DatabaseException, CloseConnectionException {
        String sql = sqlDelete(audioVisual.getId());
        try {
            this.startsConnection();

            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }

    @Override
    public abstract void update(AudioVisual object) throws DatabaseException, CloseConnectionException;

    public abstract String sqlSearch(String input);

    @Override
    public ArrayList<AudioVisual> search(String input) throws DatabaseException, CloseConnectionException {
        String sql = sqlSearch(input);
        ArrayList<AudioVisual> results = new ArrayList<>();

        try {
            startsConnection();

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                int id = set.getInt("id");
                AudioVisual audioVisual = select(id);
                results.add(audioVisual);
            }

            return results;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new CloseConnectionException();
            }
        }
    }

    public abstract String sqlSellectAllAudioVisual();

    @Override
    public ArrayList<AudioVisual> sellectAllAudioVisuals() throws DatabaseException, CloseConnectionException {
        ArrayList<AudioVisual> audioVisuals = new ArrayList<>();
        String sql = sqlSellectAllAudioVisual();

        try {
            this.startsConnection();

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt("id");
                AudioVisual audioVisual = select(id);
                audioVisuals.add(audioVisual);
            }

            return audioVisuals;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }
}
