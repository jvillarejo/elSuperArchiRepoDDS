package edu.utn.dds.aterrizar.agencia;

import java.util.ArrayList;
import java.util.List;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.ConsultaAsientos;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;
import edu.utn.dds.aterrizar.vuelo.filtros.BuscadorDeAsientos;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

public class Agencia {

	private List<Aerolinea> aerolineas;

	public Agencia(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
	
	public List<Asiento> buscarAsientos(final Vuelo vuelo, final Usuario usuario, final FiltroAsiento... filtros) {
		List<Asiento> asientos = new ArrayList<Asiento>();		
		for (Aerolinea aerolinea : aerolineas) 
			asientos.addAll(aerolinea.buscarAsientos(vuelo));

		asientos = adaptarPreciosParaUsuario(asientos, usuario);
		
		usuario.registrarConsulta(new ConsultaAsientos(vuelo, filtros));

		BuscadorDeAsientos buscador = new BuscadorDeAsientos(asientos, usuario);
		buscador.agregarFiltros(filtros);
		
		return buscador.buscar();
	}

	public void comprarAsiento(final Asiento asiento, final Usuario usuario) {
		asiento.comprar(usuario); 
		usuario.registrarCompra(asiento);
	}		

	private List<Asiento> adaptarPreciosParaUsuario(List<Asiento> asientos,
			Usuario usuario) {
		List<Asiento> asientosAdaptados= new ArrayList<Asiento>(); 
		for (Asiento asiento : asientos)
			asientosAdaptados.add(asiento.adaptarNuevoAsientoConPrecioPara(usuario));
		
		return asientos;
		
	}
}
