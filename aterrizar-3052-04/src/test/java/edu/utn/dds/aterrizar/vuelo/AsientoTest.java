package edu.utn.dds.aterrizar.vuelo;


import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.usuario.SuscripcionEstandar;
import edu.utn.dds.aterrizar.usuario.SuscripcionGratuita;
import edu.utn.dds.aterrizar.usuario.Usuario;
import org.junit.Assert;
import static org.mockito.Mockito.*;

public class AsientoTest {

	private Aerolinea aerolineaMockito = mock(Aerolinea.class);
	private Asiento asientoCaro = new Asiento(aerolineaMockito);
	private Asiento asientoDemasiadoBarato = new Asiento(aerolineaMockito);
	
	@Before
	public void setUp() {
		asientoCaro.setPrecio(10000.0);
		asientoDemasiadoBarato.setPrecio(100.0);
		
		when(aerolineaMockito.getPorcentajeDeVenta()).thenReturn(0.5);		
	}
	
	@Test
	public void asientoCaroCambiaSuPrecioParaUsuarioEstandar() { 
		Usuario unTipoComun = new Usuario("Federico", "Aloi", "9999", new SuscripcionEstandar());
		asientoCaro.adaptarPrecioPara(unTipoComun);
		
		Assert.assertEquals(Double.valueOf(15000.0), asientoCaro.getPrecio());
	}
	
	@Test
	public void asientoBaratoCambiaSuPrecioParaUsuarioGratuito() { 
		Usuario unTipoTacanio = new Usuario("Ebenezer", "Scrooge", "0000", new SuscripcionGratuita());
		asientoDemasiadoBarato.adaptarPrecioPara(unTipoTacanio);
		
		Assert.assertEquals(Double.valueOf(170.0), asientoDemasiadoBarato.getPrecio());
	}

}
