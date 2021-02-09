package com.trustly.emiliano.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exceptions that are thrown when there is an error in the controller.
 * 
 * @author Emiliano Pessôa
 *
 */
public class ControllerException extends Exception {

	private static final long serialVersionUID = 1L;

	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Constructor
	 * 
	 * @param msg a description of the exception.
	 * @param t   The cause of the exception.
	 */
	public ControllerException(String msg, Throwable t) {
		super(msg, t);
		logger.error(msg, this);
	}

	/**
	 * Constructor
	 * 
	 * @param msg a description of the exception
	 */
	public ControllerException(String msg) {
		super(msg);
		logger.error(msg, this);
	}

}
