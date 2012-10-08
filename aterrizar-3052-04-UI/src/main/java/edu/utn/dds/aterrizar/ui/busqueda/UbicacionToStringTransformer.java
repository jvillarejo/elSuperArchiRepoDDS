package edu.utn.dds.aterrizar.ui.busqueda;

import com.uqbar.commons.collections.Transformer;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class UbicacionToStringTransformer implements
		Transformer<Asiento, String> {

	@Override
	public String transform(Asiento asiento) {		
		return EnumUtils.toPascalCase(asiento.getUbicacion());
	}

}
