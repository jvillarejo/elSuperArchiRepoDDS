package edu.utn.dds.aterrizar.escalas;

import java.util.ArrayList;
import java.util.List;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.manejoDeFechas.DateTime;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;

public  abstract class Vuelo {
	protected DateTime fechaSalida;
	protected DateTime fechaLlegada;
	protected String origen;
	protected String destino;
	protected List<Asiento> asientos = new ArrayList<Asiento>();
	protected String codigo;
	protected Aerolinea aerolinea;
	public abstract long getDuration();
	public abstract void filtrarAsientos(List<Filtro<Asiento>> filtros, Usuario usuario);
	public abstract Double getPrecioMasBarato();


	public void setCodigo(String codigo) {
		this.codigo= codigo;
	}

	public List<Asiento> getAsientos() {
		return this.asientos;
	}
	public void setOrigen(String origen) {
		this.origen=origen;
		
	}
	public void setDestino(String destino) {
		this.destino= destino;
	}

	public DateTime getFechaLlegada() {
		return this.fechaLlegada;
	}

	public DateTime getFechaSalida() {
		return this.fechaSalida;
	}

	public String getOrigen() {
		return this.origen;
	}

	public String getDestino() {
		return this.destino;
	}

	public Aerolinea getAerolinea() {
		return this.aerolinea;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea= aerolinea;
		
	}
}
