package com.imd.telemaco.business.exception;

public class AudioVisualExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public AudioVisualExistsException () { }
	
	public AudioVisualExistsException (String msg) {
		super(msg);
	}
}
