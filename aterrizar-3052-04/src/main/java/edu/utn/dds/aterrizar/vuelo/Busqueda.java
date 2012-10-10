package edu.utn.dds.aterrizar.vuelo;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.manejoDeFechas.DateTime;
import edu.utn.dds.aterrizar.manejoDeFechas.SimpleDateParser;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.CriterioOrden;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.OrdenDefault;

/**
 * Representa los parámetros de consulta de busqueda
 * @author clari, juani
 *
 */
@Observable
public class Busqueda {

	private String codigo;
	private String origen;
	private String destino;
	private DateTime fechaSalida;
	private DateTime fechaLlegada;
	private String horaSalida;
	private String horaLlegada;
	
	private List<Filtro<Asiento>> filtros;
	private CriterioOrden<Vuelo> criterioOrdenamiento;
	
	private void init() {
		this.filtros = new ArrayList<Filtro<Asiento>>();
		this.setCriterioOrdenamiento(new OrdenDefault());
	}
	
	public Busqueda() {
		this.init();
	}
	
	public Busqueda(String origen, String destino,  String fechaSalida, String fechaLlegada, String horaSalida, String horaLlegada){
		this.init();
		
		this.setOrigen(origen);
		this.setDestino(destino);
		this.setFechaSalida(fechaSalida);
		this.setFechaLlegada(fechaLlegada);
		this.setHoraSalida(horaSalida);
		this.setHoraLlegada(horaLlegada);
	}

	private DateTime setFecha(String fecha) {
		return (fecha != null) ?  new DateTime(SimpleDateParser.LatinAmerican(), fecha): null;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getOrigen() {
		return this.origen;
	}

	public String getDestino() {
		return this.destino;
	}

	
	public void setCode(String code) {
		this.codigo= code;
		
	}

	public DateTime getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida= this.setFecha(fechaSalida);
	}

	public DateTime getFechaLlegada() {
		return this.fechaLlegada;
	}


	public void setFechaLlegada(String fechaLlegada) {
			this.setFecha(fechaLlegada);
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public List<Filtro<Asiento>> getFiltros() {
		return filtros;
	}

	public Busqueda agregarFiltro(Filtro<Asiento> filtro) {
		this.getFiltros().add(filtro);
		return this;
	}

	public CriterioOrden<Vuelo> getCriterioOrdenamiento() {
		return criterioOrdenamiento;
	}

	public void setCriterioOrdenamiento(CriterioOrden<Vuelo> criterioOrdenamiento) {
		this.criterioOrdenamiento = criterioOrdenamiento;
	}
}

