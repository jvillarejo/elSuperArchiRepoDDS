package edu.utn.dds.aterrizar.vuelo;

import edu.utn.dds.aterrizar.manejoDeFechas.*;


/**
 * Representa los parámetros de consulta de busqueda
 * @author clari, juani
 *
 */
public class Busqueda {

	private String codigo;
	private String origen;
	private String destino;
	private DateTime fechaSalida;
	private DateTime fechaLlegada;
	private String horaSalida;
	private String horaLlegada;
	
	public Busqueda(String origen, String destino,  String fechaSalida, String fechaLlegada, String horaSalida, String horaLlegada){
		this.origen=origen;
		this.destino=destino;
		this.setFechaSalida(fechaSalida);
		this.setFechaLlegada(fechaLlegada);
		this.setHoraSalida(horaSalida);
		this.setHoraLlegada(horaLlegada);
		
	}


	public Busqueda() {
		super();
	}


	private DateTime setFecha(String fecha) {
		return (fecha != null) ?  new DateTime(SimpleDateParser.LatinAmerican(), fecha): null;
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

	
	public void setCode(String code) {
		this.codigo= code;
		
	}

	public DateTime getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida= this.setFecha(fechaSalida);
	}

	public DateTime getFechaLlegada() {
		return this.fechaLlegada;
	}


	public void setFechaLlegada(String fechaLlegada) {
			this.setFecha(fechaLlegada);
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

}

