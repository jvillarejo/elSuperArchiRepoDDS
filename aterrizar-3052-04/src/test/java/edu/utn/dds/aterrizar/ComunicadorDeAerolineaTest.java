package edu.utn.dds.aterrizar;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import edu.utn.dds.aterrizar.comunicadoresdeaerolineas.ComunicadorDeAerolinea;
import edu.utn.dds.aterrizar.comunicadoresdeaerolineas.ComunicadorDeAerolineaLanchita;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.AsientoDisponible;
import edu.utn.dds.aterrizar.vuelo.Vuelo;

public class ComunicadorDeAerolineaTest {

	private ComunicadorDeAerolinea comunicadorDeAerolinea;
	private Vuelo vuelo;
	private AsientoDisponible asientoDisponible;
	private Usuario usuario;

	@Before
	public void setUp() throws Exception {
		vuelo = mock(Vuelo.class);
		asientoDisponible = mock(AsientoDisponible.class);
		usuario = mock(Usuario.class);
		
		this.comunicadorDeAerolinea = new ComunicadorDeAerolineaLanchita();
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
