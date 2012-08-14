package edu.utn.dds.aterrizar.escalas;

import java.util.List;

/**
 * Representa los vuelos con escala, que serían dos vuelos, y debería saber el tiempo de conexión
 * @author clari
 *
 */
public class VueloConEscala extends VueloDirecto{
private List<Vuelo> vuelos;

public VueloConEscala(Vuelo vuelo, Vuelo next) {
	this.vuelos.add(vuelo);
	this.vuelos.add(next);
}

public long getDuration(){
	long total= 0;
	for (Vuelo vuelo:vuelos){
		//falta agregar el tiempo de conexion
		total += vuelo.getDuration();
	}
	return total;
}

}