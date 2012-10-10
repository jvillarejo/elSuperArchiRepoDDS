package edu.utn.dds.aterrizar.ui.interaccionusuario;

import java.util.List;

import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class ReservasWindow extends BaseUserWindow {

	private static final long serialVersionUID = 5992007318643596135L;

	public ReservasWindow(WindowOwner owner, Usuario model) {
		super(owner, model);
	}

	@Override
	protected String getLabelText() {
		return "Reservas de " + getModelObject().getNombreCompleto();
	}

	@Override
	protected List<Asiento> getResultados(Usuario model) {
		return null;
	}

}
