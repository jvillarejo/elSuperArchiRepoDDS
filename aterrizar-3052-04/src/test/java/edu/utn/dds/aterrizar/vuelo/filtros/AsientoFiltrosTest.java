package edu.utn.dds.aterrizar.vuelo.filtros;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.Query;

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
	public void buscarAsientosConPrecioMenorA4500() {
		Asiento asientoBarato = new Asiento();
		asientoBarato.setPrecio(1500D);
		
		Asiento asientoCaro = new Asiento();
		asientoCaro.setPrecio(5000D);
		
		Query<Asiento> buscador = new BuscadorDeAsientos(Arrays.asList(asientoBarato, asientoCaro), usuarioDefault, 
				new FiltroPrecioMaximo(4500D));

		assertEquals(Arrays.asList(asientoBarato), buscador.execute());
	}

	@Test
	public void buscarAsientosConPrecioMayorA2000() {
		Asiento asientoBarato = new Asiento();
		asientoBarato.setPrecio(1500D);
		
		Asiento asientoCaro = new Asiento();
		asientoCaro.setPrecio(5000D);
		
		Query<Asiento> buscador = new BuscadorDeAsientos(Arrays.asList(asientoBarato, asientoCaro), usuarioDefault, 
				new FiltroPrecioMinimo(2000D));

		assertEquals(Arrays.asList(asientoCaro), buscador.execute());
	}
	
	public void buscarAsientosQueNoEstenReservados() {
		Asiento asientoLibre = mock(Asiento.class);
		when(asientoLibre.isReservado()).thenReturn(false);
		
		Asiento asientoReservado = mock(Asiento.class);
		when(asientoReservado.isReservado()).thenReturn(true);
		
		Asiento otroAsientoReservado = mock(Asiento.class);
		when(otroAsientoReservado.isReservado()).thenReturn(true);
		
		List<Asiento> asientos = Arrays.asList(asientoLibre, asientoReservado, otroAsientoReservado);
		Query<Asiento> buscador = new BuscadorDeAsientos(asientos, usuarioDefault, new FiltroLibres());
		
		assertEquals(Arrays.asList(asientoLibre), buscador.execute());
	}
	
	@Test
	public void buscarAsientosConPrecioEntre2000y5500() {
		Asiento asientoBarato = new Asiento();
		asientoBarato.setPrecio(1500D);
		
		Asiento asientoCaro = new Asiento();
		asientoCaro.setPrecio(5000D);
		
		Asiento asientoCarisimo = new Asiento();
		asientoCarisimo.setPrecio(6200D);
		
		List<Asiento> asientos = Arrays.asList(asientoBarato, asientoCaro, asientoCarisimo);
		Query<Asiento> buscador = new BuscadorDeAsientos(asientos, usuarioDefault);
		buscador.agregarFiltros(new FiltroPrecioMaximo(5500D), new FiltroPrecioMinimo(2000D));

		assertEquals(Arrays.asList(asientoCaro), buscador.execute());
	}
	
	@Test
	public void buscarAsientosEnElPasillo() {
		List<Asiento> asientosEnElPasillo = Arrays.asList(asientoPasilloEnPrimera);
		Query<Asiento> buscador = new BuscadorDeAsientos(asientosEnElPasillo, usuarioDefault, new FiltroPorUbicacion(Ubicacion.PASILLO));

		assertEquals(asientosEnElPasillo, buscador.execute());
	}

	@Test
	public void buscarAsientosEnVentanilla() {
		List<Asiento> asientosEnVentanilla = Arrays.asList(asientoVentanillaEnTurista, asientoVentanillaEnEjecutivo);
		Query<Asiento> buscador = new BuscadorDeAsientos(asientosEnVentanilla, usuarioDefault, new FiltroPorUbicacion(Ubicacion.VENTANILLA));
		
		assertEquals(asientosEnVentanilla, buscador.execute());
	}

	@Test
	public void buscarAsientosEnPrimeraClase() {
		List<Asiento> asientosEnPrimera = Arrays.asList(asientoCentroEnPrimera, asientoPasilloEnPrimera);
		Query<Asiento> buscador = new BuscadorDeAsientos(asientosEnPrimera, usuarioDefault, new FiltroPorClase(Clase.PRIMERA));

		assertEquals(asientosEnPrimera, buscador.execute());
	}

	@Test
	public void buscarAsientosVentanillaEnPrimera() {
		Query<Asiento> buscador = new BuscadorDeAsientos(asientosDisponibles, usuarioDefault);
		buscador.agregarFiltros(new FiltroPorUbicacion(Ubicacion.VENTANILLA), new FiltroPorClase(Clase.PRIMERA));
		
		assertTrue(buscador.execute().isEmpty());
	}

	@Test
	public void buscarAsientosEnTuristaOEnEjecutiva() {
		Query<Asiento> buscador = new BuscadorDeAsientos(asientosDisponibles, usuarioDefault);
		buscador.agregarFiltros(new FiltroPorClase(Clase.TURISTA, Clase.EJECUTIVA));
		
		assertEquals(Arrays.asList(asientoVentanillaEnTurista, asientoVentanillaEnEjecutivo), buscador.execute());
	}
	
	@Test
	public void buscarAsientosVentanillaEnTurista() {
		List<Asiento> asientosVentanillaEnTurista = Arrays.asList(asientoVentanillaEnTurista);
		
		Query<Asiento> buscador = new BuscadorDeAsientos(asientosDisponibles, usuarioDefault);
		buscador.agregarFiltros(new FiltroPorUbicacion(Ubicacion.VENTANILLA), new FiltroPorClase(Clase.TURISTA));

		assertEquals(asientosVentanillaEnTurista, buscador.execute());
	}
}