package edu.utn.dds.aterrizar.aerolineas;

import java.util.List;

import com.lanchita.AerolineaLanchita;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.AsientoDisponible;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

public class AerolineaLanchitaWrapper implements Aerolinea {

	@Override
	public List<AsientoDisponible> buscarAsientos(Vuelo vuelo) {
		String[][] asientosDisponibles = AerolineaLanchita.getInstance().getAsientosDisponibles(vuelo.getOrigen(), vuelo.getDestino(), vuelo.getFecha());
		
		return this.parsear(asientosDisponibles);
	}

	private List<AsientoDisponible> parsear(String[][] asientosDisponibles) {
		throw new RuntimeException("no implementado todavia");
	}

	@Override
	public void comprarAsiento(AsientoDisponible asientoDisponible,
			Usuario usuario) {
		AerolineaLanchita.getInstance().comprar(asientoDisponible.getCodigo(), usuario.getDni());
		
	}

}
