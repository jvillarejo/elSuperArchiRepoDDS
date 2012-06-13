package edu.utn.dds.aterrizar.agencia;

import java.util.ArrayList;
import java.util.List;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.ConsultaAsientos;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

public class Agencia {

	private List<Aerolinea> aerolineas;

	public Agencia(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
	
	public List<Asiento> buscarAsientos(final Vuelo vuelo, final Usuario usuario, final FiltroAsiento filtro) {
		List<Asiento> asientos = new ArrayList<Asiento>();
		
		for (Aerolinea aerolinea : aerolineas) {
			asientos.addAll(aerolinea.buscarAsientos(vuelo));
		}
		
		usuario.registrarConsulta(new ConsultaAsientos(vuelo, filtro));
		
		return filtro.filtrar(asientos);	
	}
	
	public void comprarAsiento(final Asiento asiento, final Usuario usuario) {
		asiento.comprar(usuario); 
	}		
}
