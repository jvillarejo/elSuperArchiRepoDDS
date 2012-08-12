package edu.utn.frba.dds.manejoFechas.domain;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateTime {
	
	private Date date;
	private final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
	
	public DateTime(Date date) {
		this.date = date;
	}
	
	public DateTime(int dia, int mes, int anio)	{
		this.date = new GregorianCalendar(anio, mes - 1, dia).getTime();
	}	
	
	public DateTime(DateParser dateParser, String dateString) {
		this.date = dateParser.parse(dateString);  
	}
	
	public long diasDeDiferenciaCon(DateTime unaFecha){
		return Math.abs((this.getDate().getTime() - unaFecha.getDate().getTime()) / MILLISECONDS_PER_DAY);
	}
	
	public boolean esAnteriorA(DateTime unaFecha){
		return this.getDate().compareTo(unaFecha.getDate()) < 0;
	}
	
	public Date getDate(){
		return this.date;
	}
}
