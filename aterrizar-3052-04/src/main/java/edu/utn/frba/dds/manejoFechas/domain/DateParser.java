package edu.utn.frba.dds.manejoFechas.domain;

import java.util.Date;

public interface DateParser {

	public Date parse(String dateString);
	public String format(Date date);

}