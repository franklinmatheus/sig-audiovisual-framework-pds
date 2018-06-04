package com.imd.telemaco.business;

import java.util.ArrayList;

import com.imd.telemaco.business.exception.AudioVisualExistsException;
import com.imd.telemaco.entity.AudioVisual;

/**
 * 
 * @author 	Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 4 de jun de 2018 | 14:14:52
 */
public abstract class AudioVisualServices {
	
	/**
	 * Default Constructor
	 */
	public AudioVisualServices () { }
	
	public abstract void validate (AudioVisual audioVisual);
	
	private void audioVisualExists (AudioVisual audioVisual) throws AudioVisualExistsException {
		//TODO
	}
	
	public void update (AudioVisual audioVisual) {
		try {
			audioVisualExists(audioVisual);
			// TODO the code of update
			
		} catch (AudioVisualExistsException e) {
			System.out.println(e);
		}
	}	
	
	public void insert (AudioVisual audioVisual) {
		try {
			audioVisualExists(audioVisual);
		} catch (AudioVisualExistsException e) {
			// TODO the code of insert
		}		
	}
	
	public void search () {
		//TODO
	}
	
	public abstract ArrayList<AudioVisual> recomend (); 
}
