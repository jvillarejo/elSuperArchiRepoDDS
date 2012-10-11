package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public class SuscripcionEstandar extends TipoDeSuscripcion {
	
	public void toVip(Usuario unUsuario){
		if (unUsuario.getTotalCompras() >10000.0){
			unUsuario.setTipo(new SuscripcionVip());
		}
	}
	
	@Override
	public void reservar(Asiento asiento, Usuario usuario) {
		asiento.reservar(usuario);
	}
	
	@Override
	public void sobreReservar(Asiento asiento, Usuario usuario) {
		asiento.sobreReservar(usuario);
	}
}
