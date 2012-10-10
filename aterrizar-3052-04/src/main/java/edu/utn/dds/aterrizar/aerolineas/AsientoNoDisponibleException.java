package edu.utn.dds.aterrizar.aerolineas;

public class AsientoNoDisponibleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 356785470816669007L;

	public AsientoNoDisponibleException(Throwable e){
		super (e);
	}

	public AsientoNoDisponibleException(String message) {
		super(message);
	}
}
