package com.imd.telemaco.entity;

import java.util.ArrayList;

import com.imd.telemaco.entity.enums.Classification;

/**
 * This class represents a AudioVisual object. Where its fields are id, name, status - if it 
 * is finished or at progress, synopsis and image - which in real is the system path image.
 * 
 * @author 	Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 4 de jun de 2018 | 10:29:31
 */
public abstract class AudioVisual {
	private int    id;
	private String name;
	private String status;
	private String synopsis;
	private String image;
	private Classification classification;
	private ArrayList<Rating> ratings;
	
	/**
	 * Default Constructor
	 */
	public AudioVisual () { }
	
	/**
	 * Parameterized Constructor
	 * @param name
	 * @param status
	 * @param synopsis
	 * @param image
	 */
	public AudioVisual (String name, String status, String synopsis, String image, Classification classification) {
		this.name 	  = name;
		this.status   = status;
		this.synopsis = synopsis;
		this.image 	  = image;		
		this.classification = classification;
		this.ratings  = new ArrayList<>(); 
	}
	
	/**
	 * Parameterized Constructor by the id
	 * @param id
	 * @param name
	 * @param status
	 * @param synopsis
	 * @param image
	 */
	public AudioVisual (int id, String name, String status, String synopsis, String image, Classification classification, ArrayList<Rating> ratings) {
		this(name, status, synopsis, image, classification);
		this.id 	  = id;
		this.ratings  = ratings;
	}
	
	/**
	 * Returns the id of the AudioVisual
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the name of the AudioVisual
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the status of the AudioVisual
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Returns the synopsis of the AudioVisual
	 * @return synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}
	
	/**
	 * Returns the image (path) of the AudioVisual
	 * @return image
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * Returns the classification of the AudioVisual
	 * @return classification
	 */
	public Classification getClassification() {
		return classification;
	}
	
	/**
     * Returns the ratings of the AudioVisual 
     * @return ratings
     */
    public ArrayList<Rating> getRating() {
        return ratings;
    }
	
	/**
	 * Change the value of the status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Change the value of the synopsis
	 * @param synopsis
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	/**
	 * Change the value of the image
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * Change the value of the ratings
	 * @param ratings
	 */
	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}
	
	/**
	 * This method add a rating in the Ratings list of the Film
	 * @param rating
	 */
	public void addRating (Rating rating) {
		ratings.add(rating);
	}
	
	/**
	 * Returns the classification of the AudioVisual in String type 
	 * @return cString;
	 */
	public String classifToString () {
		String cString;
		switch (classification) {
			case GENERAL:
				cString = "L";
				break;
			case P10:
				cString = "10";
				break;
			case P12:
				cString = "12";
				break;
			case P14:
				cString = "14";
				break;
			case P16:
				cString = "16";
				break;
			default:
				cString = "18";
		}
		return cString;
	}
    
	/**
	 * Convert a String type to Classification type
	 * @param classif
	 * @return classification
	 */
	public Classification stringToClassif (String classif) {
		Classification classification;
		
		switch (classif) {
			case "L":
				classification = Classification.GENERAL;
				break;
			case "10":
				classification = Classification.P10;
				break;
			case "12":
				classification = Classification.P12;
				break;
			case "14":
				classification = Classification.P14;
				break;
			case "16":
				classification = Classification.P16;
				break;
			default:
				classification = Classification.P18;
				break;
				
		}
		
		return classification;
	}
}
