package edu.utn.dds.aterrizar.parser;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

public class Parser {

	public List<Asiento> parseDisponibles(String[][] asientos, Vuelo vuelo){
		// esto pasa del array de arrays strings a asientos. 
		Asiento asiento;
		List<Asiento> disponibles = new ArrayList<Asiento>();
		for (int i = 0; i < asientos.length; i++) {
		 asiento=this.create(asientos[i], vuelo);
		 disponibles.add(asiento);
		}
	
		return disponibles;
	}
	
	
	public Double adaptToDouble(String string){
		return new Double(string);
		}
	
	/**
	 * Un FactoryMethod para generar nuevas instancias de Asiento seteadas.
	 * @param asientoLanchita
	 * @param vuelo
	 * @return un </code>Asiento</code> con sus fields seteados.
	 */
	public Asiento create (String[] asientoLanchita, Vuelo vuelo){
	try{	
		vuelo.setCode(this.getFlightCode(asientoLanchita[0]));
		Asiento asiento = new Asiento(vuelo);
		//TODO: crear el vuelo con lo que está antés del guión en el primer string.
				asiento.setCodigo(this.getSeatCode(asientoLanchita[0]));
				asiento.setPrecio(this.adaptToDouble(asientoLanchita[1]));
				asiento.setClase(this.stringToClase(asientoLanchita[2]));
				asiento.setUbicacion(this.stringToUbicacion(asientoLanchita[3]));
				asiento.setEstado(asientoLanchita[4]);
		
		return asiento;
	} catch(RuntimeException e){
		throw new ParserException("Error en el parseo", e);
	}
	}

	private String getSeatCode(String string){
		String sc= string.substring(15);
		return sc;
	}

	private String getFlightCode(String string) {
		String fc= string.substring(0, 13);
		return fc;
	}
	
	private Ubicacion stringToUbicacion(final String ubicacion)
	{
		Hashtable<String, String> ubicacionMapper = new Hashtable<String, String>();
		ubicacionMapper.put("V", "VENTANILLA");
		ubicacionMapper.put("C", "CENTRO");
		ubicacionMapper.put("P", "PASILLO");
		
		String ubicacionValue = ubicacionMapper.get(ubicacion);
		return Ubicacion.valueOf(ubicacionValue);
	}
	
	private Clase stringToClase(final String clase)
	{
		Hashtable<String, String> claseMapper = new Hashtable<String, String>();
		claseMapper.put("P", "PRIMERA");
		claseMapper.put("E", "EJECUTIVO");
		claseMapper.put("T", "TURISTA");
		
		String claseValue = claseMapper.get(clase);
		return Clase.valueOf(claseValue);
	}

}
