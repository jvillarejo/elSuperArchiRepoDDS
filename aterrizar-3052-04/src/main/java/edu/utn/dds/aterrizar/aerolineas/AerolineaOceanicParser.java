package edu.utn.dds.aterrizar.aerolineas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oceanic.AsientoDTO;

import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

public class AerolineaOceanicParser {

	public List<VueloDirecto> parse(List<AsientoDTO> asientosDisponibles,
			Busqueda busqueda, Aerolinea aerolinea) {
		
		List<VueloDirecto> vuelos = new ArrayList<VueloDirecto>();
		
		for (AsientoDTO asientoDTO : asientosDisponibles) { 
			Asiento asiento = this.parse(asientoDTO, aerolinea, busqueda);
			//TODO No me gusta esto muchos parametros ver de refactorizar
			this.addOrCreate(vuelos, asiento, asientoDTO, aerolinea);
		}
		
		
		return vuelos; 
		
	}

	private void addOrCreate(List<VueloDirecto> vuelos, Asiento asiento, AsientoDTO asientoDTO, Aerolinea aerolinea) {
		try { 
			Vuelo vuelo = findVuelo(vuelos, asiento.getCodigoDeVuelo());
			vuelo.getAsientos().add(asiento);
		} catch (VueloNotFoundException e) { 
			//TODO No me gusta esto muchos parametros ver de refactorizar
			VueloDirecto vuelo = new VueloDirecto(asiento.getFlight().getOrigen(), 
					asiento.getFlight().getDestino(), 
					asientoDTO.getFechaDeSalida(), 
					asientoDTO.getFechaDeLlegada(),
					aerolinea);
			
			vuelo.setCodigo(asiento.getCodigoDeVuelo());
			vuelo.setDestino(asiento.getFlight().getDestino());
			vuelo.setOrigen(asiento.getFlight().getOrigen());
			vuelo.agregarAsiento(asiento);
			vuelos.add(vuelo);
		}
		
	}

	private VueloDirecto findVuelo(List<VueloDirecto> vuelos, String codigoDeVuelo) {
		for (VueloDirecto vuelo : vuelos) {
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
		Map<String, String > map = new HashMap<String, String>();
		
		map.put("ventana", "VENTANILLA");
		map.put("pasillo", "PASILLO");
		map.put("centro", "CENTRO");
		
		return Ubicacion.valueOf(map.get(ubicacion));
	}

	private Clase parseClase(String clase) {
		Map<String, String > map = new HashMap<String, String>();
		
		map.put("primera clase", "PRIMERA");
		map.put("turista", "TURISTA");
		map.put("ejecutiva", "EJECUTIVA");
		
		return Clase.valueOf(map.get(clase));
	}

	private class VueloNotFoundException extends RuntimeException {

		public VueloNotFoundException(String message) {
			super(message);
		}

		private static final long serialVersionUID = 1L; 
		
	}

	
}
