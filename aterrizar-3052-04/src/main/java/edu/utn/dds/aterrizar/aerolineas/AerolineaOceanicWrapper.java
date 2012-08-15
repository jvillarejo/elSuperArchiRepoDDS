package edu.utn.dds.aterrizar.aerolineas;

import java.util.ArrayList;
import java.util.List;

import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;

import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.manejoDeFechas.SimpleDateParser;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.Reserva;

public class AerolineaOceanicWrapper implements Aerolinea {

	private static final Double PORCENTAJE_DE_VENTA = 0.15;
	
	private AerolineaOceanic aerolineaOceanic; 
	private AerolineaOceanicParser parser; 
	private ArrayList<Reserva> reservas;
	
	public AerolineaOceanicWrapper(AerolineaOceanic aerolineaOceanic) {
		this.aerolineaOceanic = aerolineaOceanic;
		this.reservas = new ArrayList<Reserva>();
	}
	
	@Override
	public List<Vuelo> buscarVuelos(Busqueda busqueda) {
		//TODO modificar el origen y destino para que cumpla con Oceanic
		return this.parser.parse(
				aerolineaOceanic.asientosDisponiblesParaOrigenYDestino(transformarCiudad(busqueda.getOrigen()), transformarCiudad(busqueda.getDestino()), SimpleDateParser.LatinAmerican().format(busqueda.getFecha())),
						busqueda,
						this);
	}
	
	private String transformarCiudad(String ciudad) {
		if("LA".equals(ciudad)) {
			return "SLA";
		} else if(ciudad.length() == 2) {
			return ciudad.concat("_");
		}
		
		return ciudad;
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
	
	@Override
	public void reservarAsiento(Asiento asiento, Usuario usuario) {
		Boolean respuesta = aerolineaOceanic.reservar(usuario.getDni(), asiento.getCodigoDeVuelo(), asiento.getNumeroDeAsiento());
		if(respuesta){
		}else{
			throw new RuntimeException("No se pudo reservar el asiento");
		}
	}

}
