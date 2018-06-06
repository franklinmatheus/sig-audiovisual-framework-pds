package com.imd.telemaco.business;

import java.util.ArrayList;

import com.imd.telemaco.business.exception.AudiovisualInvalidException;
import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.NoResultsException;
import com.imd.telemaco.entity.AudioVisual;
import com.imd.telemaco.entity.User;

/**
 * 
 * @author 	Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @author Valmir Correa (valmir.jr.correa@gmail.com)
 * @version 4 de jun de 2018 | 14:14:52
 */
public abstract class AudiovisualServices {
	
	/**
	 * Default Constructor
	 */
	public AudiovisualServices () { }
	
	/**
	 * Validate
	 * @param audioVisual
	 */
	public abstract void validate (AudioVisual audiovisual, User user)
		throws AudiovisualInvalidException, DatabaseException, CloseConnectionException;
	
    /**
     * Verify if the Audiovisual parameters are corrects.
     * @param audiovisual
     * @throws AudiovisualInvalidException
     */
    public abstract void validateName(AudioVisual audiovisual) 
    		throws AudiovisualInvalidException;
	
    /**
     * Valid Audiovisual ID thats match with tha user ID.
     * @param audiovisual
     * @param user
     * @throws AudiovisualInvalidException
     */
    public abstract void validateID(AudioVisual audiovisual, User user) 
    		throws AudiovisualInvalidException;
	
    /**
     * Verify if the AudioVisual already exists in the data base.
     * @param audiovisual
     * @throws AudiovisualInvalidException
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public abstract void AudiovisualExistInDB(AudioVisual audiovisual) 
    		throws AudiovisualInvalidException, DatabaseException, CloseConnectionException;
	
   /**
    * Valid AudioVisual to register in the user profile.
    * @param audioVisual
    * @throws AudiovisualInvalidException
    * @throws DatabaseException
    * @throws CloseConnectionException
    */
    public abstract void validateInsert (AudioVisual audioVisual, User user) 
    		throws AudiovisualInvalidException, DatabaseException, CloseConnectionException;
	
    /**
     * Return an array filled with AudioVisual that matched with input.
     * @param input
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     * @throws NoResultsException
     */
    public abstract ArrayList<AudioVisual> search(String input) 
    		throws DatabaseException, CloseConnectionException, NoResultsException;
}
