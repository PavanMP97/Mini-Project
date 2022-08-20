package com;

public class InvalidCopen extends Exception 
{
private String message;

public InvalidCopen(String message) 
{
	this.message = message;
}

public String getMessage() 
{
	return message;
}

@Override
public String toString() {
	return message;
}




}
