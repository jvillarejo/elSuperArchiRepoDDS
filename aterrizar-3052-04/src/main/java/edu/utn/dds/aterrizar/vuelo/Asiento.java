package edu.utn.dds.aterrizar.vuelo;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.Usuario;

public class Asiento {

	private Busqueda vuelo;
	private Aerolinea aerolinea;
	private Double precio;
	private Clase clase;
	private Ubicacion ubicacion;
	private String estado;
	private String codigoDeVuelo;
	private Integer numeroDeAsiento;

	public Asiento() {}
	
	public Asiento(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	public Asiento(Busqueda vuelo, Aerolinea aerolinea) {
		this.vuelo = vuelo;
		this.aerolinea = aerolinea;
	}

	public Busqueda getFlight() {
		return vuelo;
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


	public String getCodigoDeVuelo() {
		return this.codigoDeVuelo;
	}

	public Integer getNumeroDeAsiento() {
		return this.numeroDeAsiento;
	}
	
	public void setCodigoDeVuelo(String codigoDeVuelo) {
		this.codigoDeVuelo = codigoDeVuelo;
	}

	public void setNumeroDeAsiento(Integer numeroDeAsiento) {
		this.numeroDeAsiento = numeroDeAsiento;
	}

}
