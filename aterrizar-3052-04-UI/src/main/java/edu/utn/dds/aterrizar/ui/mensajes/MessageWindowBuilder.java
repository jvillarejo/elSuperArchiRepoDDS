package edu.utn.dds.aterrizar.ui.mensajes;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public class MessageWindowBuilder {

	private Accion accion;

	public MessageWindowBuilder(Accion accion) {
		this.accion = accion;
	}
	
	public SimpleMessageWindow build(WindowOwner owner, Asiento asiento) {
		SimpleMessageWindow simpleMessageWindow = new SimpleMessageWindow(owner, this.buildSuccessMessage(asiento.getCodigoDeVuelo()));
		return simpleMessageWindow.addActionButton("Seguir Buscando", new MessageSend(simpleMessageWindow, "close"));
	}
	
	public SimpleMessageWindow build(WindowOwner owner, RuntimeException exception) {
		SimpleMessageWindow simpleMessageWindow = new SimpleMessageWindow(owner, this.buildErrorMessage(exception.getMessage()));
		return simpleMessageWindow.addActionButton("Aceptar", new MessageSend(simpleMessageWindow, "close"));
	}

	private String buildErrorMessage(String message) {
		return "Ha ocurrido un error en su " + this.getAccionError() + ":" + message + "\n" + "Por favor intente nuevamente"; 
	}

	protected String buildSuccessMessage(String codigoDeVuelo) {
		return "El asiento " + codigoDeVuelo + " ha sido " + getAccion() + "exitosamente";
	}
	
	private String getAccionError() {
		return this.accion.getAccion(); 
	}


	private String getAccion() {
		return this.accion.getAdjetivoAccion();
	}
}
