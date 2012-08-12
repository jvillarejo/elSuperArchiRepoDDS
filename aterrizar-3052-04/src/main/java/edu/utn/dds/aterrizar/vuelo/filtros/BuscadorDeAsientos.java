package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class BuscadorDeAsientos {
	private Stream<Asiento> asientosFiltrados;

	public void setAsientosFiltrados(Stream<Asiento> asientosFiltrados) {
		this.asientosFiltrados = asientosFiltrados;
	}

	public Stream<Asiento> getAsientosFiltrados() {
		return asientosFiltrados;
	}

	private void defaultConstructor(List<Asiento> asientos, Usuario usuario) {
		this.setAsientosFiltrados(Streams.from(asientos));
		this.agregarFiltro(usuario.getFiltro());
	}

	public BuscadorDeAsientos(List<Asiento> asientos, Usuario usuario) {
		this.defaultConstructor(asientos, usuario);
	}

	public BuscadorDeAsientos(List<Asiento> asientos, Usuario usuario, FiltroAsiento primerFiltro) {
		this.defaultConstructor(asientos, usuario);
		this.agregarFiltro(primerFiltro);
	}

	public void agregarFiltro(FiltroAsiento filtro) {
		Stream<Asiento> asientosVueltosAFiltrar = filtro.filtrar(this.getAsientosFiltrados());
		this.setAsientosFiltrados(asientosVueltosAFiltrar);
	}

	public void agregarFiltros(FiltroAsiento... filtros) {
		for (FiltroAsiento filtro : filtros)
			this.agregarFiltro(filtro);
	}

	public List<Asiento> buscar() {
		return this.getAsientosFiltrados().toList();
	}
}
