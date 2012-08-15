package edu.utn.dds.aterrizar.escalas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaWrapper;
import edu.utn.dds.aterrizar.aerolineas.AerolineaOceanicWrapper;
import edu.utn.dds.aterrizar.agencia.Agencia;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;


import static org.mockito.Mockito.*;


public class EscalasTest {

private Aerolinea lanchita = mock(AerolineaLanchitaWrapper.class);
private Aerolinea oceanic = mock(AerolineaOceanicWrapper.class);
private VueloDirecto unVuelo= 	new VueloDirecto("BA", "LA", "14/08/2012", "17/08/2012", lanchita);
private VueloDirecto otroVuelo= new VueloDirecto("LA", "DC", "18/08/2012", "30/08/2012", lanchita);
private List<VueloDirecto> vuelosDisponibles = new ArrayList<VueloDirecto>();
	@Test
	public void testEsEscala() {
		assertTrue(unVuelo.getDestino().equals(otroVuelo.getOrigen()));
		assertTrue(unVuelo.getFechaLlegada().esAnteriorA(otroVuelo.getFechaSalida()));
	assertTrue(new Agencia().esEscala(unVuelo,otroVuelo));
	
	}
	
	@Test
	public void testBuscarVuelosConEscalasLanchita(){
		vuelosDisponibles.add(unVuelo);
		vuelosDisponibles.add(otroVuelo);
		List<VueloConEscala> vuelos = new Agencia().armarVuelosConEscala(vuelosDisponibles);
		assertFalse(vuelos.isEmpty());
		assertTrue(vuelos.get(0).getVuelos().containsAll(vuelosDisponibles));
	}
	
	@Test
	public void buscarPrecioMasBaratoEnVueloDirecto() {
		Asiento asientoUnaLocura = new Asiento();
		asientoUnaLocura.setPrecio(10500D);
		
		Asiento asientoCaro = new Asiento();
		asientoCaro.setPrecio(7500D);
		
		Asiento asientoBarato = new Asiento();
		asientoBarato.setPrecio(1500D);
		
		VueloDirecto buenosAiresAMadrid = new VueloDirecto();
		buenosAiresAMadrid.agregarAsiento(asientoBarato);
		buenosAiresAMadrid.agregarAsiento(asientoCaro);
		buenosAiresAMadrid.agregarAsiento(asientoUnaLocura);
		
		assertEquals(1500D, buenosAiresAMadrid.getPrecioMasBarato(), 0D);
	}

}
