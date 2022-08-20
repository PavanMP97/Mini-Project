package com;

public class ItemNotFoundException extends Exception
{
	public String getMessage()
	{
		return message;
	}
	private String message;
	

	public ItemNotFoundException(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return  message;
	}
	

}
