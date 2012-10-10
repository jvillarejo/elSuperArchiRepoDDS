package edu.utn.dds.aterrizar.ui.interaccionusuario;

import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.usuario.Usuario;

public class ReservasWindow extends BaseUserWindow {

	private static final long serialVersionUID = 5992007318643596135L;

	public ReservasWindow(WindowOwner owner, Usuario model) {
		super(owner, model);
	}

	@Override
	protected String getLabelText() {
		return "Reservas de " + getModelObject().getNombreCompleto();
	}

}
