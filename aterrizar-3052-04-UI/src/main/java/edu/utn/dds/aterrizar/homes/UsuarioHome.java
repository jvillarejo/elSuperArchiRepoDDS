package edu.utn.dds.aterrizar.homes;

import edu.utn.dds.aterrizar.ui.appmodels.UsuarioModel;
import edu.utn.dds.aterrizar.ui.appmodels.UsuarioModelAdapter;
import edu.utn.dds.aterrizar.usuario.SuscripcionEstandar;
import edu.utn.dds.aterrizar.usuario.Usuario;

public class UsuarioHome {

	private static UsuarioHome INSTANCE;

	public static UsuarioHome getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new UsuarioHome(); 
		} 
		
		return INSTANCE;
	}

	public UsuarioModel getDefaultUser() {
		return UsuarioModelAdapter.toApplicationModel(new Usuario("Bruce", "Wayne", "22868921", new SuscripcionEstandar()));
	}

}
