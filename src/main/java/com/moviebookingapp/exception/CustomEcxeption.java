package com.moviebookingapp.exception;

public class CustomEcxeption extends Exception{

	/**
	 * UsernameAlreadyExists Exception
	 */
	private static final long serialVersionUID = 1L;

	public CustomEcxeption(String msg) {

		super(msg);
	}
}