package edu.utn.dds.aterrizar.ui.appmodels;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.utn.dds.aterrizar.usuario.SuscripcionEstandar;
import edu.utn.dds.aterrizar.usuario.SuscripcionGratuita;
import edu.utn.dds.aterrizar.usuario.Usuario;

public class UsuarioModelTest {

	@Test
	public void getNombreCompletoDeberiaDevolverNombreYApellidoConcatenadosConUnEspacio() {
		Usuario unTipoConUnNokiaMilCien = new Usuario("Franco", "Bulgarelli", "123456", new SuscripcionGratuita());
		assertEquals("Franco Bulgarelli", new UsuarioModel(unTipoConUnNokiaMilCien).getNombreCompleto());
	}
	
	@Test
	public void getMensajeBienvenidaDeberiaDevolverElNombreCompletoMasElMensaje() {
		Usuario unExAyudanteDeOperativos = new Usuario("Matias", "Dumrauf", "1233354", new SuscripcionEstandar());
		
		assertEquals(
			"Hola Matias Dumrauf\n\n" + "¿Qué desea hacer?\n",
			new UsuarioModel(unExAyudanteDeOperativos).getMensajeBienvenida());
	}
	
}
