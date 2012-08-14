package edu.utn.dds.aterrizar.escalas;

import java.util.List;

import edu.utn.dds.aterrizar.manejoDeFechas.DateTime;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public interface Vuelo {
	public long getDuration();
	public void setCodigo(String codigo);
	public List<Asiento> getAsientos();
	public Asiento getPrimerAsiento();
	public DateTime getFechaLlegada();
	public DateTime getFechaSalida();
	public String getOrigen();
	public String getDestino();
	
}
