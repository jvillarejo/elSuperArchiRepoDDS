package edu.utn.dds.aterrizar.vuelo;

import edu.utn.dds.aterrizar.manejoDeFechas.*;

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
	
	public Vuelo(String origen, String destino, String fecha){
		this.origen=origen;
		this.destino=destino;
		this.setFecha(fecha);
	}

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
			this.fecha= new SimpleDateParser("dd/mm/yyyy").parse(aString);

	}
	public void setCode(String code) {
		this.codigo= code;
		
	}

}

