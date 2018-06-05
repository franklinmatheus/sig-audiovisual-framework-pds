package com.imd.telemaco.entity;

/**
 * This class represents a TV channel program Presenter. Its fields are name,
 * and the genre (f to female and m to the male).
 *
 * @author 	Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 4 de jun de 2018 | 11:38:41
 */
public class Presenter {
	private String name;
	private char   genre;
	
	/**
	 * Parameterized Constructor
	 * @param name
	 * @param genre
	 */
	public Presenter (String name, char genre) {
		this.name  = name;
		this.genre = genre;
	}
	
	/**
	 * Returns the name of the Presenter
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the genre of the Presenter
	 * @return
	 */
	public char getGenre() {
		return genre;
	}
}