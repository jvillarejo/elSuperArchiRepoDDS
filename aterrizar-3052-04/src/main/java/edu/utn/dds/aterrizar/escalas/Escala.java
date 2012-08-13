package edu.utn.dds.aterrizar.escalas;

import java.util.List;

import net.sf.staccatocommons.collections.stream.Streams;
import static net.sf.staccatocommons.lambda.Lambda.lambda;
import static net.sf.staccatocommons.lambda.Lambda.$;

import edu.utn.dds.aterrizar.vuelo.*;
import edu.utn.dds.aterrizar.aerolineas.*;

public class Escala {
	
public List<Asiento> getVuelosDisponiblesDirectosYConEscalas(String origen, String destino, String fecha, Aerolinea aerolinea){
	//buscamos los vuelos directos, como siempre
	List<Asiento>vuelos= aerolinea.buscarAsientos(new Vuelo(origen, destino, fecha));
	//y agregamos los vuelos con escala
	vuelos.addAll(this.buscarVuelosConEscala(origen, destino,fecha,aerolinea));
	return vuelos;
}
public List<Asiento> buscarVuelosConEscala(String origen, String destino,String fecha,Aerolinea aerolinea){
	List<Asiento>vuelosDisponibles= this.buscarVueloSegun(origen, destino,fecha, aerolinea);
	return Streams
			.from(vuelosDisponibles)
			.filter(lambda($(Asiento.class).esEscala($(Asiento.class))))
			.toList();

}

private List<Asiento>buscarVueloSegun(String origen, String destino, String fecha, Aerolinea aerolinea){
	//agregamos los que cumplen solo con el origen
	List<Asiento>vuelosDisponibles= aerolinea.buscarAsientos(new Vuelo(origen, null, fecha));
	//y ahora solo los que cumplen con el destino
	vuelosDisponibles.addAll(aerolinea.buscarAsientos(new Vuelo(null, destino, null)));
	return vuelosDisponibles;
}
}