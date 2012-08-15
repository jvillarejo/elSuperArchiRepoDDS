package edu.utn.dds.aterrizar.escalas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
private VueloDirecto otroVuelo= new VueloDirecto("LA", "DC", "17/08/2012", "30/8/2012", lanchita);
private List<VueloDirecto> vuelosDisponibles = new ArrayList<VueloDirecto>();
	@Test
	public void testEsEscala() {
		assertTrue(new Agencia().esEscala(unVuelo,otroVuelo));
	
	}
	
	@Test
	public void testBuscarVuelosConEscalasLanchita(){
		vuelosDisponibles.add(unVuelo);
		vuelosDisponibles.add(otroVuelo);
		List<Vuelo> vuelos = mock(Agencia.class).buscarVuelosConEscala(vuelosDisponibles);
		assertTrue(vuelos.containsAll(vuelosDisponibles));
	}

}
