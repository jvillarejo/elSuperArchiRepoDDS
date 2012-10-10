package edu.utn.dds.aterrizar.ui.transformers;

import com.uqbar.commons.collections.Transformer;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public class AsientoToCodigoVueloTransformer implements
		Transformer<Asiento, String> {

	@Override
	public String transform(Asiento asiento) {
		return asiento.getFlight().getCodigo();
	}

}
