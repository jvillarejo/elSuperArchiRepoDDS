package edu.utn.dds.aterrizar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.parser.Parser;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

import static org.mockito.Mockito.*;
/**
 * 
 * @author clari
 *
 */
public class AsientoLanchitaFactoryTest {
	private Parser parser;
	
	@Before
	public void setUp(){
		this.parser= new Parser();
	}
	@Test
	public void laFactoryCreaUnAsientoCorrectamente() {
		String[] asientoLanchita = { "01202022220202-3", "159.90", "P", "V", "D", "" };
		Asiento asiento = parser.create(asientoLanchita, mock(Vuelo.class), mock(Aerolinea.class));
		Assert.assertEquals(159.90, asiento.getPrecio(), 0.0);	
		Assert.assertEquals( "3", asiento.getCodigo());
		Assert.assertEquals( Clase.PRIMERA, asiento.getClase());
		Assert.assertEquals( Ubicacion.VENTANILLA, asiento.getUbicacion());
		Assert.assertEquals( "D", asiento.getEstado());
	}

}
