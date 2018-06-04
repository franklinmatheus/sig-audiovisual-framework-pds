package com.imd.telemaco.entity;

import java.util.ArrayList;

import com.imd.telemaco.entity.enums.Classification;

/**
 * This class represents a Film object, that extends of the AudioVisual class. Its fields 
 * are year - the year that it was created, genre and duration - the duration in minutes. 
 * 
 * @author 	Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 4 de jun de 2018 | 10:41:17
 */
public class Film extends AudioVisual{
	private int    year;
	private String genre;
	private int    duration;
	/**
	 * Default Constructor
	 */
	public Film () { }
	
	/**
	 * Parameterized Constructor
	 * @param name
	 * @param status
	 * @param synopsis
	 * @param image
	 * @param classif
	 * @param year
	 * @param genre
	 * @param duration
	 */
	public Film (String name, String status, String synopsis, String image, Classification classif, int year, String genre, 
			int duration) {
		super (name, status, synopsis, image, classif);
		this.year	  = year;
		this.genre	  = genre;
		this.duration = duration;
	}
	
	/**
	 * Parameterized Constructor by the id
	 * @param id
	 * @param name
	 * @param status
	 * @param synopsis
	 * @param image
	 * @param classif
	 * @param ratings
	 * @param year
	 * @param genre
	 * @param duration
	 */
	public Film (int id, String name, String status, String synopsis, String image, Classification classif, ArrayList<Rating> ratings, 
			int year, String genre, int duration) {
		super (id, name, status, synopsis, image, classif, ratings);
		this.year	  = year;
		this.genre	  = genre;
		this.duration = duration;
	}
	
	/**
	 * Returns the year of the Film
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Returns the genre of the Film
	 * @return
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * Returns the duration of the Film
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}
}
