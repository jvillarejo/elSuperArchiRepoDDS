package edu.utn.dds.aterrizar.aerolineas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;

import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

public class AerolineaOceanicWrapperTest {

	private AerolineaOceanicParser parser;
	private AerolineaOceanic aerolineaOceanic;
	private AerolineaOceanicWrapper aerolineaOceanicWrapper;
	private Usuario usuario;
	private Asiento asientoDisponible;
	
	@Before
	public void setUp() {
		aerolineaOceanic = mock(AerolineaOceanic.class);
		parser = mock(AerolineaOceanicParser.class);
		aerolineaOceanicWrapper = new AerolineaOceanicWrapper(aerolineaOceanic, parser);
		
		usuario = mock(Usuario.class);
		when(usuario.getDni()).thenReturn("33981245");
		
		asientoDisponible = mock(Asiento.class);
		when(asientoDisponible.getCodigoDeVuelo()).thenReturn("3E7");
		when(asientoDisponible.getNumeroDeAsiento()).thenReturn(Integer.valueOf(7));

	}
	
	@Test
	public void testBuscarVuelos() {
		Busqueda busqueda = new Busqueda("MA", "LA", "12/08/2012", "13/08/2012", "15:00", "09:00");
		AsientoDTO asientoDTO = new AsientoDTO("BCE", Integer.valueOf(5), "12/08/2012", "13/08/2012", "15:00", "09:00", BigDecimal.valueOf(512), "primera clase", "pasillo", false, "MA_", "SLA");
		List<AsientoDTO> asientosDTOs = Arrays.asList(asientoDTO);
		
		VueloDirecto vueloDirecto = new VueloDirecto("MA", "LA", "12/08/2012", "13/08/2012", aerolineaOceanicWrapper);
		
		when(aerolineaOceanic.asientosDisponiblesParaOrigenYDestino(anyString(), anyString(), anyString())).thenReturn(asientosDTOs);
		when(parser.parse(anyListOf(AsientoDTO.class), any(Busqueda.class), any(Aerolinea.class))).thenReturn(Arrays.asList(vueloDirecto));
		
		List<VueloDirecto> buscarVuelos = aerolineaOceanicWrapper.buscarVuelos(busqueda);
		
		assertNotNull(buscarVuelos);
		assertEquals(1, buscarVuelos.size());
		
		verify(aerolineaOceanic, times(1)).asientosDisponiblesParaOrigenYDestino("MA_", "SLA", "12/08/2012");
		verify(parser, times(1)).parse(asientosDTOs, busqueda, aerolineaOceanicWrapper);
	}

	@Test
	public void testComprarAsientoDisponible() {
		
		when(aerolineaOceanic.comprarSiHayDisponibilidad(anyString(), anyString(), anyInt())).thenReturn(Boolean.TRUE);
		
		aerolineaOceanicWrapper.comprarAsiento(asientoDisponible, usuario);
		
		verify(aerolineaOceanic,times(1)).comprarSiHayDisponibilidad("33981245", "3E7", Integer.valueOf(7));
		verify(asientoDisponible,times(1)).setEstado("C");
	}
	
	@Test(expected=AsientoNoDisponibleException.class)
	public void testComprarAsientoNoDisponible() {
		
		when(aerolineaOceanic.comprarSiHayDisponibilidad(anyString(), anyString(), anyInt())).thenReturn(false);
		
		aerolineaOceanicWrapper.comprarAsiento(asientoDisponible, usuario);
		
		verify(aerolineaOceanic,times(1)).comprarSiHayDisponibilidad("33981245", "3E7", Integer.valueOf(7));
		verify(asientoDisponible,times(0)).setEstado("C");
	}

}
