package com.imd.telemaco.entity;

import com.imd.telemaco.entity.enums.Classification;
import java.util.ArrayList;

/**
 * This class represents a TV Series object. Its fields are year, creator, and genre
 * 
 * @author 	Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 4 de jun de 2018 | 11:59:16
 */
public class Series extends AudioVisual {
    private int year;
    private String creator;
    private String genre;
    private ArrayList<Season> seasons;
    
    /**
     * Default Constructor
     */
    public Series() { }

    /**
     * Parameterized Constructor
     * @param name
     * @param year
     * @param status
     * @param creator
     * @param classification
     * @param genre
     * @param synopsis
     * @param image
     */
    public Series(String name, int year, String status, String creator, Classification classification, String genre, 
    		String synopsis, String image) { 
    	super(name, status, synopsis, image, classification);
    	this.year = year;
    	this.creator = creator;
    	this.genre = genre;
    	this.seasons = new ArrayList<>();
    }
    
    /**
     * Parameterized Constructor by the id
     * @param id
     * @param name
     * @param year
     * @param status
     * @param creator
     * @param classification
     * @param genre
     * @param synopsis
     * @param image
     * @param seasons
     */
    public Series(int id, String name, int year, String status, String creator, Classification classification, 
    		ArrayList<Rating> ratings, String genre, String synopsis, String image, ArrayList<Season> seasons) {
    	super(id, name, status, synopsis, image, classification, ratings);
    	this.year = year;
    	this.creator = creator;
    	this.genre = genre;
    	this.seasons = seasons;
    }
    
    /**
     * Returns the year of the Series
     * @return year
     */
    public int getYear() {
		return year;
	}
    
    /**
     * Returns the creator of the Series
     * @return creator
     */
    public String getCreator () {
    	return creator;
    }
    
    /**
     * Returns the genre of the Series
     * @return
     */
    public String getGenre () {
    	return genre;
    }

    /**
     * Returns the seasons of the Series
     * @return
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * Change the value of the seasons
     * @param seasons
     */
    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
    
    @Override
    public String toString() {
        return "Serie{" + "nome=" + super.getName() + ", Ano=" + year + '}';
    }
}
