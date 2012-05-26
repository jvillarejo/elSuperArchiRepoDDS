package edu.utn.dds.aterrizar.vuelo;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.Usuario;


public class Asiento {

	//TODO Agregar estadoDelAsiento (Disponibilidad), clase, ubicacion
	private Vuelo vuelo;
	private Aerolinea aerolinea;
	private Double precio;
	private String codigo;
	
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
		return this.precio + this.precio * this.aerolinea.getPorcentajeDeVenta() + usuario.recargo();
	}
}
