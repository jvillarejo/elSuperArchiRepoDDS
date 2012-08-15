package edu.utn.dds.aterrizar.aerolineas;

import java.util.ArrayList;

import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Reserva;

public abstract class AerolineaWrapper {
	private ArrayList<Reserva> reservas;
	private ArrayList<String> codigosComprados;
	
	public AerolineaWrapper(){
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
			if(reserva != null){
				if(!contieneUsuario(reserva, usuario.getDni()))
					reserva.addUsuario(usuario);
			}else{
				reserva = new Reserva();
				reserva.setCodigo(getCodigo(asiento));
				reserva.addUsuario(usuario);
				reservas.add(reserva);
			}
		}
	}
	
	private boolean esAsientoComprado(String codigo){
		return codigosComprados.contains(codigo);
	}
	
	protected void comprarAsiento(Asiento asientoDisponible, Usuario usuario){
		String codigo = getCodigo(asientoDisponible);
		Reserva reserva = contieneCodigo(codigo);
		if(usuario.getDni().equals(reserva.getUsuarios().get(0).getDni())){
			codigosComprados.add(codigo);
			if(reserva != null)
				reservas.remove(reserva);
		}else{
			throw new AsientoNoDisponibleException("El asiento ha sido reservado por otra persona");
		}
	}
	
	public void reservaExpirada(Reserva reserva){
		reserva.getUsuarios().remove(0);
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
		return asientoDisponible.getCodigoDeVuelo() + "-" + asientoDisponible.getNumeroDeAsiento();
	}
}
