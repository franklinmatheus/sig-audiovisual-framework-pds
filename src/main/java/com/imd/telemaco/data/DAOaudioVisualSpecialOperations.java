package com.imd.telemaco.data;

import java.util.ArrayList;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.AudioVisual;

/**
 * This class extends the DAO class
 * 
 * @author  Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 5 de jun de 2018 | 09:46:45
 */
public interface DAOaudioVisualSpecialOperations extends DAO<AudioVisual> {
	
	/**
	 * Returns a AudioVisual by the name past
	 * @param  name
	 * @return AudioVisual
	 * @throws CloseConnectionException 
	 * @throws DatabaseException 
	 */
	public AudioVisual select (String name) throws DatabaseException, CloseConnectionException;
	
	/**
	 * Returns AudioVisual ArrayList that corresponds with the input 
	 * @param  input
	 * @return audioVisuals
	 * @throws DatabaseException 
	 * @throws CloseConnectionException 
	 */
	public ArrayList<AudioVisual> search (String input) throws DatabaseException, CloseConnectionException;
	
	/**
	 * Returns all the AudioVisuals
	 * @return audioVisuals
	 * @throws DatabaseException 
	 * @throws CloseConnectionException 
	  */
	public ArrayList<AudioVisual> sellectAllAudioVisuals () throws DatabaseException, CloseConnectionException;
}
