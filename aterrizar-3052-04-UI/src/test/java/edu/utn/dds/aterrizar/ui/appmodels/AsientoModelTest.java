package edu.utn.dds.aterrizar.ui.appmodels;

import org.junit.Test;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Clase;
import edu.utn.dds.aterrizar.vuelo.Ubicacion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AsientoModelTest {

	@Test
	public void getNombreAerolineaDeberiaDevolverElNombreDeLaAerolineaDelAsientoOriginal() {
		Aerolinea laPresidencial = mock(Aerolinea.class);
		when(laPresidencial.getName()).thenReturn("Aerolinea Presidencial");
		
		AsientoModel asientoDeCris = new AsientoModel(new Asiento(laPresidencial));
		
		assertEquals("Aerolinea Presidencial", asientoDeCris.getNombreAerolinea());
	}

	@Test
	public void getCodigoDeVueloDeberiaDevolverElCodigoDeLaBusquedaDelAsientoOriginal() {
		Asiento asientoDeOceanic = new Asiento();
		asientoDeOceanic.setCodigoDeVuelo("AHF43");
		
		assertEquals("AHF43", new AsientoModel(asientoDeOceanic).getCodigoDeVuelo());
	}
	
	@Test
	public void getPrecioDeberiaDevolverElPrecioDelAsientoOriginal() {
		Asiento asientoDeCienPesos = new Asiento();
		asientoDeCienPesos.setPrecio(100.0);
		
		assertEquals("100.0", new AsientoModel(asientoDeCienPesos).getPrecio());
	}
	
	@Test
	public void getClaseDeberiaDevolverElNombreDeLaClaseDelAsientoOriginalEnPascalCase() {
		Asiento asientoEnPrimera = new Asiento();
		asientoEnPrimera.setClase(Clase.PRIMERA);
		
		assertEquals("Primera", new AsientoModel(asientoEnPrimera).getClase());
	}
	
	@Test
	public void getUbicacionDeberiaDevolverElNombreDeLaUbicacionDelAsientoOriginalEnPascalCase() {
		Asiento asientoEnVentanilla = new Asiento();
		asientoEnVentanilla.setUbicacion(Ubicacion.VENTANILLA);
		
		assertEquals("Ventanilla", new AsientoModel(asientoEnVentanilla).getUbicacion());
	}

}
