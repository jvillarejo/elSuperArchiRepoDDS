package edu.utn.dds.aterrizar.ui.appmodels;

import edu.utn.dds.aterrizar.usuario.Usuario;

public class UsuarioModelAdapter {

	public static UsuarioModel toApplicationModel(Usuario usuario) {
		return new UsuarioModel(usuario);
	}

}
