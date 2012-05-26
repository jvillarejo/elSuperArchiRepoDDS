package edu.utn.dds.aterrizar.aerolineas;

import java.util.List;

import com.lanchita.AerolineaLanchita;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

public class AerolineaLanchitaWrapper implements Aerolinea {

	private static final double PORCENTAJE_DE_VENTA = 0.15;

	@Override
	public List<Asiento> buscarAsientos(Vuelo vuelo) {
		String[][] asientosDisponibles = AerolineaLanchita.getInstance().getAsientosDisponibles(vuelo.getOrigen(), vuelo.getDestino(), vuelo.getFecha());
		
		return this.parsear(asientosDisponibles);
	}

	private List<Asiento> parsear(String[][] asientosDisponibles) {
		throw new RuntimeException("no implementado todavia");
	}

	@Override
	public void comprarAsiento(Asiento asientoDisponible,
			Usuario usuario) {
		//TODO Agregar manejo de excepcion AsientoLanchitaNoDisponibleException
		AerolineaLanchita.getInstance().comprar(asientoDisponible.getCodigo(), usuario.getDni());
		
	}

	@Override
	public Double getPorcentajeDeVenta() {
		return PORCENTAJE_DE_VENTA; 
	}

}
