package edu.utn.dds.aterrizar.ui.interaccionusuario;

import java.util.List;

import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class ComprasWindow extends BaseUserWindow {

	private static final long serialVersionUID = -3514713865890313537L;

	public ComprasWindow(WindowOwner owner, Usuario model) {
		super(owner, model);
	}

	@Override
	protected String getLabelText() {
		return "Compras de " + getModelObject().getNombreCompleto();
	}

	@Override
	protected List<Asiento> getAsientos(Usuario usuario) {
		return usuario.getComprasEfectuadas();
	}


}
