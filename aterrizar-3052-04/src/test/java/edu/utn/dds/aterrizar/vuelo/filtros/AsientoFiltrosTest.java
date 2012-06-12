package edu.utn.dds.aterrizar.vuelo.filtros;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

public class AsientoFiltrosTest {

	private List<Asiento> asientosDisponibles = new ArrayList<Asiento>();
	private Asiento asientoCentroEnPrimera = new Asiento();
	private Asiento asientoPasilloEnPrimera = new Asiento();
	private Asiento asientoVentanillaEnTurista = new Asiento();
	private Asiento asientoVentanillaEnEjecutivo = new Asiento();

	@Before
	public void setUp() {
		asientoCentroEnPrimera.setUbicacion(Ubicacion.CENTRO);
		asientoCentroEnPrimera.setClase(Clase.PRIMERA);
		
		asientoPasilloEnPrimera.setUbicacion(Ubicacion.PASILLO);
		asientoPasilloEnPrimera.setClase(Clase.PRIMERA);
		
		asientoVentanillaEnTurista.setUbicacion(Ubicacion.VENTANILLA);
		asientoVentanillaEnTurista.setClase(Clase.TURISTA);
		
		asientoVentanillaEnEjecutivo.setUbicacion(Ubicacion.VENTANILLA);
		asientoVentanillaEnEjecutivo.setClase(Clase.EJECUTIVO);
		
		asientosDisponibles.add(asientoCentroEnPrimera);
		asientosDisponibles.add(asientoPasilloEnPrimera);
		asientosDisponibles.add(asientoVentanillaEnTurista);
		asientosDisponibles.add(asientoVentanillaEnEjecutivo);
	}
	
	@Test
	public void buscarAsientosEnElPasillo() {
		List<Asiento> asientosEnElPasillo = new ArrayList<Asiento>();
		asientosEnElPasillo.add(asientoPasilloEnPrimera);
		
		FiltroAsiento filtroUbicacion = new FiltroPorUbicacion(Ubicacion.PASILLO, new FiltroDummy());
		assertEquals(asientosEnElPasillo, filtroUbicacion.filtrar(asientosDisponibles));
	}
	
	@Test
	public void buscarAsientosEnVentanilla() {		
		List<Asiento> asientosEnVentanilla = new ArrayList<Asiento>();
		asientosEnVentanilla.add(asientoVentanillaEnTurista);
		asientosEnVentanilla.add(asientoVentanillaEnEjecutivo);
		
		FiltroAsiento filtroUbicacion = new FiltroPorUbicacion(Ubicacion.VENTANILLA, new FiltroDummy());
		assertEquals(asientosEnVentanilla, filtroUbicacion.filtrar(asientosDisponibles));
	}
	
	@Test
	public void buscarAsientosEnPrimeraClase() {
		List<Asiento> asientosEnPrimera = new ArrayList<Asiento>();
		asientosEnPrimera.add(asientoCentroEnPrimera);
		asientosEnPrimera.add(asientoPasilloEnPrimera);
		
		FiltroAsiento filtroClase = new FiltroPorClase(Clase.PRIMERA, new FiltroDummy());
		assertEquals(asientosEnPrimera, filtroClase.filtrar(asientosDisponibles));
	}
	
	@Test
	public void buscarAsientosVentanillaEnPrimera() {
		FiltroAsiento filtroClase = new FiltroPorClase(Clase.PRIMERA, 
				new FiltroPorUbicacion(Ubicacion.VENTANILLA, new FiltroDummy())
		);
		
		assertTrue(filtroClase.filtrar(asientosDisponibles).isEmpty());
	}
	
	@Test
	public void buscarAsientosVentanillaEnTurista() {		
		List<Asiento> asientosVentanillaEnTurista = new ArrayList<Asiento>();
		asientosVentanillaEnTurista.add(asientoVentanillaEnTurista);
		
		FiltroAsiento filtroClase = new FiltroPorClase(Clase.TURISTA, 
				new FiltroPorUbicacion(Ubicacion.VENTANILLA, new FiltroDummy())
		);
		assertEquals(asientosVentanillaEnTurista, filtroClase.filtrar(asientosDisponibles));
	}
}
