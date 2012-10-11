package edu.utn.dds.aterrizar.vuelo;

import java.util.ArrayList;

import edu.utn.dds.aterrizar.usuario.Usuario;

public class Reserva {
	private String codigo;
	private ArrayList<Usuario> usuarios;
	
	public Reserva(){
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigo() {
		return codigo;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
}