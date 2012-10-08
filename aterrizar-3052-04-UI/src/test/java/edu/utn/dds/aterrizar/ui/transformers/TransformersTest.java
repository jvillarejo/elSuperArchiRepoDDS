package edu.utn.dds.aterrizar.ui.transformers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

public class TransformersTest {

	@Test
	public void UbicacionToStringTransformerAdaptaAsientoEnElPasilloAPasillo() {
		Asiento asientoEnElPasillo = new Asiento();
		asientoEnElPasillo.setUbicacion(Ubicacion.PASILLO);
		
		assertEquals("Pasillo", new UbicacionToStringTransformer().transform(asientoEnElPasillo));
	}

	@Test
	public void ClaseToStringTransformerAdaptaAsientoEnPrimeraAPrimera() {
		Asiento asientoEnPrimera = new Asiento();
		asientoEnPrimera.setClase(Clase.PRIMERA);
		
		assertEquals("Primera", new ClaseToStringTransformer().transform(asientoEnPrimera));
	}
	
}
