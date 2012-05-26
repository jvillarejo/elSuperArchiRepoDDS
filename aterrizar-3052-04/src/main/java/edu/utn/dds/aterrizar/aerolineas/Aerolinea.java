package edu.utn.dds.aterrizar.aerolineas;

import java.util.List;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.AsientoDisponible;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

public interface Aerolinea {

	List<AsientoDisponible> buscarAsientos(Vuelo vuelo);
	
	void comprarAsiento(AsientoDisponible asientoDisponible, Usuario usuario);
}
