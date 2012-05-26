package edu.utn.dds.aterrizar.usuario;

public class Usuario {

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
	
	public double recargo() {
		// TODO Implementar recargo segun el tipo de usuario
		return 0;
	}

}
