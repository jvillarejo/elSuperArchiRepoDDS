package edu.utn.dds.aterrizar.aerolineas;

import java.util.ArrayList;
import java.util.List;

import com.oceanic.AsientoDTO;

import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

public class AerolineaOceanicParser {

	public List<Vuelo> parse(List<AsientoDTO> asientosDisponibles,
			Busqueda busqueda, Aerolinea aerolinea) {
		
		List<Vuelo> vuelos = new ArrayList<Vuelo>();
		
		for (AsientoDTO asientoDTO : asientosDisponibles) { 
			Asiento asiento = this.parse(asientoDTO, aerolinea, busqueda);
			this.addOrCreate(vuelos, asiento);
		}
		
		
		return vuelos; 
		
	}

	private void addOrCreate(List<Vuelo> vuelos, Asiento asiento) {
		try { 
			Vuelo vuelo = findVuelo(vuelos, asiento.getCodigoDeVuelo());
			vuelo.getAsientos().add(asiento);
		} catch (VueloNotFoundException e) { 
			VueloDirecto vuelo = new VueloDirecto();
			
			vuelo.setCodigo(asiento.getCodigoDeVuelo());
			vuelo.setDestino(asiento.getFlight().getDestino());
			vuelo.setOrigen(asiento.getFlight().getOrigen());
			vuelo.agregarAsiento(asiento);
		}
		
	}

	private Vuelo findVuelo(List<Vuelo> vuelos, String codigoDeVuelo) {
		for (Vuelo vuelo : vuelos) {
			if(vuelo.getCodigo().equals(codigoDeVuelo)) {
				return vuelo;
			}
 		}
		
		throw new VueloNotFoundException("No se creo el vuelo");
	}

	public Asiento parse(AsientoDTO asientoDTO, Aerolinea aerolinea, Busqueda busqueda) {
		Asiento asiento = new Asiento(busqueda, aerolinea);
		
		asiento.setCodigoDeVuelo(asientoDTO.getCodigoDeVuelo());
		//TODO Esto no está bueno BigDecimal es mejor para el manejo de plata pero hay que cambiar todo
		asiento.setPrecio(asientoDTO.getPrecio().doubleValue());
		asiento.setNumeroDeAsiento(asientoDTO.getNumeroDeAsiento());
		asiento.setClase(parseClase(asientoDTO.getClase()));
		asiento.setUbicacion(parseUbicacion(asientoDTO.getUbicacion()));
		//TODO Usar enum para esto también, ver con Ari
		asiento.setEstado(asientoDTO.getReservado() ? "R" : "D");
		
		return asiento ;
	}
	
	private Ubicacion parseUbicacion(String ubicacion) {
		return Ubicacion.valueOf(ubicacion.toUpperCase());
	}

	private Clase parseClase(String clase) {
		return Clase.valueOf(clase.substring(0, clase.indexOf(" ")).toUpperCase());
	}

	private class VueloNotFoundException extends RuntimeException {

		public VueloNotFoundException(String message) {
			super(message);
		}

		private static final long serialVersionUID = 1L; 
		
	}

	
}
