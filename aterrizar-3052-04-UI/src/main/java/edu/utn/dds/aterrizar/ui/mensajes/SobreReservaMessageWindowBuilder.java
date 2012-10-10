package edu.utn.dds.aterrizar.ui.mensajes;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public class SobreReservaMessageWindowBuilder extends MessageWindowBuilder {

	public SobreReservaMessageWindowBuilder() {
		super(Accion.RESERVA);
	}
	
	@Override
	public SimpleMessageWindow build(WindowOwner owner, Asiento asiento) {
		SimpleMessageWindow simpleMessageWindow = super.build(owner, asiento);
		//TODO cambiar esto cuando este en agencia la sobrereserva
		return simpleMessageWindow.addActionButton("Sobrereservar", new MessageSend(simpleMessageWindow, "close"));
	}
	
	@Override
	protected String buildSuccessMessage(String codigoDeVuelo) {
		return "El asiento " + codigoDeVuelo + " ya se encuentra " + this.accion.getAdjetivoAccion() + 
				"\n" +
				"¿Que desea hacer?"; 
	}

}
