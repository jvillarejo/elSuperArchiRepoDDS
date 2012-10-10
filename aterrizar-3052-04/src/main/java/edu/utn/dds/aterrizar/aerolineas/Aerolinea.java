package edu.utn.dds.aterrizar.aerolineas;

import java.util.List;

import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.manejoDeFechas.DateParser;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

public interface Aerolinea {

	List<VueloDirecto> buscarVuelos(Busqueda busqueda);
	
	void comprarAsiento(Asiento asientoDisponible, Usuario usuario);

	Double getPorcentajeDeVenta();
	
	void reservarAsiento(Asiento asiento, Usuario usuario);
	
	Usuario reservaExpirada(String codigo, String numeroAsiento);
	
	String getName();

	DateParser getFormatter();

	void sobreReservarAsiento(Asiento asiento, Usuario usuario);

}
