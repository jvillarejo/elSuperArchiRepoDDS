package edu.utn.dds.aterrizar.ui.busqueda;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

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
		return new BusquedaVuelosWindow(this);
	}
	
}