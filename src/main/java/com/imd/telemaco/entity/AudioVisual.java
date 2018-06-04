package com.imd.telemaco.entity;

/**
 * This class represents a AudioVisual object. Where its fields are id, name, status - if it 
 * is finished or at progress, synopsis and image - which in real is the system path image.
 * 
 * @author 	Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 4 de jun de 2018 | 10:29:31
 */
public abstract class AudioVisual {
	private int id;
	private String name;
	private String status;
	private String synopsis;
	private String image;
	
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
	public AudioVisual (String name, String status, String synopsis, String image) {
		this.name = name;
		this.status = status;
		this.synopsis = synopsis;
		this.image = image;		
	}
	
	/**
	 * Parameterized Constructor by the id
	 * @param id
	 * @param name
	 * @param status
	 * @param synopsis
	 * @param image
	 */
	public AudioVisual (int id, String name, String status, String synopsis, String image) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.synopsis = synopsis;
		this.image = image;		
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
}
