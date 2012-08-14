package edu.utn.frba.dds.manejoFechas.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.utn.frba.dds.manejoFechas.exceptions.DateParserException;


public enum SimpleDateParser implements DateParser {

	LATIN_AMERICAN("dd/MM/yyyy"),
	ISO8601("yyyy-MM-dd"),
	NORTH_AMERICAN("MM-dd-yyyy");
	
	private String pattern;
	private SimpleDateFormat dateFormat;
	
	private SimpleDateParser(String pattern) {
		this.setPattern(pattern);
		this.dateFormat = new SimpleDateFormat(getPattern());
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
			return this.dateFormat.parse(dateString);
		} catch (ParseException e) {
			throw new DateParserException("No se pudo parsear la fecha.", e);
		}
	}
	
	public String format(Date date) { 
		return this.dateFormat.format(date);
	}
	
}
