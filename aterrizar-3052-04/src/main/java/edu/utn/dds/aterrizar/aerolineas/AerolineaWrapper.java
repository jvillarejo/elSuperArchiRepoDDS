package edu.utn.dds.aterrizar.aerolineas;

import java.util.ArrayList;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Reserva;

public abstract class AerolineaWrapper {
	private ArrayList<Reserva> reservas;
	private ArrayList<String> codigosComprados;
	
	public AerolineaWrapper() {
		this.reservas = new ArrayList<Reserva>();
		this.codigosComprados = new ArrayList<String>();
	}
	
	protected void reservarAsiento(Asiento asiento, Usuario usuario){
		String codigo = getCodigo(asiento);
		if(esAsientoComprado(codigo)){
			throw new AsientoNoDisponibleException("El asiento ya fue comprado");
		}else{
			asiento.setEstado("R");
			Reserva reserva = contieneCodigo(codigo);
			if(reserva != null && !contieneUsuario(reserva, usuario.getDni()))
				reserva.addUsuario(usuario);
			else
				throw new AsientoReservadoException("El asiento ya fue reservado");
		}
	}
	
	protected void sobreReservarAsiento(Asiento asiento, Usuario usuario){
		Reserva reserva = new Reserva();
		reserva.setCodigo(getCodigo(asiento));
		reserva.addUsuario(usuario);
		reservas.add(reserva);
	}
	
	private boolean esAsientoComprado(String codigo){
		return codigosComprados.contains(codigo);
	}
	
	protected void comprarAsiento(Asiento asientoDisponible, Usuario usuario){
		String codigo = getCodigo(asientoDisponible);
		Reserva reserva = contieneCodigo(codigo);
		if(reserva == null){
			codigosComprados.add(codigo);
		}else if(usuario.getDni().equals(reserva.getUsuarios().get(0).getDni())){
			codigosComprados.add(codigo);
			reservas.remove(reserva);
		}else{
			throw new AsientoNoDisponibleException("El asiento ha sido reservado por otra persona");
		}
	}
	
	public Usuario reservaExpirada(String codigo, String numeroAsiento){
		Reserva reserva = contieneCodigo(joinStrings(codigo, numeroAsiento));
		reserva.getUsuarios().remove(0);
		return reserva.getUsuarios().get(0);
	}
	
	private boolean contieneUsuario(Reserva reserva, String dni) {
		for(Usuario usuario : reserva.getUsuarios())
			if(usuario.getDni().equals(dni))
				return true;
		return false;
	}

	private Reserva contieneCodigo(String codigo) {
		for(Reserva reserva : reservas)
			if(reserva.getCodigo().equals(codigo))
				return reserva;
		return null;
	}
	
	protected String getCodigo(Asiento asientoDisponible) {
		return joinStrings(asientoDisponible.getCodigoDeVuelo(), asientoDisponible.getNumeroDeAsiento().toString());
	}
	
	private String joinStrings(String codigo, String numeroAsiento){
		return codigo + "-" + numeroAsiento;
	}
}
