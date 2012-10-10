package edu.utn.dds.aterrizar.parser;
import static net.sf.staccatocommons.lambda.Lambda.$;
import static net.sf.staccatocommons.lambda.Lambda.lambda;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.sf.staccatocommons.collections.stream.Streams;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

public class Parser {

private 	List<VueloDirecto> disponibles = new ArrayList<VueloDirecto>();
	
public List<VueloDirecto> parseDisponibles(String[][] asientos, Busqueda busqueda, Aerolinea aerolinea){
		// esto pasa del array de arrays strings a asientos. 
		VueloDirecto vuelo;
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
		//ahora el asiento es algo como
		// "01202022220202-3", "159.90", "P", "V", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012"
		VueloDirecto vuelo;
		try{
        String flightCode = this.getFlightCode(asientoLanchita[0]);
		busqueda.setCode(flightCode);
		Asiento asiento = new Asiento(busqueda, aerolinea);
		asiento.validar();
		if (verificarSiExisteElVuelo(flightCode)){
			 vuelo = new VueloDirecto(asientoLanchita[8],asientoLanchita[9],asientoLanchita[10] , asientoLanchita[11], aerolinea);		
			 parseCodigoDeVueloYNumeroDeAsiento(asiento, asientoLanchita[0], vuelo);
		}else {
			vuelo =  getVueloConCodigo(flightCode);
					this.parseNumeroDeAsiento(asiento, asientoLanchita[0]);
		}
				asiento.setPrecio(this.adaptToDouble(asientoLanchita[1]));
				asiento.setClase(this.stringToClase(asientoLanchita[2]));
				asiento.setUbicacion(this.stringToUbicacion(asientoLanchita[3]));
				asiento.setEstado(asientoLanchita[4]);
				vuelo.agregarAsiento(asiento);
				return vuelo;
	}
		catch(RuntimeException e){
		throw new ParserException("Error en el parseo", e);
	}

	}


	private VueloDirecto getVueloConCodigo(String flightCode) {
		return Streams
				.from(disponibles)
				.find(lambda($(VueloDirecto.class).getCodigo().equals(flightCode)));
	}


	private boolean verificarSiExisteElVuelo(String flightCode) {
		return Streams
		.from(disponibles)
		.filter(lambda($(VueloDirecto.class).getCodigo()).equal(flightCode))
		.isEmpty();
	}

	private void parseCodigoDeVueloYNumeroDeAsiento(Asiento asiento,
			String codigoLanchita, Vuelo vuelo) {
		String[] values = codigoLanchita.split("-");
		vuelo.setCodigo(values[0]);
		asiento.setCodigoDeVuelo(values[0]);
		asiento.setNumeroDeAsiento(Integer.valueOf(values[1]));

	}
	private void parseNumeroDeAsiento(Asiento asiento,
			String codigoLanchita) {
		String[] values = codigoLanchita.split("-");
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
