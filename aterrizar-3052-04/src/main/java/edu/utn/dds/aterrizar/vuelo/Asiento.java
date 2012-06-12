package edu.utn.dds.aterrizar.vuelo;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.Usuario;

public class Asiento {

	private Vuelo vuelo;
	private Aerolinea aerolinea;
	private Double precio;
	private String codigo;
	//ponele que eventualmente podrían no ser strings 
	private Clase clase;
	private Ubicacion ubicacion;
	private String estado;

	public Asiento() {}
	
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

	public void setClase(Clase clase) {
		this.clase= clase;
		
	}

	public void setEstado(String string) {
		this.estado= string;
		
	}

	public Double getPrecio() {
		return precio;
	}

	public Clase getClase() {
		return this.clase;
	}

	public String getEstado() {
		return this.estado;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
}
