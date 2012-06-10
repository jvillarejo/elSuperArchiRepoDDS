package edu.utn.dds.aterrizar;


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

	@Before
	public void setUp() throws Exception {
		vuelo = mock(Vuelo.class);
		asientoDisponible = mock(Asiento.class);
		usuario = mock(Usuario.class);
		
		this.comunicadorDeAerolinea = new AerolineaLanchitaWrapper(mock(Parser.class));
	}
	
	@Test
	public void testBuscarAsientos() throws Exception {
		comunicadorDeAerolinea.buscarAsientos(vuelo);
		
		Assert.fail("no implementado todavia");
	}
	
	@Test
	public void testComprarAsiento() {
		comunicadorDeAerolinea.comprarAsiento(asientoDisponible, usuario);
		
		Assert.fail("no implementado todavia");
	}

}
