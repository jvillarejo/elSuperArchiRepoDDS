package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import edu.utn.dds.aterrizar.usuario.*;
import edu.utn.dds.aterrizar.vuelo.*;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.Query;

import static org.junit.Assert.*;


public class FiltrosSegunTipoSuscripcionTest {

	private Asiento asientoSuperOfertaEnPrimera = new Asiento();
	private Asiento asientoNormalEnPrimera = new Asiento();
	private Asiento asientoSuperOfertaEnEjecutiva = new Asiento();
	private Asiento asientoNormalEnTurista = new Asiento();
	
	private List<Asiento> asientosDisponibles = new ArrayList<Asiento>();
	
	@Before
	public void setUp() {
		asientoSuperOfertaEnPrimera.setClase(Clase.PRIMERA);
		asientoSuperOfertaEnPrimera.setPrecio(5000.0);
		
		asientoNormalEnPrimera.setClase(Clase.PRIMERA);
		asientoNormalEnPrimera.setPrecio(10000.0);
		
		asientoSuperOfertaEnEjecutiva.setClase(Clase.EJECUTIVA);
		asientoSuperOfertaEnEjecutiva.setPrecio(3900.0);
		
		asientoNormalEnTurista.setClase(Clase.TURISTA);
		asientoNormalEnTurista.setPrecio(2000.0);
		
		asientosDisponibles.add(asientoSuperOfertaEnPrimera);
		asientosDisponibles.add(asientoNormalEnPrimera);
		asientosDisponibles.add(asientoSuperOfertaEnEjecutiva);
		asientosDisponibles.add(asientoNormalEnTurista);		
	}
	
	@Test
	public void usuarioVipRecibeLasSuperOfertas() {
		Usuario unTipoImportante = new Usuario("Barack", "Obama", "12331", new SuscripcionVip());
		Query<Asiento> buscador = new BuscadorDeAsientos(asientosDisponibles, unTipoImportante);
		
		assertEquals(asientosDisponibles, buscador.execute()); 
	}
	
	@Test
	public void usuarioEstandarNoRecibeLasSuperOfertas() {
		Usuario unTipoComun = new Usuario("Federico", "Aloi", "9999", new SuscripcionEstandar());
		Query<Asiento> buscador = new BuscadorDeAsientos(asientosDisponibles, unTipoComun);
		
		List<Asiento> asientosComunes = Arrays.asList(asientoNormalEnPrimera, asientoNormalEnTurista);
		assertEquals(asientosComunes, buscador.execute()); 
	}
	
	@Test
	public void usuarioGratuitoNoRecibeLasSuperOfertas() {
		Usuario unTipoTacanio = new Usuario("Ebenezer", "Scrooge", "0000", new SuscripcionGratuita());
		Query<Asiento> buscador = new BuscadorDeAsientos(asientosDisponibles, unTipoTacanio);
		
		List<Asiento> asientosComunes = Arrays.asList(asientoNormalEnPrimera, asientoNormalEnTurista);
		assertEquals(asientosComunes, buscador.execute()); 
	}
}
