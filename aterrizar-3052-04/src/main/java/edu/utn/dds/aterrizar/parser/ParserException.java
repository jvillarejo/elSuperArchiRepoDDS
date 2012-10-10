package edu.utn.dds.aterrizar.parser;

@SuppressWarnings("serial")
public class ParserException extends RuntimeException {
	public ParserException(String message, Throwable e){
		super(message, e);
	}

	public ParserException(){
		super();
	}
}
