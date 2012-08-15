package edu.utn.dds.aterrizar.vuelo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.lanchita.AerolineaLanchita;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaWrapper;
import edu.utn.dds.aterrizar.aerolineas.AerolineaOceanicImpostor;
import edu.utn.dds.aterrizar.aerolineas.AerolineaOceanicWrapperImpostor;
import edu.utn.dds.aterrizar.aerolineas.AsientoNoDisponibleException;
import edu.utn.dds.aterrizar.parser.Parser;
import edu.utn.dds.aterrizar.usuario.SuscripcionEstandar;
import edu.utn.dds.aterrizar.usuario.SuscripcionGratuita;
import edu.utn.dds.aterrizar.usuario.SuscripcionVip;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.usuario.UsuarioNoDisponibleException;

public class ReservaTest {
	private Usuario usuarioGratuito;
	private Usuario usuarioVip;
	private Usuario usuarioEstandar;
	private Usuario otroUsuarioEstandar;
	private Asiento asientoLanchita;
	private Asiento asientoOceanic;
	private Aerolinea comunicadorDeAerolineaLanchita;
	private Aerolinea comunicadorDeAerolineaOceanic;
	private AerolineaLanchita aerolineaLanchita;
	private AerolineaOceanicImpostor aerolineaOceanicImpostor;

	@Before
	public void setUp() {
		aerolineaLanchita = mock(AerolineaLanchita.class);
		aerolineaOceanicImpostor = AerolineaOceanicImpostor.getInstance();
		usuarioGratuito = new Usuario("nombre", "apellido", "dni", new SuscripcionGratuita());
		usuarioVip = new Usuario("nombre", "apellido", "dni", new SuscripcionVip());
		usuarioEstandar = new Usuario("nombre", "apellido", "dni", new SuscripcionEstandar());
		otroUsuarioEstandar = new Usuario("otroNombre", "otroApellido", "otroDni", new SuscripcionEstandar());
		this.comunicadorDeAerolineaLanchita = new AerolineaLanchitaWrapper(aerolineaLanchita, new Parser());
		this.comunicadorDeAerolineaOceanic = new AerolineaOceanicWrapperImpostor(aerolineaOceanicImpostor);
		asientoLanchita = new Asiento(this.comunicadorDeAerolineaLanchita);
		asientoOceanic = new Asiento(this.comunicadorDeAerolineaOceanic);
	}
	
	@Test(expected = UsuarioNoDisponibleException.class)
	public void usuarioGratuitoNoPuedeReservar() {
		usuarioGratuito.reservar(new Asiento());
	}
	
	@Test(expected = UsuarioNoDisponibleException.class)
	public void usuarioVipNoPuedeReservar() {
		usuarioVip.reservar(new Asiento());
	}
	
	@Test
	public void reservaYCompraExitosaDeUnAsientoDisponibleLanchita() {
		asientoLanchita.setCodigoDeVuelo("01202022220202");
		asientoLanchita.setNumeroDeAsiento(3);
		asientoLanchita.setEstado("D");
		usuarioEstandar.reservar(asientoLanchita);
		assertEquals("R", asientoLanchita.getEstado());
		comunicadorDeAerolineaLanchita.comprarAsiento(asientoLanchita, usuarioEstandar);
		assertEquals("C", asientoLanchita.getEstado());
		verify(aerolineaLanchita).reservar("01202022220202-3", "dni");
		verify(aerolineaLanchita).comprar("01202022220202-3");
	}
	
	@Test(expected = AsientoNoDisponibleException.class)
	public void reservaUsuarioYCompraOtroUsuarioLanchitaRompe() {
		asientoLanchita.setCodigoDeVuelo("01202022220202");
		asientoLanchita.setNumeroDeAsiento(3);
		asientoLanchita.setEstado("D");
		usuarioEstandar.reservar(asientoLanchita);
		assertEquals("R", asientoLanchita.getEstado());
		comunicadorDeAerolineaLanchita.comprarAsiento(asientoLanchita, otroUsuarioEstandar);
	}
	
	@Test
	public void reservaYCompraExitosaDeUnAsientoDisponibleOceanic() {
		asientoOceanic.setCodigoDeVuelo("OC100");
		asientoOceanic.setNumeroDeAsiento(10);
		asientoOceanic.setEstado("D");
		usuarioEstandar.reservar(asientoOceanic);
		assertEquals("R", asientoOceanic.getEstado());
		comunicadorDeAerolineaOceanic.comprarAsiento(asientoOceanic, usuarioEstandar);
		assertEquals("C", asientoOceanic.getEstado());
	}
	
	@Test(expected = AsientoNoDisponibleException.class)
	public void reservaUsuarioYCompraOtroUsuarioOceanicRompe() {
		asientoOceanic.setCodigoDeVuelo("OC100");
		asientoOceanic.setNumeroDeAsiento(10);
		asientoOceanic.setEstado("D");
		usuarioEstandar.reservar(asientoOceanic);
		assertEquals("R", asientoOceanic.getEstado());
		comunicadorDeAerolineaOceanic.comprarAsiento(asientoOceanic, otroUsuarioEstandar);
	}
	
	@Test
	public void reservanDosExpiroElPrimeroYElSegundoCompra() {
		asientoOceanic.setCodigoDeVuelo("OC100");
		asientoOceanic.setNumeroDeAsiento(10);
		asientoOceanic.setEstado("D");
		usuarioEstandar.reservar(asientoOceanic);
		assertEquals("R", asientoOceanic.getEstado());
		otroUsuarioEstandar.reservar(asientoOceanic);
		Usuario usuario = comunicadorDeAerolineaOceanic.reservaExpirada("OC100", "10");
		comunicadorDeAerolineaOceanic.comprarAsiento(asientoOceanic, otroUsuarioEstandar);
		assertEquals("C", asientoOceanic.getEstado());
	}
	
	@Test
	public void reservanDosCompraElPrimeroYElSegundoFalla() {
		asientoOceanic.setCodigoDeVuelo("OC100");
		asientoOceanic.setNumeroDeAsiento(10);
		asientoOceanic.setEstado("D");
		usuarioEstandar.reservar(asientoOceanic);
		assertEquals("R", asientoOceanic.getEstado());
		otroUsuarioEstandar.reservar(asientoOceanic);
//		comunicadorDeAerolineaOceanic.comprarAsiento(asientoOceanic, usuarioEstandar);
//		assertEquals("C", asientoOceanic.getEstado());
//		comunicadorDeAerolineaOceanic.comprarAsiento(asientoOceanic, otroUsuarioEstandar);
	}
}