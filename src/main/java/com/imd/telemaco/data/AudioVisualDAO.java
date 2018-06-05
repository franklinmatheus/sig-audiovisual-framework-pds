package com.imd.telemaco.data;

import java.util.ArrayList;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.AudioVisual;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author  Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 5 de jun de 2018 | 09:52:54
 */
public abstract class AudioVisualDAO implements DAOaudioVisualSpecialOperations {
	private Connection connection;
	private static AudioVisualDAO audioVisualDAO = null;
	
	/**
	 * Default Constructor
	 */
	public AudioVisualDAO () throws DatabaseException { 
		this.connection = ConnectionFactory.getConnection();
	}
	
//	public abstract static synchronized AudioVisualDAO getInstanceAudioVisual();
	
	public static synchronized AudioVisualDAO getInstance() throws DatabaseException {
//        return getInstanceAudioVisual();
		// FIXME
		return null;		
    }
	
	/**
	 * Method to know if already exists a connection with the db
	 * @throws DatabaseException
	 */
	protected void startsConnection() throws DatabaseException {
        try {
            if(this.connection.isClosed())
                this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new DatabaseException();
        }   
    } 
	
	@Override
	public abstract void insert(AudioVisual audioVisual) throws DatabaseException, CloseConnectionException;
	
	@Override
	public abstract AudioVisual select(int id) throws DatabaseException, CloseConnectionException;

	@Override
	public abstract AudioVisual select(String name) throws DatabaseException, CloseConnectionException;
	
	public abstract String sqlDelete();
	
	@Override
	public void delete(AudioVisual object) throws DatabaseException, CloseConnectionException {
		String sql = sqlDelete();
        try {
            this.startsConnection();
            
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch(SQLException e) {
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
	public void update(AudioVisual object) throws DatabaseException, CloseConnectionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<AudioVisual> search(String input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public abstract ArrayList<AudioVisual> sellectAllAudioVisuals();
}
