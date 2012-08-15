package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.Buscador;

public class BuscadorDeAsientos extends Buscador<Asiento> {
	private void defaultConstructor(List<Asiento> asientos, Usuario usuario) {
		this.agregarFiltro(usuario.getFiltro());
	}

	public BuscadorDeAsientos(List<Asiento> asientos, Usuario usuario) {
		super(asientos);
		this.defaultConstructor(asientos, usuario);
	}

	public BuscadorDeAsientos(List<Asiento> asientos, Usuario usuario, Filtro<Asiento> primerFiltro) {
		super(asientos);
		this.defaultConstructor(asientos, usuario);
		this.agregarFiltro(primerFiltro);
	}
	
}
