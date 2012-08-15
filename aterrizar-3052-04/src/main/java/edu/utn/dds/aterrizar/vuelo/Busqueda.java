package edu.utn.dds.aterrizar.vuelo;

import edu.utn.dds.aterrizar.manejoDeFechas.*;

import java.util.Date;


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
		this.setFechaSalida(new DateTime(SimpleDateParser.LatinAmerican(), fechaSalida));
		this.setFechaLlegada(new DateTime(SimpleDateParser.LatinAmerican(), fechaLlegada));
		this.setHoraSalida(horaSalida);
		this.setHoraLlegada(horaLlegada);
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
		return fechaSalida;
	}

	public void setFechaSalida(DateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public DateTime getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(DateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
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

