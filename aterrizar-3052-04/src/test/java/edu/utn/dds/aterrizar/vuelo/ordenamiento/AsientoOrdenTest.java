package edu.utn.dds.aterrizar.vuelo.ordenamiento;

import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.filtros.BuscadorDeAsientos;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroDummy;

import static org.mockito.Mockito.*;

public class AsientoOrdenTest {
	
	private Usuario usuarioDefault;
	
	@Before
	public void setUp() {
		usuarioDefault = mock(Usuario.class);
		when(usuarioDefault.getFiltro()).thenReturn(new FiltroDummy());
	}

	@Test
	public void ordenarAsientosPorPrecioDescendente() {
		Asiento asientoBarato = new Asiento();
		asientoBarato.setPrecio(1500D);
		
		Asiento asientoCaro = new Asiento();
		asientoCaro.setPrecio(2500D);
		
		BuscadorDeAsientos buscador = new BuscadorDeAsientos(Arrays.asList(asientoCaro, asientoBarato), usuarioDefault);
		buscador.ordenarPor(new OrdenPorPrecio(Orden.DESCENDENTE));
		
		assertEquals(Arrays.asList(asientoBarato, asientoCaro), buscador.buscar());
	}
	
	@Test
	public void ordenarAsientosPorPrecioAscendente() {
		Asiento asientoBarato = new Asiento();
		asientoBarato.setPrecio(1500D);
		
		Asiento asientoCaro = new Asiento();
		asientoCaro.setPrecio(2500D);
		
		BuscadorDeAsientos buscador = new BuscadorDeAsientos(Arrays.asList(asientoCaro, asientoBarato), usuarioDefault);
		buscador.ordenarPor(new OrdenPorPrecio(Orden.ASCENDENTE));
		
		assertEquals(Arrays.asList(asientoCaro, asientoBarato), buscador.buscar());
	}
}
