package edu.utn.dds.aterrizar.manejoDeFechas;

import java.util.Date;

public interface DateParser {

	public Date parse(String dateString);
	public String toString(DateTime date);

}