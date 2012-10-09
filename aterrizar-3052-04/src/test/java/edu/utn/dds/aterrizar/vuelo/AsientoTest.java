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
	
	@Before
	public void setUp() {
		when(aerolineaMockito.getPorcentajeDeVenta()).thenReturn(0.5);		
	}
	
	@Test
	public void asientoCaroCambiaSuPrecioParaUsuarioEstandar() { 
		Usuario unTipoComun = new Usuario("Federico", "Aloi", "9999", new SuscripcionEstandar());
		
		Asiento asientoCaro = new Asiento(aerolineaMockito);	
		asientoCaro.setPrecio(10000.0);
		asientoCaro.adaptarPrecioPara(unTipoComun);
		
		Assert.assertEquals(Double.valueOf(15000.0), asientoCaro.getPrecio());
	}
	
	@Test
	public void asientoBaratoCambiaSuPrecioParaUsuarioGratuito() { 
		Usuario unTipoTacanio = new Usuario("Ebenezer", "Scrooge", "0000", new SuscripcionGratuita());
		
		Asiento asientoDemasiadoBarato = new Asiento(aerolineaMockito);
		asientoDemasiadoBarato.setPrecio(100.0);
		asientoDemasiadoBarato.adaptarPrecioPara(unTipoTacanio);
		
		Assert.assertEquals(Double.valueOf(170.0), asientoDemasiadoBarato.getPrecio());
	}

}
