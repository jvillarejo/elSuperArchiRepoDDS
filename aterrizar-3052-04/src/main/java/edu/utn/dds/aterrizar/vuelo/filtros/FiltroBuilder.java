package edu.utn.dds.aterrizar.vuelo.filtros;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.*;

public class FiltroBuilder {

	private FiltroAsiento filtro;
	
	public FiltroBuilder(Usuario usuario) {
		this.filtro = usuario.getFiltro();
	}
	
	public FiltroBuilder filtrarPorUbicacion(Ubicacion ubicacion) {
		FiltroPorUbicacion filtro = new FiltroPorUbicacion(ubicacion, this.filtro);
		this.filtro = filtro;
		
		return this;
	}
	
	public FiltroBuilder filtrarPorClase(Clase clase) {
		FiltroPorClase filtro = new FiltroPorClase(clase, this.filtro);
		this.filtro = filtro;
		
		return this;
	}

	public FiltroAsiento build() {
		return this.filtro;
	}	
	
}
