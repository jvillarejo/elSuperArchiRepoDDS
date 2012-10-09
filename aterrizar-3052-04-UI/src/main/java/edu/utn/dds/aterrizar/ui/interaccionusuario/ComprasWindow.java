package edu.utn.dds.aterrizar.ui.interaccionusuario;

import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.usuario.Usuario;

public class ComprasWindow extends BaseUserWindow {

	private static final long serialVersionUID = -3514713865890313537L;

	public ComprasWindow(WindowOwner owner, Usuario model) {
		super(owner, model);
	}

	@Override
	protected String getLabelText() {
		return "Compras de " + getModelObject().getNombreCompleto();
	}


}
