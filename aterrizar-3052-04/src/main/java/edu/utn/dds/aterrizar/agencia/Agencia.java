package edu.utn.dds.aterrizar.agencia;

import java.util.ArrayList;
import java.util.List;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

public class Agencia {

	private List<Aerolinea> aerolineas;

	public Agencia(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
	
	public List<Asiento> buscarAsientos(final Vuelo vuelo, final Usuario usuario) {
		List<Asiento> asientos = new ArrayList<Asiento>();
		
		//TODO implementar filtro segun usuario, para que solo le aparezcan las superofertas a los usuarios vip
		for (Aerolinea aerolinea : aerolineas) {
			asientos.addAll(aerolinea.buscarAsientos(vuelo));
		}
		
		return asientos;
	}
	
	public void comprarAsiento(final Asiento asiento, final Usuario usuario) {
		asiento.comprar(usuario); 
	}
	
}
