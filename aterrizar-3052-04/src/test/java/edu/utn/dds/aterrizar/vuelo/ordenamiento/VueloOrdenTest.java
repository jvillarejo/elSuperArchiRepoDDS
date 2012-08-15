package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;

import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroDummy;

import static org.mockito.Mockito.*;

public class VueloOrdenTest {
	
	private Usuario usuarioDefault;
	
	@Before
	public void setUp() {
		usuarioDefault = mock(Usuario.class);
		when(usuarioDefault.getFiltro()).thenReturn(new FiltroDummy());
	}

	@Test
	public void ordenarVuelosPorPrecioDescendente() {
		Vuelo vueloConAsientoCaro = mock(Vuelo.class);
		when(vueloConAsientoCaro.getPrecioMasBarato()).thenReturn(5500D);
		
		Vuelo vueloConAsientoBarato = mock(Vuelo.class);
		when(vueloConAsientoBarato.getPrecioMasBarato()).thenReturn(1500D);
		
		Buscador<Vuelo> buscador = new BuscadorDeVuelos(Arrays.asList(vueloConAsientoBarato, vueloConAsientoCaro));
		buscador.ordenarPor(new OrdenPorPrecio(Orden.DESCENDENTE));
		
		assertEquals(Arrays.asList(vueloConAsientoCaro, vueloConAsientoBarato), buscador.buscar());
	}
	
	@Test
	public void ordenarVuelosPorPrecioAscendente() {
		Vuelo vueloConAsientoCarisimo = mock(Vuelo.class);
		when(vueloConAsientoCarisimo.getPrecioMasBarato()).thenReturn(7000D);
		
		Vuelo vueloConAsientoCaro = mock(Vuelo.class);
		when(vueloConAsientoCaro.getPrecioMasBarato()).thenReturn(5500D);
		
		Vuelo vueloConAsientoBarato = mock(Vuelo.class);
		when(vueloConAsientoBarato.getPrecioMasBarato()).thenReturn(1500D);
		
		Buscador<Vuelo> buscador = new BuscadorDeVuelos(Arrays.asList(vueloConAsientoBarato, vueloConAsientoCarisimo, vueloConAsientoCaro));
		buscador.ordenarPor(new OrdenPorPrecio(Orden.ASCENDENTE));
		
		assertEquals(Arrays.asList(vueloConAsientoBarato, vueloConAsientoCaro, vueloConAsientoCarisimo), buscador.buscar());
	}
	
	@Test
	public void ordenarVuelosPorTiempoDeVuelo() {
		Vuelo vueloLargo = mock(Vuelo.class);
		when(vueloLargo.getDuration()).thenReturn(5L);
		
		Vuelo vueloCortito = mock(Vuelo.class);
		when(vueloLargo.getDuration()).thenReturn(1L);
		
		Vuelo vueloLarguisimo = mock(Vuelo.class);
		when(vueloLarguisimo.getDuration()).thenReturn(27L);
		
		Buscador<Vuelo> buscador = new BuscadorDeVuelos(Arrays.asList(vueloLargo, vueloCortito, vueloLarguisimo));
		buscador.ordenarPor(new OrdenPorTiempoDeVuelo());
		
		assertEquals(Arrays.asList(vueloCortito, vueloLargo, vueloLarguisimo), buscador.buscar());
	}
	
}
