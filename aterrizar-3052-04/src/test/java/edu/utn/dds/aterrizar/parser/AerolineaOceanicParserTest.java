package edu.utn.dds.aterrizar.parser;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.oceanic.AsientoDTO;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.aerolineas.AerolineaOceanicParser;
import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;
import static org.mockito.Mockito.*;

public class AerolineaOceanicParserTest {
	
	private AerolineaOceanicParser parser;
	private Aerolinea aerolinea;
	
	@Before
	public void setup() {
		parser = new AerolineaOceanicParser();
		
		aerolinea = mock(Aerolinea.class);
	}
	
	@Test
	public void testParseListOfAsientoDTOBusquedaAerolinea() {
		AsientoDTO asientoDTO1 = new AsientoDTO("E8", Integer.valueOf(5), "12/08/2012", "13/08/2012", "15:00", "09:00", BigDecimal.valueOf(512), "primera clase", "pasillo", false, "MA_", "SLA");
		AsientoDTO asientoDTO2 = new AsientoDTO("F9", Integer.valueOf(5), "12/08/2012", "13/08/2012", "15:00", "09:00", BigDecimal.valueOf(500), "turista", "ventana", false, "MA_", "SLA");
		
		List<AsientoDTO> asientosDisponibles = Arrays.asList(asientoDTO1, asientoDTO2);
		
		Busqueda busqueda = new Busqueda("MA", "LA", "12/08/2012", "13/08/2012", "15:00", "09:00");
		
		List<VueloDirecto> vuelos = parser.parse(asientosDisponibles, busqueda, aerolinea);
		assertNotNull(vuelos);
		assertEquals(2, vuelos.size());
		
		Iterator<VueloDirecto> iterator = vuelos.iterator();
		
		VueloDirecto vueloDirecto = iterator.next();
		assertEquals(1, vueloDirecto.getAsientos().size());
		
		vueloDirecto = iterator.next();
		assertEquals(1, vueloDirecto.getAsientos().size());
	}
	
	@Test
	public void testParseListOfAsientoDTOBusquedaMuchosAsientosDeUnVuelo() {
		AsientoDTO asientoDTO1 = new AsientoDTO("E8", Integer.valueOf(5), "12/08/2012", "13/08/2012", "15:00", "09:00", BigDecimal.valueOf(512), "primera clase", "pasillo", false, "MA_", "SLA");
		AsientoDTO asientoDTO2 = new AsientoDTO("E8", Integer.valueOf(6), "12/08/2012", "13/08/2012", "15:00", "09:00", BigDecimal.valueOf(500), "turista", "ventana", false, "MA_", "SLA");
		AsientoDTO asientoDTO3 = new AsientoDTO("E8", Integer.valueOf(7), "12/08/2012", "13/08/2012", "15:00", "09:00", BigDecimal.valueOf(500), "turista", "ventana", false, "MA_", "SLA");
		AsientoDTO asientoDTO4 = new AsientoDTO("E8", Integer.valueOf(8), "12/08/2012", "13/08/2012", "15:00", "09:00", BigDecimal.valueOf(500), "turista", "ventana", false, "MA_", "SLA");
		
		List<AsientoDTO> asientosDisponibles = Arrays.asList(asientoDTO1, asientoDTO2,asientoDTO3, asientoDTO4);
		
		Busqueda busqueda = new Busqueda("MA", "LA", "12/08/2012", "13/08/2012", "15:00", "09:00");
		
		List<VueloDirecto> vuelos = parser.parse(asientosDisponibles, busqueda, aerolinea);
		
		assertNotNull(vuelos);
		assertEquals(1, vuelos.size());
		
		VueloDirecto vueloDirecto = vuelos.iterator().next();
		assertEquals(4, vueloDirecto.getAsientos().size());
	}

	@Test
	public void testParseAsientoDTOAerolineaBusqueda() {
		Busqueda busqueda = new Busqueda("MA", "LA", "12/08/2012", "13/08/2012", "15:00", "09:00");
		
		AsientoDTO asientoDTO = new AsientoDTO("E8", Integer.valueOf(5), "12/08/2012", "13/08/2012", "15:00", "09:00", BigDecimal.valueOf(512), "primera clase", "pasillo", false, "MA_", "SLA");
		
		Asiento asiento = parser.parse(asientoDTO, aerolinea, busqueda);
		
		assertEquals(busqueda, asiento.getFlight());
		assertEquals(Ubicacion.PASILLO, asiento.getUbicacion());
		assertEquals(Clase.PRIMERA, asiento.getClase());
		assertEquals("E8", asiento.getCodigoDeVuelo());
		assertEquals(Integer.valueOf(5), asiento.getNumeroDeAsiento());
		assertEquals(Double.valueOf(512), asiento.getPrecio());
	}

}
