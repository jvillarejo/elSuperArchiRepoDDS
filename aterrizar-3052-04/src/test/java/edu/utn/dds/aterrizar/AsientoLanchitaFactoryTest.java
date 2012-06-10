package edu.utn.dds.aterrizar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.parser.Parser;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

import static org.mockito.Mockito.*;

public class AsientoLanchitaFactoryTest {
	private Parser parser;
	
	@Before
	public void setUp(){
		//por alguna razón no pasa el test si uso un mock en vez de una instancia nueva xD
		this.parser= new Parser();//mock(Parser.class); 
	}
	@Test
	public void laFactoryCreaUnAsientoCorrectamente() {
		String[] asientoLanchita = { "01202022220202-3", "159.90", "P", "V", "D", "" };
		Asiento asiento = parser.create(asientoLanchita, mock(Vuelo.class));
		Assert.assertEquals(159.90, asiento.getPrecio(), 0.0);	
		Assert.assertEquals( "3", asiento.getCodigo());
		Assert.assertEquals( "P", asiento.getClase());
		Assert.assertEquals( "V", asiento.getUbicacion());
		Assert.assertEquals( "D", asiento.getEstado());
	}

}
