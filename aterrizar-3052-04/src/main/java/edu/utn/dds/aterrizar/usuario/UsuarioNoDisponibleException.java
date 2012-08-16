package edu.utn.dds.aterrizar.usuario;

@SuppressWarnings("serial")
public class UsuarioNoDisponibleException extends RuntimeException {
	public UsuarioNoDisponibleException(Throwable e){
		super (e);
	}

	public UsuarioNoDisponibleException(String message) {
		super(message);
	}
}