package edu.utn.dds.aterrizar.agencia;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.agencia.Agencia;
import edu.utn.dds.aterrizar.usuario.ConsultaVuelos;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroDummy;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroLibres;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.OrdenPorTiempoDeVuelo;

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
		List<Filtro<Asiento>> filtros = Arrays.asList(new FiltroDummy(), new FiltroLibres());
		laAgencia.buscarVuelos(new ConsultaVuelos(mock(Busqueda.class), filtros, new OrdenPorTiempoDeVuelo()), juanMockito);
		
		verify(aerolineaMockito).buscarVuelos(any(Busqueda.class));
		verify(aerolineaPrivadaDelTurco).buscarVuelos(any(Busqueda.class));
	}
	
	@Test
	public void laAgenciaRegistraLaConsultaEnElUsuario() {
		List<Filtro<Asiento>> filtros = Arrays.asList(new FiltroDummy(), new FiltroLibres());
		laAgencia.buscarVuelos(new ConsultaVuelos(mock(Busqueda.class), filtros, new OrdenPorTiempoDeVuelo()), juanMockito);

		verify(juanMockito).registrarConsulta(any(ConsultaVuelos.class));
	}
}
