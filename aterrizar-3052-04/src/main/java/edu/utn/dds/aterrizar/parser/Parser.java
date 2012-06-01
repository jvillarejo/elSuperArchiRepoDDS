package edu.utn.dds.aterrizar.parser;
import java.util.ArrayList;
import java.util.List;


import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

public class Parser {

	public List<Asiento> parseDisponibles(String[][] asientos, Vuelo vuelo){
		// esto pasa del array de arrays strings a asientos. 
		Asiento asiento;
		List<Asiento> disponibles = new ArrayList<Asiento>();
		for (int i = 0; i < asientos.length; i++) {
		 asiento=new AsientoLanchitaFactory().create(asientos[i], vuelo);
		 disponibles.add(asiento);
		}
	
		return disponibles;
	}
}
