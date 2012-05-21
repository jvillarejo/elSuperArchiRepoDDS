package edu.utn.dds.aterrizar.usuario;

public class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	
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

}
