package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import java.util.List;

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

	public Query<T> addFilter(Filtro<T> filtro) {
		Stream<T> elementosVueltosAFiltrar = filtro.filtrar(this.getElementosAFiltrar());
		this.setElementosFiltrados(elementosVueltosAFiltrar);
		
		return this;
	}

	public List<T> execute() {
		return this.getElementosAFiltrar().toList();
	}
	
	public Query<T> addOrderByCriteria(CriterioOrden<T> criterioOrden) {
		Stream<T> vuelosOrdenados = criterioOrden.ordenar(this.getElementosAFiltrar());
		this.setElementosFiltrados(vuelosOrdenados);
		
		return this;
	}

	public Query(List<T> elementosOriginales) {
		this.setElementosFiltrados(Streams.from(elementosOriginales));
	}
}
