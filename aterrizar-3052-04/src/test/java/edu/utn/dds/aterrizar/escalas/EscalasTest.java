package edu.utn.dds.aterrizar.escalas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaWrapper;
import edu.utn.dds.aterrizar.aerolineas.AerolineaOceanicWrapper;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Vuelo;


import static org.mockito.Mockito.*;


public class EscalasTest {

private Aerolinea lanchita = mock(AerolineaLanchitaWrapper.class);
private Aerolinea oceanic = mock(AerolineaOceanicWrapper.class);
private Asiento unAsiento;
private Asiento otroAsiento;
private List<Asiento> asientosDisponibles = new ArrayList<Asiento>();

@Before
public void setUp(){
	unAsiento= new Asiento(new Vuelo("BA", "LA", "17/08/2012"), lanchita);
	otroAsiento= new Asiento(new Vuelo("LA", "DC", "17/08/2012"), lanchita);
}
	@Test
	public void testEsEscala() {
		//TODO revisar porque no quiere parsear la fecha.
		assertTrue(unAsiento.esEscala(otroAsiento));
	
	}
	
	@Test
	public void testBuscarVuelosConEscalasLanchita(){
		asientosDisponibles.add(unAsiento);
		asientosDisponibles.add(otroAsiento);
		List<Asiento> vuelos = new Escala().buscarVuelosConEscala("BA", "DC", "17/8/2012", lanchita);
		assertTrue(vuelos.containsAll(asientosDisponibles));
	}
//TODO chequear como anda con oceanic, que faltan implementar mensajes en el wrapper
//TODO escribir test con expected failures
}
