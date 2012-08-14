package edu.utn.dds.aterrizar.parser;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

public class Parser {

	public List<Vuelo> parseDisponibles(String[][] asientos, Busqueda busqueda, Aerolinea aerolinea){
		// esto pasa del array de arrays strings a asientos. 
		VueloDirecto vuelo;
		List<Vuelo> disponibles = new ArrayList<Vuelo>();
		for (int i = 0; i < asientos.length; i++) {
		 vuelo=this.create(asientos[i], busqueda, aerolinea);
		 disponibles.add(vuelo);
		}
	
		return disponibles;
	}
	
	
	public Double adaptToDouble(String string){
		return new Double(string);
		}
	
	/**
	 * Un FactoryMethod para generar nuevas instancias de Asiento seteadas.
	 * @param asientoLanchita
	 * @param busqueda
	 * @return un </code>Asiento</code> con sus fields seteados.
	 */
	public VueloDirecto create (String[] asientoLanchita, Busqueda busqueda, Aerolinea aerolinea){
	try{	
		busqueda.setCode(this.getFlightCode(asientoLanchita[0]));
		VueloDirecto vuelo = new VueloDirecto();
		vuelo. setOrigen(busqueda.getOrigen());
		vuelo.setDestino(busqueda.getDestino());
		//TODO: falta ver que hacemos con la fecha
		Asiento asiento = new Asiento(busqueda, aerolinea);

				parseCodigoDeVueloYNumeroDeAsiento(asiento, asientoLanchita[0], vuelo);
				asiento.setPrecio(this.adaptToDouble(asientoLanchita[1]));
				asiento.setClase(this.stringToClase(asientoLanchita[2]));
				asiento.setUbicacion(this.stringToUbicacion(asientoLanchita[3]));
				asiento.setEstado(asientoLanchita[4]);
				vuelo.agregarAsiento(asiento);
		
		return vuelo;
	} catch(RuntimeException e){
		throw new ParserException("Error en el parseo", e);
	}

	}

	private void parseCodigoDeVueloYNumeroDeAsiento(Asiento asiento,
			String codigoLanchita, Vuelo vuelo) {
		String[] values = codigoLanchita.split("-");
		vuelo.setCodigo(values[0]);
		asiento.setNumeroDeAsiento(Integer.valueOf(values[1]));

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
		claseMapper.put("E", "EJECUTIVA");
		claseMapper.put("T", "TURISTA");
		
		String claseValue = claseMapper.get(clase);
		return Clase.valueOf(claseValue);
	}

}
