package edu.utn.dds.aterrizar.ui.transformers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.utn.dds.aterrizar.vuelo.Ubicacion;

public class EnumUtilsTest {

	@Test
	public void CanCorrectlyConvertUbicacionToPascalCaseValue() {
		assertEquals("Pasillo", EnumUtils.toPascalCase(Ubicacion.PASILLO));
	}
	
}
