package edu.utn.dds.aterrizar.escalas;

import java.util.List;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.manejoDeFechas.DateTime;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public interface Vuelo {
	public long getDuration();
	public void setCodigo(String codigo);
	public String getCodigo();
	public List<Asiento> getAsientos();
	public Asiento getPrimerAsiento();
	public DateTime getFechaLlegada();
	public DateTime getFechaSalida();
	public String getOrigen();
	public String getDestino();
	public Aerolinea getAerolinea();
	public Asiento getAsientoMasBarato();
	
}
