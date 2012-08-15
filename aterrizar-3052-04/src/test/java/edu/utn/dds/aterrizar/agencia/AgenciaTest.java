package edu.utn.dds.aterrizar.agencia;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.agencia.Agencia;
import edu.utn.dds.aterrizar.usuario.ConsultaAsientos;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

import static org.mockito.Mockito.*;


public class AgenciaTest {

	private Agencia laAgencia;
	private List<Aerolinea> aerolineas;
	private Aerolinea aerolineaMockito;
	private Aerolinea aerolineaPrivadaDelTurco;

	@Before
	public void setUp() {
		aerolineaMockito = mock(Aerolinea.class);
		aerolineaPrivadaDelTurco = mock(Aerolinea.class);
		aerolineas = new ArrayList<Aerolinea>();
		aerolineas.add(aerolineaMockito);
		aerolineas.add(aerolineaPrivadaDelTurco);
		
		laAgencia = new Agencia(aerolineas);
	}
	
	@Test
	public void laAgenciaBuscaAsientosEnTodasLasAerolineas() {
		laAgencia.buscarAsientos(mock(Busqueda.class), mock(Usuario.class), mock(FiltroAsiento.class));
		
		verify(aerolineaMockito).buscarAsientos(any(Busqueda.class));
		verify(aerolineaPrivadaDelTurco).buscarAsientos(any(Busqueda.class));
	}
	
	@Test
	public void laAgenciaRegistraLaConsultaEnElUsuario() {
		Usuario juanMockito = mock(Usuario.class);
		laAgencia.buscarAsientos(mock(Busqueda.class), juanMockito, mock(FiltroAsiento.class));

		verify(juanMockito).registrarConsulta(any(ConsultaAsientos.class));
	}
	
	@Test
	public void laAgenciaFiltraLosAsientosSegunLosCriterios() {
		FiltroAsiento filtros = mock(FiltroAsiento.class);
		laAgencia.buscarAsientos(mock(Busqueda.class), mock(Usuario.class), filtros);

		verify(filtros).filtrar(anyListOf(Asiento.class));
	}

	
}
