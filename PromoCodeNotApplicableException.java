package com;

public class PromoCodeNotApplicableException extends Exception
{
	private String message;

	public PromoCodeNotApplicableException(String message) 
	{
		this.message = message;
	}

	@Override
	public String toString() 
	{
		return message;
	}

	public String getMessage() {
		return message;
	}

	

}
