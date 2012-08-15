package edu.utn.dds.aterrizar.vuelo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.lanchita.AerolineaLanchita;
import com.oceanic.AerolineaOceanic;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaWrapper;
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
	private Asiento asiento;
	private Aerolinea comunicadorDeAerolinea;
	private AerolineaLanchita aerolineaLanchita;

	@Before
	public void setUp() {
		aerolineaLanchita = mock(AerolineaLanchita.class);
		usuarioGratuito = new Usuario("nombre", "apellido", "dni", new SuscripcionGratuita());
		usuarioVip = new Usuario("nombre", "apellido", "dni", new SuscripcionVip());
		usuarioEstandar = new Usuario("nombre", "apellido", "dni", new SuscripcionEstandar());
		otroUsuarioEstandar = new Usuario("otroNombre", "otroApellido", "otroDni", new SuscripcionEstandar());
		this.comunicadorDeAerolinea = new AerolineaLanchitaWrapper(aerolineaLanchita, new Parser());
		asiento = new Asiento(this.comunicadorDeAerolinea);
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
		asiento.setCodigoDeVuelo("01202022220202");
		asiento.setNumeroDeAsiento(3);
		asiento.setEstado("D");
		usuarioEstandar.reservar(asiento);
		assertEquals("R", asiento.getEstado());
		comunicadorDeAerolinea.comprarAsiento(asiento, usuarioEstandar);
		assertEquals("C", asiento.getEstado());
		verify(aerolineaLanchita).reservar("01202022220202-3", "dni");
		verify(aerolineaLanchita).comprar("01202022220202-3");
	}
	
	@Test(expected = AsientoNoDisponibleException.class)
	public void reservaUsuarioYCompraOtroUsuarioLanchitaRompe() {
		asiento.setCodigoDeVuelo("01202022220202");
		asiento.setNumeroDeAsiento(3);
		asiento.setEstado("D");
		usuarioEstandar.reservar(asiento);
		assertEquals("R", asiento.getEstado());
		comunicadorDeAerolinea.comprarAsiento(asiento, otroUsuarioEstandar);
	}
}