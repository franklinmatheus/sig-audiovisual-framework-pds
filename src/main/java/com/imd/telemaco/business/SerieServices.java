package com.imd.telemaco.business;

import com.imd.telemaco.business.exception.AudiovisualInvalidException;
import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.NoResultsException;
import com.imd.telemaco.business.exception.RatingInvalidException;
import com.imd.telemaco.business.exception.SeasonExistsException;
import com.imd.telemaco.data.DAORatingSpecialOperations;
import com.imd.telemaco.data.RatingDAO;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.AudioVisual;
import com.imd.telemaco.entity.Rating;
import com.imd.telemaco.entity.Series;
import com.imd.telemaco.entity.User;
import java.util.ArrayList;

/**
 * Class to validate all services referent to the Series Class.
 * @author Valmir Correa (valmir.jr.correa@gmail.com)
 */
public class SerieServices extends AudiovisualServices {

    /**
     * Default constructor
     */
    public SerieServices() {
    	super();
    }
	
	@Override
	public void validate(AudioVisual audiovisual, User user) 
			throws AudiovisualInvalidException, DatabaseException, CloseConnectionException {
	
		this.validateName(audiovisual);
		this.validateID(audiovisual, user);
		this.AudiovisualExistInDB(audiovisual);
	}
	
	public void validate(AudioVisual audiovisual) 
			throws AudiovisualInvalidException, DatabaseException, CloseConnectionException {
	
		this.validateName(audiovisual);
		this.AudiovisualExistInDB(audiovisual);
	}

	@Override
	public void validateName(AudioVisual audiovisual) throws AudiovisualInvalidException {
        if (audiovisual.getName().isEmpty() || audiovisual.getName() == null) {
            throw new AudiovisualInvalidException("Nome da série inválido!");
        }
		
	}

	@Override
	public void validateID(AudioVisual audiovisual, User user) throws AudiovisualInvalidException {
      if (!(audiovisual.getId() == user.getId())) {
            throw new AudiovisualInvalidException("Id inválido!");
        }
	
	}

	@Override
	public void AudiovisualExistInDB(AudioVisual audiovisual)
			throws AudiovisualInvalidException, DatabaseException, CloseConnectionException {
        
        SerieDAO serieDAO = (SerieDAO) SerieDAO.getInstanceAudioVisual();
        Series serieRemove = (Series) serieDAO.select(audiovisual.getId());
        if (null != serieRemove) {
			try {
				throw new SeasonExistsException("A serie " + "'" + audiovisual.getName() + "'" + " não existe!");
			} catch (SeasonExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
	}

	@Override
	public void validateInsert(AudioVisual audiovisual, User user)
			throws AudiovisualInvalidException, DatabaseException, CloseConnectionException {
    	
		//FIXME tirar os comentários da validação
    	this.validate(audiovisual, user);
    	SerieDAO serieDAO = (SerieDAO) SerieDAO.getInstanceAudioVisual();
    	serieDAO.insert(audiovisual);
		
	}
	
    /**
     * Return an array filled with AudioVisual that matched with input.
     * @param input
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     * @throws NoResultsException
     */
	@Override
    public ArrayList<AudioVisual> search(String input) 
    		throws DatabaseException, CloseConnectionException, NoResultsException {
    	
    	SerieDAO dao = (SerieDAO) SerieDAO.getInstanceAudioVisual();
        ArrayList<AudioVisual> results = dao.search(input);
        
        if(results.isEmpty()) {
        	throw new NoResultsException();
        }

        return results;
    }


    /**
     * Valid Rating of the series .
     *
     * @param serie
     * @throws RatingInvalidException
     */
    public void validSerieRating(Series serie) throws RatingInvalidException {
        // TODO 
        /*try {
           ValidateRatingService validade = new ValidateRatingService();
           validade.validRating(serie.getRating());
       } catch (RatingInvalidException r) {
           throw new RatingInvalidException();
       }*/
    }
    

    /**
     * Select all comments of serie.
     * @param id
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public ArrayList<Rating> getRatings(int id) throws DatabaseException, CloseConnectionException {
        DAORatingSpecialOperations commentDAO = RatingDAO.getInstance();
        ArrayList<Rating> ratings = commentDAO.selectBySerie(id);
        
        return ratings;
    }
    
    /**
     * Add a rating in database to related serie.
     * @param rating
     * @throws DatabaseException
     * @throws CloseConnectionException 
     */
    public void addRating(Rating rating) throws DatabaseException, CloseConnectionException {
        DAORatingSpecialOperations ratingDAO = RatingDAO.getInstance();
        ratingDAO.insert(rating);
    }
    
    /**
     * Remove a rating in database.
     * @param rating
     * @throws DatabaseException
     * @throws CloseConnectionException 
     */
    public void removeRating(Rating rating) throws DatabaseException, CloseConnectionException {
        DAORatingSpecialOperations ratingDAO = RatingDAO.getInstance();
        ratingDAO.delete(rating);
    }
	
}
