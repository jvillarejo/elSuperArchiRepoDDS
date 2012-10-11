package edu.utn.dds.aterrizar.ui.busqueda;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import edu.utn.dds.aterrizar.ui.mensajes.Accion;
import edu.utn.dds.aterrizar.ui.mensajes.MessageWindowBuilder;
import edu.utn.dds.aterrizar.vuelo.Asiento;

/**
 * Correr esta clase con el siguiente argument
 * 
 * -Djava.system.class.loader=org.uqbar.arena.aop.ArenaClassLoader
 */
public class BusquedaVuelosApp extends Application {

	public static void main(String[] args) {
		new BusquedaVuelosApp().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		Asiento asiento = new Asiento();
		asiento.setCodigoDeVuelo("agasgiji214124");
		
		return new MessageWindowBuilder(Accion.COMPRA).build(this, asiento);
	}
	
}