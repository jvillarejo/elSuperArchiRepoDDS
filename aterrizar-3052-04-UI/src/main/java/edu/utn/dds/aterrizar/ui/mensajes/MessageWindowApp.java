package edu.utn.dds.aterrizar.ui.mensajes;

import java.awt.Button;

import org.uqbar.arena.Application;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;

import edu.utn.dds.aterrizar.ui.mensajes.Accion;
import edu.utn.dds.aterrizar.ui.mensajes.MessageWindowBuilder;
import edu.utn.dds.aterrizar.vuelo.Asiento;

/**
 * Correr esta clase con el siguiente argument
 * 
 * -Djava.system.class.loader=org.uqbar.arena.aop.ArenaClassLoader
 */
public class MessageWindowApp extends Application {

	private static Asiento asiento;
	private static RuntimeException e;

	public static void main(String[] args) {
		asiento = new Asiento();
		asiento.setCodigoDeVuelo("LIKE80085");
		
		e = new RuntimeException("No se pudo establecer comunicacion");
		
		MessageWindowApp messageWindowApp = new MessageWindowApp();
		
		messageWindowApp.start();
		
		
	}

//	@Override
//	protected Window<?> createMainWindow() {
//		return new MessageWindowBuilder(Accion.RESERVA).build(this, asiento);
//	}
//	
//	@Override
//	protected Window<?> createMainWindow() {
//		return new MessageWindowBuilder(Accion.COMPRA).build(this, asiento);
//	}
//	
//	@Override
//	protected Window<?> createMainWindow() {
//		return new MessageWindowBuilder(Accion.RESERVA).build(this, e);
//	}
	
//	@Override
//	protected Window<?> createMainWindow() {
//		return new MessageWindowBuilder(Accion.COMPRA).build(this, e);
//	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new SobreReservaMessageWindowBuilder().build(this, asiento);
	}
	
	
}