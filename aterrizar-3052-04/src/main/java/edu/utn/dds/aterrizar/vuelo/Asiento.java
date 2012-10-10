package edu.utn.dds.aterrizar.vuelo;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.Usuario;

@SuppressWarnings("serial")
@Observable
@Transactional
public class Asiento extends Entity{

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

	public void validar(){
		if(!this.ingresoOrigen()){
			throw new UserException("Debe ingresar una ciudad de origen");
		}
		if(!this.ingresoDestino()){
			throw new UserException("Debe ingresar una ciudad destino");
		}
		if(!this.ingresoFechaSalida()){
			throw new UserException("Debe ingresar una fecha de salida");
		}
	}
	
	public boolean ingresoOrigen(){
		return this.vuelo.getOrigen() != null && !this.vuelo.getOrigen().equals("");
	}
	
	public boolean ingresoDestino(){
		return this.vuelo.getDestino() != null && !this.vuelo.getDestino().equals("");
	}
	
	public boolean ingresoFechaSalida(){
		return this.vuelo.getFechaSalida()!= null;
	}
	
	public void comprar(final Usuario usuario) {
		this.aerolinea.comprarAsiento(this, usuario);		
	}
	
	public void reservar(Usuario usuario){
		this.aerolinea.reservarAsiento(this, usuario);
	}
	
	public void sobreReservar(Usuario usuario) {
		this.aerolinea.sobreReservarAsiento(this, usuario);
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
	
	public Aerolinea getAerolinea() {
		return this.aerolinea;
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

	public boolean isReservado() {
		return this.getEstado().equals("R");
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
