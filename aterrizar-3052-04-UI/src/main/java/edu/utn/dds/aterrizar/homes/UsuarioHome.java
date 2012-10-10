package edu.utn.dds.aterrizar.homes;

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

	public Usuario getDefaultUser() {
		return new Usuario("Bruce", "Wayne", "22868921", new SuscripcionEstandar());
	}

}
