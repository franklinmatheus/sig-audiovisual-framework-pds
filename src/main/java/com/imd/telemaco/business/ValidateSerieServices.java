package com.imd.telemaco.business;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.EpisodeInvalidException;
import com.imd.telemaco.business.exception.NoResultsException;
import com.imd.telemaco.business.exception.RatingInvalidException;
import com.imd.telemaco.business.exception.SeasonExistsException;
import com.imd.telemaco.business.exception.SerieExistsException;
import com.imd.telemaco.business.exception.SerieInvalidException;
import com.imd.telemaco.data.RatingDAO;
import com.imd.telemaco.data.DAORatingSpecialOperations;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.Rating;
import com.imd.telemaco.entity.Series;
import com.imd.telemaco.entity.User;
import java.util.ArrayList;

/**
 * Class to validate all services referent to the Series Class.
 *
 * @author valmir
 */
public class ValidateSerieServices {

    /**
     * Default constructor
     */
    public ValidateSerieServices() { }

    /**
     * Verify if the series parameters are corrects.
     *
     * @param serie
     * @throws SerieInvalidException
     */
    public void validSerieName(Series serie) throws SerieInvalidException {

        if (serie.getName().isEmpty() || serie.getName() == null) {
            throw new SerieInvalidException("Nome da série inválido!");
        }
    }

    /**
     * Valid series to insert in data base.
     *
     * @param serie
     * @throws com.imd.telemaco.business.exception.EpisodeInvalidException
     */
    public void validateSerieInsert(Series serie) throws EpisodeInvalidException {
        try {
            this.validSerieName(serie);
        } catch (SerieInvalidException s) {
            throw new EpisodeInvalidException("Não foi possivel inserir a serie!");
        }
    }

    /**
     * Valid series ID thats match with tha user ID.
     *
     * @param serie
     * @param user
     * @throws com.imd.telemaco.business.exception.SerieInvalidException
     */
    public void validSerieID(Series serie, User user) throws SerieInvalidException {
        if (!(serie.getId() == user.getId())) {
            throw new SerieInvalidException();
        }
    }

    /**
     * Verify if the series already exists in the data base.
     *
     * @param serie
     * @throws SerieExistsException
     */
    private void serieExist(Series serie) throws SerieExistsException, DatabaseException, CloseConnectionException {
        SerieDAO dao = SerieDAO.getInstance();
        Series exists = dao.select(serie.getId());
        if (null == exists) {
            throw new SerieExistsException("Essa serie não existe!");
        }
    }

    /**
     * Valid series to register in the user profile.
     *
     * @param serie
     * @throws com.imd.telemaco.business.exception.SerieInvalidException
     * @throws SerieExistsException
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public void validSerieInsert (Series serie) throws SerieInvalidException, SerieExistsException, DatabaseException, CloseConnectionException {
    	//FIXME tirar os comentários da validação
    	//this.validSerieName(serie);
        //this.serieExist(serie);
        SerieDAO serieDAO = new SerieDAO();
        serieDAO.insert(serie);
    }

    /**
     * Valid series to remove of the data base.
     *
     * @param serie
     * @throws SeasonExistsException
     */
    public void validSerieRemove(Series serie) throws SeasonExistsException, DatabaseException, CloseConnectionException {
        SerieDAO serieDAO = SerieDAO.getInstance();
        Series serieRemove = serieDAO.select(serie.getId());
        if (null != serieRemove) {
            throw new SeasonExistsException("A serie '" + serie.getName() + "' não existe!");
        }
    }

    /**
     * Valid series to Update in the data base.
     *
     * @param serie
     * @throws SerieInvalidException
     * @throws com.imd.telemaco.business.exception.SerieExistsException
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public void validSerieUpdate(Series serie) throws SerieInvalidException, SerieExistsException, DatabaseException, CloseConnectionException {
        this.validSerieName(serie);
        SerieDAO serieDAO = SerieDAO.getInstance();
        Series serieUpdate = serieDAO.select(serie.getId());
        if (serieUpdate == null) {
            throw new SerieExistsException("Impossível atualizar, esta serie não existe!");
        }
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
     * Return an array filled with series that matched with input.
     * @param input
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     * @throws NoResultsException 
     */
    public ArrayList<Series> search(String input) throws DatabaseException, CloseConnectionException, NoResultsException {
        SerieDAO dao = SerieDAO.getInstance();
        ArrayList<Series> results = dao.search(input);
        
        if(results.isEmpty())
            throw new NoResultsException();
        
        return results;
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
