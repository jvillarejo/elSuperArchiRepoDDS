package edu.utn.dds.aterrizar.ui.busqueda;

import com.uqbar.commons.collections.Transformer;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class UbicacionToStringTransformer implements
		Transformer<Asiento, String> {

	@Override
	public String transform(Asiento asiento) {
		return toPascalCase(asiento.getUbicacion().name());
	}
	
	protected String toPascalCase(String original) {
		return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase(); 
	}

}
