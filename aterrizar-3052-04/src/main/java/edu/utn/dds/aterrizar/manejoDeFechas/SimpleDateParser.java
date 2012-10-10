package edu.utn.dds.aterrizar.manejoDeFechas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.utn.dds.aterrizar.manejoDeFechas.exceptions.DateParserException;


public class SimpleDateParser implements DateParser {

	private String pattern;
	private SimpleDateFormat formatter;
	
	public SimpleDateParser(String pattern) {
		this.setPattern(pattern);
		this.formatter = new SimpleDateFormat(getPattern());
	}

	protected void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	protected String getPattern() {
		return pattern;
	}
	
	@Override
	public Date parse(String dateString) {
		try {
			return this.formatter.parse(dateString);
		} catch (ParseException e) {
			throw new DateParserException("No se pudo parsear la fecha.", e);
		}
	}
	
	public String toString(DateTime dateTime) {
		return this.formatter.format(dateTime.getDate());
	}
	
	//Implementaciones default
	public static SimpleDateParser ISO8601() {
		return new SimpleDateParser("yyyy-MM-dd");
	}
	
	public static SimpleDateParser LatinAmerican() {
		return new SimpleDateParser("dd/MM/yyyy");
	}
	
	public static SimpleDateParser NorthAmerican() {
		return new SimpleDateParser("MM-dd-yyyy");
	}
}
