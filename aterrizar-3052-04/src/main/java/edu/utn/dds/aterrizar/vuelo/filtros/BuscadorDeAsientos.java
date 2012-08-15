package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.List;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.CriterioOrdenAsiento;

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

	public BuscadorDeAsientos(List<Asiento> asientos, Usuario usuario, Filtro<Asiento> primerFiltro) {
		this.defaultConstructor(asientos, usuario);
		this.agregarFiltro(primerFiltro);
	}

	public void agregarFiltro(Filtro<Asiento> filtro) {
		Stream<Asiento> asientosVueltosAFiltrar = filtro.filtrar(this.getAsientosFiltrados());
		this.setAsientosFiltrados(asientosVueltosAFiltrar);
	}

	public void agregarFiltros(Filtro<Asiento>... filtros) {
		for (Filtro<Asiento> filtro : filtros)
			this.agregarFiltro(filtro);
	}
	
	public void ordenarPor(CriterioOrdenAsiento criterio) {
		Stream<Asiento> asientosOrdenados = criterio.ordenar(this.getAsientosFiltrados());
		this.setAsientosFiltrados(asientosOrdenados);
	}

	public List<Asiento> buscar() {
		return this.getAsientosFiltrados().toList();
	}
}
