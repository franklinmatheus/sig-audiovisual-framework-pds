/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.Series;
import java.util.ArrayList;

/**
 *
 * @author franklin
 */
public interface DAOSerieSpecialOperations extends DAO<Series> {
    /**
     * Select a serie by name.
     * @param name
     * @return
     * @throws DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public Series select (String name) throws DatabaseException, CloseConnectionException;
    
    /**
     * Select all series and return an array with them.
     * @return
     * @throws DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     */
    public ArrayList<Series> selectAllSeries () throws DatabaseException, CloseConnectionException;
    
    /**
     * Search series by name that user inputs.
     * @param input
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException 
     */
    public ArrayList<Series> search(String input) throws DatabaseException, CloseConnectionException;
}
