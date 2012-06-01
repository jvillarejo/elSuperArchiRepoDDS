package edu.utn.dds.aterrizar;

import org.junit.Assert;
import org.junit.Test;

import edu.utn.dds.aterrizar.parser.AsientoLanchitaFactory;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

public class AsientoLanchitaFactoryTest {
	
	@Test
	public void laFactoryCreaUnAsientoCorrectamente() {
		String[] asientoLanchita = { "01202022220202-3", "159.90", "P", "V", "D", "" };
		Asiento asiento = new AsientoLanchitaFactory().create(asientoLanchita, new Vuelo());
		Assert.assertEquals(159.90, asiento.getPrecio(), 0.0);	
		Assert.assertEquals( "01202022220202-3", asiento.getCodigo());
		Assert.assertEquals( "P", asiento.getClase());
		Assert.assertEquals( "V", asiento.getUbicacion());
		Assert.assertEquals( "D", asiento.getEstado());
	}

}
