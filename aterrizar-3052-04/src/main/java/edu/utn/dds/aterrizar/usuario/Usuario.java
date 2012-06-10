package edu.utn.dds.aterrizar.usuario;

import java.util.Collection;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public abstract class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	
	//TODO Agregar tipo de usuario VIP, Estandar, los que no pagan
	public Usuario(String nombre, String apellido, String dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}
	public String getDni() {
		return this.dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
	
	public abstract double recargo();
	
	public abstract Collection<Asiento> filtrarAsientos(Collection<Asiento> asientos);

}
