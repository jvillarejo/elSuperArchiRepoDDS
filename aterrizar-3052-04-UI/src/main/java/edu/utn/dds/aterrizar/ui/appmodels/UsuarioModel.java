package edu.utn.dds.aterrizar.ui.appmodels;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;

@Observable
public class UsuarioModel {

	private Usuario usuarioOriginal;
	private List<AsientoModel> resultados;

	// *************************************************************************
	// ** Constructor
	// *************************************************************************
	
	public UsuarioModel(Usuario usuario) {
		this.setUsuarioOriginal(usuario);
	}

	// *************************************************************************
	// ** Computed properties
	// *************************************************************************

	public String getNombreCompleto() {
		return this.getUsuarioOriginal().getNombre() + ' ' + this.getUsuarioOriginal().getApellido();
	}
	
	public String getMensajeBienvenida() {
		return "Hola " + this.getNombreCompleto() + "\n\n" + "¿Qué desea hacer?" + "\n";
	}
	
	public List<Asiento> getComprasEfectuadas() {
		return this.getUsuarioOriginal().getComprasEfectuadas();
	}
	
	// *************************************************************************
	// ** Accesors
	// *************************************************************************
	
	public Usuario getUsuarioOriginal() {
		return usuarioOriginal;
	}

	private void setUsuarioOriginal(Usuario usuarioOriginal) {
		this.usuarioOriginal = usuarioOriginal;
	}

	public List<AsientoModel> getResultados() {
		return resultados;
	}

	public void setResultados(List<AsientoModel> resultados) {
		this.resultados = resultados;
	}
	
}
