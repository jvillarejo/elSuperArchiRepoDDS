package edu.utn.dds.aterrizar.vuelo.filtros;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

import static org.mockito.Mockito.*;

public class AsientoFiltrosTest {

	private List<Asiento> asientosDisponibles = new ArrayList<Asiento>();
	private Asiento asientoCentroEnPrimera = new Asiento();
	private Asiento asientoPasilloEnPrimera = new Asiento();
	private Asiento asientoVentanillaEnTurista = new Asiento();
	private Asiento asientoVentanillaEnEjecutivo = new Asiento();
	private Usuario usuarioDefault = mock(Usuario.class);

	@Before
	public void setUp() {
		asientoCentroEnPrimera.setUbicacion(Ubicacion.CENTRO);
		asientoCentroEnPrimera.setClase(Clase.PRIMERA);

		asientoPasilloEnPrimera.setUbicacion(Ubicacion.PASILLO);
		asientoPasilloEnPrimera.setClase(Clase.PRIMERA);

		asientoVentanillaEnTurista.setUbicacion(Ubicacion.VENTANILLA);
		asientoVentanillaEnTurista.setClase(Clase.TURISTA);

		asientoVentanillaEnEjecutivo.setUbicacion(Ubicacion.VENTANILLA);
		asientoVentanillaEnEjecutivo.setClase(Clase.EJECUTIVA);

		asientosDisponibles.add(asientoCentroEnPrimera);
		asientosDisponibles.add(asientoPasilloEnPrimera);
		asientosDisponibles.add(asientoVentanillaEnTurista);
		asientosDisponibles.add(asientoVentanillaEnEjecutivo);
		
		when(usuarioDefault.getFiltro()).thenReturn(new FiltroDummy());
	}

	@Test
	public void buscarAsientosEnElPasillo() {
		List<Asiento> asientosEnElPasillo = new ArrayList<Asiento>();
		asientosEnElPasillo.add(asientoPasilloEnPrimera);

		FiltroAsiento filtroUbicacion = new FiltroBuilder(usuarioDefault)
				.filtrarPorUbicacion(Ubicacion.PASILLO).build();

		assertEquals(asientosEnElPasillo,
				filtroUbicacion.filtrar(asientosDisponibles));
	}

	@Test
	public void buscarAsientosEnVentanilla() {
		List<Asiento> asientosEnVentanilla = new ArrayList<Asiento>();
		asientosEnVentanilla.add(asientoVentanillaEnTurista);
		asientosEnVentanilla.add(asientoVentanillaEnEjecutivo);

		FiltroAsiento filtroUbicacion = new FiltroBuilder(usuarioDefault)
				.filtrarPorUbicacion(Ubicacion.VENTANILLA).build();

		assertEquals(asientosEnVentanilla,
				filtroUbicacion.filtrar(asientosDisponibles));
	}

	@Test
	public void buscarAsientosEnPrimeraClase() {
		List<Asiento> asientosEnPrimera = new ArrayList<Asiento>();
		asientosEnPrimera.add(asientoCentroEnPrimera);
		asientosEnPrimera.add(asientoPasilloEnPrimera);

		FiltroAsiento filtroClase = new FiltroBuilder(usuarioDefault)
				.filtrarPorClase(Clase.PRIMERA).build();

		assertEquals(asientosEnPrimera,
				filtroClase.filtrar(asientosDisponibles));
	}

	@Test
	public void buscarAsientosVentanillaEnPrimera() {
		FiltroAsiento filtroCompuesto = new FiltroBuilder(usuarioDefault)
				.filtrarPorUbicacion(Ubicacion.VENTANILLA)
				.filtrarPorClase(Clase.PRIMERA).build();

		assertTrue(filtroCompuesto.filtrar(asientosDisponibles).isEmpty());
	}

	@Test
	public void buscarAsientosVentanillaEnTurista() {
		List<Asiento> asientosVentanillaEnTurista = new ArrayList<Asiento>();
		asientosVentanillaEnTurista.add(asientoVentanillaEnTurista);

		FiltroAsiento filtroCompuesto = new FiltroBuilder(usuarioDefault)
				.filtrarPorUbicacion(Ubicacion.VENTANILLA)
				.filtrarPorClase(Clase.TURISTA).build();

		assertEquals(asientosVentanillaEnTurista,
				filtroCompuesto.filtrar(asientosDisponibles));
	}
}
