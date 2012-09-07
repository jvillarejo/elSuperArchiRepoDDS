package edu.utn.dds.aterrizar.usuario;

public class SuscripcionEstandar extends TipoDeSuscripcion {
	
	public void toVip(Usuario unUsuario){
		if (unUsuario.getTotalCompras() >10000.0){
			unUsuario.setTipo(new SuscripcionVip());
		}
	}
}
