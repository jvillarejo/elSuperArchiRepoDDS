package edu.utn.dds.aterrizar.aerolineas;

import java.util.List;

import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

public class AerolineaOceanicWrapper implements Aerolinea {

	private static final Double PORCENTAJE_DE_VENTA = 0.15;
	
	private AerolineaOceanic aerolineaOceanic; 
	private AerolineaOceanicParser parser; 
	
	public AerolineaOceanicWrapper(AerolineaOceanic aerolineaOceanic) {
		this.aerolineaOceanic = aerolineaOceanic;
	}
	
	@Override
	public List<Asiento> buscarAsientos(Busqueda vuelo) {
		// TODO pensar la busqueda ya que oceanic tiene dos métodos
		return null;
	}

	@Override
	public void comprarAsiento(Asiento asientoDisponible, Usuario usuario) {
		Boolean fueComprado = aerolineaOceanic.comprarSiHayDisponibilidad(usuario.getDni(), asientoDisponible.getCodigoDeVuelo(), asientoDisponible.getNumeroDeAsiento());
		if(!fueComprado) {
			throw new AsientoNoDisponibleException("No se pudo comprar el asiento");
		}
		
	}

	@Override
	public Double getPorcentajeDeVenta() {
		return PORCENTAJE_DE_VENTA;
	}

}
