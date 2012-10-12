package edu.utn.dds.aterrizar.homes;

import com.lanchita.AerolineaLanchita;

import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaWrapper;
import edu.utn.dds.aterrizar.parser.Parser;
import edu.utn.dds.aterrizar.ui.appmodels.UsuarioModel;
import edu.utn.dds.aterrizar.ui.appmodels.UsuarioModelAdapter;
import edu.utn.dds.aterrizar.usuario.SuscripcionEstandar;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.Clase;

public class UsuarioHome {

	private static UsuarioHome INSTANCE;

	public static UsuarioHome getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new UsuarioHome(); 
		} 
		
		return INSTANCE;
	}

	public UsuarioModel getDefaultUser() {
		Usuario usuario = new Usuario("Bruce", "Wayne", "22868921", new SuscripcionEstandar());
		//Desde Aca hasta 
		Asiento asiento = new Asiento(new AerolineaLanchitaWrapper(AerolineaLanchita.getInstance(), new Parser()));
		asiento.setClase(Clase.EJECUTIVA);
		asiento.setCodigoDeVuelo("EFE01851295");
		asiento.setPrecio(Double.valueOf(575.00));
		asiento.setNumeroDeAsiento(7);
		Busqueda busqueda = new Busqueda("EZE", "NYC", "09/09/2012", "10/09/2012", null, null);
		asiento.setBusqueda(busqueda);
		usuario.registrarCompra(asiento);
		//Aca es hardcoding a modo de prueba
		return UsuarioModelAdapter.toApplicationModel(usuario);
	}

}
