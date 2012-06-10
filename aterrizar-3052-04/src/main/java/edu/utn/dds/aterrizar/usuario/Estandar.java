package edu.utn.dds.aterrizar.usuario;

import java.util.Collection;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public class Estandar extends Usuario {

	public Estandar (String nombre, String apellido, String dni){
		super(nombre, apellido, dni);
	}
	@Override
	public double recargo() {
		return 0.0;
	}
	@Override
	public Collection <Asiento> filtrarAsientos (Collection<Asiento> asientos){
		return asientos;
	}
}
