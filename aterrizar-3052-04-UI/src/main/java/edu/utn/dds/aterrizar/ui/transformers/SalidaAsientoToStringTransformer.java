package edu.utn.dds.aterrizar.ui.transformers;

import com.uqbar.commons.collections.Transformer;

import edu.utn.dds.aterrizar.manejoDeFechas.DateParser;
import edu.utn.dds.aterrizar.manejoDeFechas.SimpleDateParser;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class SalidaAsientoToStringTransformer implements
		Transformer<Asiento, String> {

	private DateParser dateParser;

	public SalidaAsientoToStringTransformer() {
		this.dateParser = SimpleDateParser.LatinAmerican();
	}
	
	public SalidaAsientoToStringTransformer(SimpleDateParser dateParser) {
		this.dateParser = dateParser;
	}
	
	@Override
	public String transform(Asiento asiento) {
		return this.dateParser.toString(asiento.getFlight().getFechaSalida());
	}

}
