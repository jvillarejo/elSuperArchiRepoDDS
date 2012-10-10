package edu.utn.frba.dds.manejoFechas.exceptions;

@SuppressWarnings("serial")
public class DateParserException extends RuntimeException {

	public DateParserException(String msg) {
		super(msg);
	}
	
	public DateParserException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}