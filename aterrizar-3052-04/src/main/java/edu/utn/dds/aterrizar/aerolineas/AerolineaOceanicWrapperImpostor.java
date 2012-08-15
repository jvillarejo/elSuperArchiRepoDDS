package edu.utn.dds.aterrizar.aerolineas;

import java.util.List;

import com.oceanic.AerolineaOceanic;

import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.manejoDeFechas.SimpleDateParser;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

public class AerolineaOceanicWrapperImpostor extends AerolineaWrapper implements Aerolinea {

	private static final Double PORCENTAJE_DE_VENTA = 0.15;
	
	private AerolineaOceanicImpostor aerolineaOceanicImpostor; 
	private AerolineaOceanicParser parser; 
	
	public AerolineaOceanicWrapperImpostor(AerolineaOceanicImpostor aerolineaOceanicImpostor) {
		super();
		this.aerolineaOceanicImpostor = aerolineaOceanicImpostor;
	}
	
	@Override
	public List<VueloDirecto> buscarVuelos(Busqueda busqueda) {
		//TODO modificar el origen y destino para que cumpla con Oceanic
		return this.parser.parse(
				aerolineaOceanicImpostor.asientosDisponiblesParaOrigenYDestino(transformarCiudad(busqueda.getOrigen()), transformarCiudad(busqueda.getDestino()), SimpleDateParser.LatinAmerican().format(busqueda.getFecha())),
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
		Boolean fueComprado = aerolineaOceanicImpostor.comprarSiHayDisponibilidad(usuario.getDni(), asientoDisponible.getCodigoDeVuelo(), asientoDisponible.getNumeroDeAsiento());
		if(fueComprado) {
			super.comprarAsiento(asientoDisponible, usuario);
			asientoDisponible.setEstado("C");
		}else{
			throw new AsientoNoDisponibleException("No se pudo comprar el asiento");
		}
		
	}

	@Override
	public Double getPorcentajeDeVenta() {
		return PORCENTAJE_DE_VENTA;
	}
	
	@Override
	public void reservarAsiento(Asiento asiento, Usuario usuario) {
		aerolineaOceanicImpostor.reservar(usuario.getDni(), asiento.getCodigoDeVuelo(), asiento.getNumeroDeAsiento());
		super.reservarAsiento(asiento, usuario);
	}

}