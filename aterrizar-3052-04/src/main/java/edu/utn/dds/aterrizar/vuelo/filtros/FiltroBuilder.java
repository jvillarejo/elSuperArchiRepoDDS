package edu.utn.dds.aterrizar.vuelo.filtros;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.*;

/**
 * Es un builder para FiltroAsiento
 * @author faloi
 */
public class FiltroBuilder {

	private FiltroAsiento filtro;
	
	/**
	 * @param usuario El usuario para el cual se van a crear los filtros.
	 */
	public FiltroBuilder(Usuario usuario) {
		this.filtro = usuario.getFiltro();
	}
	
	public FiltroBuilder filtrarPorUbicacion(Ubicacion ubicacion) {
		this.filtro = new FiltroPorUbicacion(ubicacion, this.filtro);
		
		return this;
	}
	
	public FiltroBuilder filtrarPorClase(Clase clase) {
		this.filtro = new FiltroPorClase(clase, this.filtro);
		
		return this;
	}

	public FiltroAsiento build() {
		return this.filtro;
	}	
	
}
