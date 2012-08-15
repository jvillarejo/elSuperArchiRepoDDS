package edu.utn.dds.aterrizar.parser;

import java.util.List;

/**
 * @author clari
 */

import org.junit.Assert;
import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaWrapper;
import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.parser.Parser;
import edu.utn.dds.aterrizar.parser.ParserException;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import static org.mockito.Mockito.*;

public class ParserTest {

	String[][] asientosLanchita = {
	{ "01202022220202-3", "159.90", "P", "V", "D", "" },
	{ "01202022220123-3", "205.10", "E", "P", "D", "" },
	{ "01202012315523-8", "154.08", "E", "P", "D", "" },
	{ "01202022267867-7", "255.98", "E", "P", "D", "" },
	{ "01202022227897-3", "236.10", "P", "C", "D", "" },
	{ "01202022998988-6", "148.23", "P", "V", "D", "" },
	{ "01202022220008-3", "383.22", "T", "V", "D", "" },

	{ "01202022256565-3", "282.19", "T", "C", "D", "" },

};
	
	@Test 
	public void generacionDeListaDeAsientos(){
		List <Vuelo> vuelosParseados= new Parser().parseDisponibles(asientosLanchita, mock(Busqueda.class), mock(AerolineaLanchitaWrapper.class));
		
		Assert.assertFalse(vuelosParseados.isEmpty());
	}
	
	@Test(expected= ParserException.class) 
	public void fallaDeParseo(){
		 new Parser().parseDisponibles(new String[][]{{}}, mock(Busqueda.class), mock(AerolineaLanchitaWrapper.class));
	}
	
	@Test(expected= ParserException.class)
	public void parseoDeAsientoConUbicacionErronea() {
		new Parser().parseDisponibles(new String[][]{{ "01202022220298-2", "528.81", "P", "ERRONEA", "D", "" }}, mock(Busqueda.class), mock(AerolineaLanchitaWrapper.class));
	}
	
	@Test(expected= ParserException.class)
	public void parseoDeAsientoConClaseErronea() {
		new Parser().parseDisponibles(new String[][]{{ "01202022220298-2", "528.81", "ERRONEA", "V", "D", "" }}, mock(Busqueda.class), mock(AerolineaLanchitaWrapper.class));
	}
}
