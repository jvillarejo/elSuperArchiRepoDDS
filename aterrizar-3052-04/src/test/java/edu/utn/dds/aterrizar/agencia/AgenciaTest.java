package edu.utn.dds.aterrizar.agencia;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.agencia.Agencia;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
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
		Busqueda busquedaDummy = new Busqueda();
		laAgencia.buscarVuelos(busquedaDummy, juanMockito);
		
		verify(aerolineaMockito).buscarVuelos(busquedaDummy);
		verify(aerolineaPrivadaDelTurco).buscarVuelos(busquedaDummy);
	}
	
	@Test
	public void laAgenciaRegistraLaConsultaEnElUsuario() {
		Busqueda busquedaDummy = new Busqueda();
		laAgencia.buscarVuelos(busquedaDummy, juanMockito);

		verify(juanMockito).registrarConsulta(busquedaDummy);
	}
}
