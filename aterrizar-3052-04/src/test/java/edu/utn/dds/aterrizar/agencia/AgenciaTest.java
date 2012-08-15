package edu.utn.dds.aterrizar.agencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.staccatocommons.collections.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.agencia.Agencia;
import edu.utn.dds.aterrizar.usuario.ConsultaAsientos;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroDummy;

import static org.mockito.Mockito.*;


public class AgenciaTest {

	private Agencia laAgencia;
	private List<Aerolinea> aerolineas;
	private Aerolinea aerolineaMockito;
	private Aerolinea aerolineaPrivadaDelTurco;
	private Usuario juanMockito;

	@Before
	public void setUp() {
		aerolineaMockito = mock(Aerolinea.class);
		aerolineaPrivadaDelTurco = mock(Aerolinea.class);
		
		juanMockito = mock(Usuario.class);
		when(juanMockito.getFiltro()).thenReturn(new FiltroDummy());
		
		aerolineas = Arrays.asList(aerolineaMockito, aerolineaPrivadaDelTurco);
		laAgencia = new Agencia(aerolineas);
	}
	
	@Test
	public void laAgenciaBuscaAsientosEnTodasLasAerolineas() {
		laAgencia.buscarVuelos(mock(Busqueda.class), juanMockito, new FiltroDummy());
		
		verify(aerolineaMockito).buscarVuelos(any(Busqueda.class));
		verify(aerolineaPrivadaDelTurco).buscarVuelos(any(Busqueda.class));
	}
	
	@Test
	public void laAgenciaRegistraLaConsultaEnElUsuario() {
		laAgencia.buscarVuelos(mock(Busqueda.class), juanMockito, new FiltroDummy());

		verify(juanMockito).registrarConsulta(any(ConsultaAsientos.class));
	}
	
	@Test
	public void laAgenciaFiltraLosAsientosSegunLosCriterios() {
		//TODO: mejorar este test...
		Busqueda deNeuquenALaQuiaca = mock(Busqueda.class);
		when(aerolineaMockito.buscarAsientos(deNeuquenALaQuiaca)).thenReturn(Arrays.asList(mock(Asiento.class)));
		
		FiltroAsiento filtros = mock(FiltroAsiento.class);
		when(filtros.filtrar(any(Stream.class))).thenReturn(mock(Stream.class));
		
		laAgencia.buscarVuelos(deNeuquenALaQuiaca, juanMockito, filtros);

		verify(filtros).filtrar(any(Stream.class));
	}
}
