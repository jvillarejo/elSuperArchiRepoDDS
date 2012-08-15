package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import java.util.List;

import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;

public class Buscador<T> {

	private Stream<T> elementosFiltrados;

	public void setElementosFiltrados(Stream<T> elementosFiltrados) {
		this.elementosFiltrados = elementosFiltrados;
	}

	public Stream<T> getElementosFiltrados() {
		return elementosFiltrados;
	}

	public void agregarFiltro(Filtro<T> filtro) {
		Stream<T> elementosVueltosAFiltrar = filtro.filtrar(this.getElementosFiltrados());
		this.setElementosFiltrados(elementosVueltosAFiltrar);
	}

	public void agregarFiltros(Filtro<T>... filtros) {
		for (Filtro<T> filtro : filtros)
			this.agregarFiltro(filtro);
	}

	public List<T> buscar() {
		return this.getElementosFiltrados().toList();
	}
	
	public Buscador(List<T> elementosOriginales) {
		this.setElementosFiltrados(Streams.from(elementosOriginales));
	}

}
