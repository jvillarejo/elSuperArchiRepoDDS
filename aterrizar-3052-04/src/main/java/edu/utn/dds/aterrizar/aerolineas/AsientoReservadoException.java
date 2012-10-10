package edu.utn.dds.aterrizar.aerolineas;

public class AsientoReservadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 356785470816669007L;

	public AsientoReservadoException(Throwable e){
		super (e);
	}

	public AsientoReservadoException(String message) {
		super(message);
	}
}