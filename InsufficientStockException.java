package com;

public class InsufficientStockException extends Exception
{
	private String message;

	@Override
	public String toString() {
		return message;
	}

	public InsufficientStockException(String message)
	{
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	

}
