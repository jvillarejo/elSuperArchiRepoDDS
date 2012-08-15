package edu.utn.dds.aterrizar.aerolineas;



import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lanchita.AerolineaLanchita;
import com.lanchita.excepciones.EstadoErroneoException;

import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.parser.Parser;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
/**
 * 
 * @author clari, ariel
 *
 */
public class AerolineaTest {

	private Aerolinea comunicadorDeAerolinea;
	private Busqueda vuelo;
	private Usuario usuario;
	private Date fecha;
	private AerolineaLanchita aerolineaLanchita;
	private Parser parser;

	@Before
	public void setUp()  {
		aerolineaLanchita = mock(AerolineaLanchita.class);
		parser = mock(Parser.class);
		vuelo = mock(Busqueda.class);
		usuario = mock(Usuario.class);
		fecha= new Date();
		this.comunicadorDeAerolinea = new AerolineaLanchitaWrapper(aerolineaLanchita, parser);
	}
	
	@Test
	public void testBuscarAsientos()  {
		String[] asiento = {"01202022220202-3", "159.90", "P", "V", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012"};
		String[][] asientosLanchita = {asiento};
		when(aerolineaLanchita.asientosDisponibles(anyString(), anyString(), anyString(),anyString(), anyString(),anyString() )).thenReturn(asientosLanchita);
		Busqueda busqueda= new Busqueda("EZE","USA","20/12/2012", "21/12/2012","14:00", "02:25");
		List<VueloDirecto> disponibles = comunicadorDeAerolinea.buscarVuelos(busqueda);
		Assert.assertNotNull(disponibles);
		Assert.assertFalse(disponibles.isEmpty());
		
		verify(parser).parseDisponibles(asientosLanchita, busqueda, comunicadorDeAerolinea);
		verify(aerolineaLanchita).asientosDisponibles("EZE", "USA", "20/12/2012","14:00","21/12/2012","02:25");
	}
	
	@Test
	public void testComprarAsientoDisponible() {
	//	when(usuario.getDni()).thenReturn("35247037");
		Asiento asiento= new Asiento(vuelo, mock(Aerolinea.class));
		asiento.setCodigoDeVuelo("01202022220202");
		asiento.setNumeroDeAsiento(3);
		asiento.setEstado("D");
		comunicadorDeAerolinea.comprarAsiento(asiento, usuario);
		assertEquals("C", asiento.getEstado());
		verify(aerolineaLanchita).comprar("01202022220202-3" /*, "35247037"*/);
	}
	
	
	@Test(expected = AsientoNoDisponibleException.class)
	public void testComprarAsientoNoDisponible() {
	//	when(usuario.getDni()).thenReturn("35247037");
		Asiento asiento= new Asiento(vuelo, mock(AerolineaLanchitaWrapper.class));
		asiento.setCodigoDeVuelo("01202022220202");
		asiento.setNumeroDeAsiento(3);
		asiento.setEstado("R");
		doThrow(new EstadoErroneoException()).when(aerolineaLanchita).comprar(anyString() /*, anyString()*/);
		
		comunicadorDeAerolinea.comprarAsiento(asiento, usuario);
		
		verify(aerolineaLanchita).comprar("01202022220202-3" /*,"35247037"*/);
	}
	
	
	@Test(expected = AsientoNoDisponibleException.class)
	public void testComprarAsientoDisponibleDosVecesLaSegundaTiraError() {
		when(usuario.getDni()).thenReturn("35247037");
		
		Asiento asientoDisponible = new Asiento(vuelo, mock(Aerolinea.class));
		asientoDisponible.setCodigoDeVuelo("01202022220202");
		asientoDisponible.setNumeroDeAsiento(3);
		asientoDisponible.setEstado("D");
		
		doNothing().doThrow(new EstadoErroneoException()).when(aerolineaLanchita).comprar(anyString() /*, anyString()*/);
		
		comunicadorDeAerolinea.comprarAsiento(asientoDisponible, usuario);
		assertEquals("C",asientoDisponible.getEstado());
		verify(aerolineaLanchita).comprar("01202022220202-3"/*, "35247037"*/);

		
		comunicadorDeAerolinea.comprarAsiento(asientoDisponible, usuario);
		verify(aerolineaLanchita).comprar("01202022220202-3" /*, "35247037"*/);
}
}
