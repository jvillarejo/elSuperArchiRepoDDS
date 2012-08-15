package edu.utn.dds.aterrizar.manejoDeFechas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.utn.dds.aterrizar.manejoDeFechas.exceptions.DateParserException;

public class SimpleDateParser implements DateParser {

	private String pattern;
	private SimpleDateFormat dateFormat;
	
	public SimpleDateParser(String pattern) {
		this.setPattern(pattern);
		dateFormat = new SimpleDateFormat(pattern);
	}

	protected void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	protected String getPattern() {
		return pattern;
	}
	
	public String format(Date date) { 
		return dateFormat.format(date);
	}
	
	@Override
	public Date parse(String dateString) {
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			throw new DateParserException("No se pudo parsear la fecha.", e);
		}
	}

	
	//TODO: tiene que haber una forma mejor de resolver esto...
	
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
