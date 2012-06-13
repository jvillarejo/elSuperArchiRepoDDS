package edu.utn.dds.aterrizar.vuelo.filtros;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import edu.utn.dds.aterrizar.usuario.*;
import edu.utn.dds.aterrizar.vuelo.*;

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
		FiltroAsiento filtroVip = new FiltroBuilder(unTipoImportante).build();
		
		assertEquals(asientosDisponibles, filtroVip.filtrar(asientosDisponibles)); 
	}
	
	@Test
	public void usuarioEstandarNoRecibeLasSuperOfertas() {
		Usuario unTipoComun = new Usuario("Federico", "Aloi", "9999", new SuscripcionEstandar());
		FiltroAsiento filtroComun = new FiltroBuilder(unTipoComun).build();
		
		List<Asiento> asientosComunes = new ArrayList<Asiento>();
		asientosComunes.add(asientoNormalEnPrimera);
		asientosComunes.add(asientoNormalEnTurista);
		
		assertEquals(asientosComunes, filtroComun.filtrar(asientosDisponibles)); 
	}
	
	@Test
	public void usuarioGratuitoNoRecibeLasSuperOfertas() {
		Usuario unTipoTacanio = new Usuario("Ebenezer", "Scrooge", "0000", new SuscripcionGratuita());
		FiltroAsiento filtroGratuito = new FiltroBuilder(unTipoTacanio).build();
		
		List<Asiento> asientosComunes = new ArrayList<Asiento>();
		asientosComunes.add(asientoNormalEnPrimera);
		asientosComunes.add(asientoNormalEnTurista);
		
		assertEquals(asientosComunes, filtroGratuito.filtrar(asientosDisponibles)); 
	}
	
}
