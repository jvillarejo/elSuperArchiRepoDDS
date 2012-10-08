package edu.utn.dds.aterrizar.vuelo;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import edu.utn.dds.aterrizar.manejoDeFechas.DateTime;
import edu.utn.dds.aterrizar.manejoDeFechas.SimpleDateParser;

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
	
	private List<Asiento> resultados;
	
	public Busqueda() 
	{
		this.inventarAsientos();
	}
	
	public Busqueda(String origen, String destino,  String fechaSalida, String fechaLlegada, String horaSalida, String horaLlegada){
		this.setOrigen(origen);
		this.setDestino(destino);
		this.setFechaSalida(new DateTime(SimpleDateParser.LatinAmerican(), fechaSalida));
		this.setFechaLlegada(new DateTime(SimpleDateParser.LatinAmerican(), fechaLlegada));
		this.setHoraSalida(horaSalida);
		this.setHoraLlegada(horaLlegada);
	}

	private void inventarAsientos() {
		Asiento asiento1 = new Asiento();
		asiento1.setPrecio(100.0);
		asiento1.setNumeroDeAsiento(1);
		asiento1.setUbicacion(Ubicacion.PASILLO);
		asiento1.setClase(Clase.PRIMERA);

		Asiento asiento2 = new Asiento();
		asiento2.setPrecio(200.0);
		asiento2.setNumeroDeAsiento(2);
		asiento2.setUbicacion(Ubicacion.VENTANILLA);
		asiento2.setClase(Clase.TURISTA);
		
		this.setResultados(Arrays.asList(asiento1, asiento2));
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
		return fechaSalida;
	}

	public void setFechaSalida(DateTime fechaSalida) {
		if(fechaSalida != null)
		this.fechaSalida = fechaSalida;
		else this.fechaSalida= null;
	}

	public DateTime getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(DateTime fechaLlegada) {
		if(fechaLlegada != null)
			this.fechaLlegada = fechaLlegada;
			else this.fechaLlegada= null;
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

	public List<Asiento> getResultados() {
		return resultados;
	}

	public void setResultados(List<Asiento> resultados) {
		this.resultados = resultados;
	}

}

