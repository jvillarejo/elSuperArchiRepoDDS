package edu.utn.dds.aterrizar.parser;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

/**
 * Representa una AbstractFactory para crear Asientos a partir de un array de arrays de String.
 * @author clari
 * 
 */
public class AsientoLanchitaFactory {

	/**
	 * @param  string
	 * @return un Double cuyo valor es el representado por el String
	 */
	public Double adaptToDouble(String string){
		return new Double(string);
		}
	
	/**
	 * Un FactoryMethod para generar nuevas instancias de Asiento seteadas.
	 * @param asientoLanchita
	 * @param vuelo
	 * @return un </code>Asiento</code> con sus fields seteados.
	 * @throws ParserException si se produce un error.
	 */
	public Asiento create (String[] asientoLanchita, Vuelo vuelo){
		Asiento asiento = new Asiento(vuelo);
		//por ahora, hardcodeo ¬¬ TODO: pensar una alternativa más extensible.
		//probablemente lo ideal sería tener builders para cada posible abstract factory.
		try{		
				asiento.setCodigo(asientoLanchita[0]);
				asiento.setPrecio(this.adaptToDouble(asientoLanchita[1]));
				asiento.setClase(asientoLanchita[2]);
				asiento.setUbicacion(asientoLanchita[3]);
				asiento.setEstado(asientoLanchita[4]);
		}
		catch(RuntimeException e){
			throw new ParserException();
			}
			
		return asiento;
	}
}


