package edu.utn.dds.aterrizar.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaWrapper;
import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.parser.Parser;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

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
	public void laFactoryCreaUnVueloCorrectamente() {
		String[] asientoLanchita = { "01202022220202-3", "159.90", "P", "V", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012" };
		Vuelo vuelo = parser.create(asientoLanchita, new Busqueda("EZE", "USA", "20/12/2012"), mock(AerolineaLanchitaWrapper.class));
		Assert.assertEquals(159.90, vuelo.getPrimerAsiento().getPrecio(), 0.0);	

		Assert.assertEquals( "01202022220202", vuelo.getPrimerAsiento().getCodigoDeVuelo());
		Assert.assertEquals( Integer.valueOf(3), vuelo.getPrimerAsiento().getNumeroDeAsiento());

		Assert.assertEquals( Clase.PRIMERA, vuelo.getPrimerAsiento().getClase());
		Assert.assertEquals( Ubicacion.VENTANILLA, vuelo.getPrimerAsiento().getUbicacion());
		Assert.assertEquals( "D", vuelo.getPrimerAsiento().getEstado());
	}

}
