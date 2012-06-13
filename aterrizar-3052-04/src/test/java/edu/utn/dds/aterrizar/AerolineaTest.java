package edu.utn.dds.aterrizar;


import java.util.Date;
import java.util.List;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaWrapper;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;
import edu.utn.dds.aterrizar.parser.*;

public class AerolineaTest {

	private Aerolinea comunicadorDeAerolinea;
	private Vuelo vuelo;
	private Asiento asientoDisponible;
	private Usuario usuario;
	private Date fecha;

	@Before
	public void setUp()  {
		vuelo = mock(Vuelo.class);
		asientoDisponible = mock(Asiento.class);
		usuario = mock(Usuario.class);
		fecha= new Date();
		this.comunicadorDeAerolinea = new AerolineaLanchitaWrapper(new Parser());
	}
	
	@Test
	public void testBuscarAsientos()  {
		when(vuelo.getOrigen()).thenReturn("BUE");
		when(vuelo.getDestino()).thenReturn("LA");
		when(vuelo.getFecha()).thenReturn(fecha);
		List<Asiento> disponibles = comunicadorDeAerolinea.buscarAsientos(vuelo);
		Assert.assertFalse(disponibles.isEmpty());
		;
	}
	
	@Test
	public void testComprarAsiento() {
		when(usuario.getDni()).thenReturn("35247037");
		when(asientoDisponible.getCodigo()).thenReturn("01202022220202-3");
		comunicadorDeAerolinea.comprarAsiento(asientoDisponible, usuario);		
		Assert.assertTrue(asientoDisponible.getEstado().equals("C"));
	}

}
