package edu.utn.dds.aterrizar.aerolineas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;
import edu.utn.dds.aterrizar.vuelo.Vuelo;
import edu.utn.frba.dds.manejoFechas.domain.SimpleDateParser;

public class AerolineaOceanicWrapper implements Aerolinea {

	private static final Double PORCENTAJE_DE_VENTA = 0.15;
	
	private AerolineaOceanic aerolineaOceanic; 
	
	public AerolineaOceanicWrapper(AerolineaOceanic aerolineaOceanic) {
		this.aerolineaOceanic = aerolineaOceanic;
	}
	
	@Override
	public List<Asiento> buscarAsientos(Vuelo vuelo) {
		return crearAsientos(
				aerolineaOceanic.asientosDisponiblesParaOrigen(
						vuelo.getOrigen(), 
						SimpleDateParser.LATIN_AMERICAN.format(vuelo.getFecha())), 
				vuelo);
		
	}

	private List<Asiento> crearAsientos(
			List<AsientoDTO> asientosDisponiblesParaOrigen, Vuelo vuelo) {
		List<Asiento> asientosDisponibles = new ArrayList<Asiento>();
		
		for (AsientoDTO asientoDTO : asientosDisponiblesParaOrigen) { 
			asientosDisponibles.add(crearAsiento(asientoDTO, vuelo));
		}
		
		return asientosDisponibles ;
	}

	private Asiento crearAsiento(AsientoDTO asientoDTO, Vuelo vuelo) {
		Asiento asiento = new Asiento(vuelo, this);
		asiento.setCodigoDeVuelo(asientoDTO.getCodigoDeVuelo());
		asiento.setNumeroDeAsiento(asientoDTO.getNumeroDeAsiento());
		//Esto no es copado habría que cambiar el manejo de precios para usar BigDecimal, pero lleva mucho laburo por ahora
		asiento.setPrecio(Double.valueOf(asientoDTO.getPrecio().doubleValue()));
		
		
		asiento.setUbicacion(transformarUbicacion(asientoDTO.getUbicacion()));
		asiento.setClase(transformarClase(asientoDTO.getClase()));
		
		
		return asiento ;
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
