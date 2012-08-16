package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import java.util.List;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;

public class Query<T> {

	private Stream<T> elementosFiltrados;

	public void setElementosFiltrados(Stream<T> elementosFiltrados) {
		this.elementosFiltrados = elementosFiltrados;
	}

	public Stream<T> getElementosAFiltrar() {
		return elementosFiltrados;
	}

	public void filter(Filtro<T> filtro) {
		Stream<T> elementosVueltosAFiltrar = filtro.filtrar(this.getElementosAFiltrar());
		this.setElementosFiltrados(elementosVueltosAFiltrar);
	}

	public void agregarFiltros(Filtro<T>... filtros) {
		for (Filtro<T> filtro : filtros)
			this.filter(filtro);
	}

	public List<T> execute() {
		return this.getElementosAFiltrar().toList();
	}
	
	public void ordenarPor(CriterioOrden<T> criterioOrden) {
		Stream<T> vuelosOrdenados = criterioOrden.ordenar(this.getElementosAFiltrar());
		this.setElementosFiltrados(vuelosOrdenados);
	}

	public Query(List<T> elementosOriginales) {
		this.setElementosFiltrados(Streams.from(elementosOriginales));
	}

	public void agregarFiltros(List<Filtro<T>> filtros) {
		for(Filtro<T> filtro : filtros)
			this.filter(filtro);
	}

}
