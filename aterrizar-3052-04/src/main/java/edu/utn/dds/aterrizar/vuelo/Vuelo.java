package edu.utn.dds.aterrizar.vuelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa los parámetros de consulta de busqueda
 * @author clari, juani
 *
 */
public class Vuelo {

	private String codigo;
	private String origen;
	private String destino;
	private Date fecha;

	public String getCodigo() {
		return codigo;
	}
	
	public String getOrigen() {
		return this.origen;
	}

	public String getDestino() {
		return this.destino;
	}

	public Date getFecha() {
		return this.fecha;
	}
	
	public void setFecha(String aString){
		try {
			this.fecha= new SimpleDateFormat().parse(aString);
		} catch (ParseException e) {
			throw new RuntimeException("can't parse string to date", e);
		}
	}
	public void setCode(String code) {
		this.codigo= code;
		
	}

}

