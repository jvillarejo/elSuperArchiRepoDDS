package edu.utn.dds.aterrizar.vuelo;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.Usuario;

public class Asiento {

	private Vuelo vuelo;
	private Aerolinea aerolinea;
	private Double precio;
	private String codigo;
	private Clase clase;
	private Ubicacion ubicacion;
	private String estado;

	public Asiento() {}
	
	public Asiento(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	public Asiento(Vuelo vuelo, Aerolinea aerolinea) {
		this.vuelo = vuelo;
		this.aerolinea = aerolinea;
	}

	public Vuelo getFlight() {
		return vuelo;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void comprar(final Usuario usuario) {
		this.aerolinea.comprarAsiento(this, usuario);		
	} 
	
	public Boolean esSuperOferta() {
		return 
			this.getClase() == Clase.PRIMERA && this.getPrecio() < 8000
			||
			this.getClase() == Clase.EJECUTIVA && this.getPrecio() < 4000;
	}
	
	public void setCodigo(String string) {
		this.codigo= string;
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

	public void adaptarPrecioPara(Usuario usuario) {
		this.setPrecio(this.precioTotal(usuario));
	}

	private Double precioTotal(Usuario usuario) {
		return this.getPrecio() + this.getPrecio() * this.aerolinea.getPorcentajeDeVenta() + usuario.getRecargo();
	}

	public Asiento adaptarNuevoAsientoConPrecioPara(Usuario usuario) {
		Asiento asientoAdaptado = new Asiento(this.vuelo, this.aerolinea);
		asientoAdaptado.adaptarPrecioPara(usuario);
		return asientoAdaptado;
	}
}
