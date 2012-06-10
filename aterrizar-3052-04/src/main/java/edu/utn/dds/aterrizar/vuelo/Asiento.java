package edu.utn.dds.aterrizar.vuelo;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.Usuario;


public class Asiento {

	private Vuelo vuelo;
	private Aerolinea aerolinea;
	private Double precio;
	private String codigo;
	//ponele que eventualmente podrían no ser strings 
	private String clase;
	private String ubicacion;
	private String estado;

	
	public Asiento(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public Vuelo getFlight() {
		return vuelo;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void comprar(final Usuario usuario) {
		//TODO delegar esto en la disponibilidad para que lance excepcion si no esta disponible
		this.aerolinea.comprarAsiento(this, usuario);
		
	} 
	
	public Double precioTotal(Usuario usuario) {
		return this.getPrecio() + this.getPrecio() * this.aerolinea.getPorcentajeDeVenta() + usuario.recargo();
	}

	public void setCodigo(String field) {
		this.codigo= field;
		
	}

	public void setPrecio(Double precio) {
		this.precio= precio;
		
	}

	public void setClase(String string) {
		this.clase= string;
		
	}

	public void setUbicacion(String string) {
		this.ubicacion=string;
		
	}

	public void setEstado(String string) {
		this.estado= string;
		
	}

	public Double getPrecio() {
		return precio;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public String getClase() {
		return this.clase;
	}

	public String getEstado() {
		return this.estado;
	}
}
