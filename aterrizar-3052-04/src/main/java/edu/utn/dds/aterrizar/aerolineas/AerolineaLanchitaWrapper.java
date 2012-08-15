package edu.utn.dds.aterrizar.aerolineas;

import java.util.ArrayList;
import java.util.List;

import com.lanchita.AerolineaLanchita;
import com.lanchita.excepciones.CodigoErroneoException;
import com.lanchita.excepciones.EstadoErroneoException;

import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.parser.*;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.*;


/**
 * Representa una implementación del pattern Adapter entre AerolineaLanchita y el model
 * @author clari, juani
 *
 */
public class AerolineaLanchitaWrapper implements Aerolinea {
   // private static final AerolineaLanchita aerolinea= AerolineaLanchita.getInstance();
	private static final double PORCENTAJE_DE_VENTA = 0.15;
	private Parser lanchitaParser;
	private AerolineaLanchita aerolineaLanchita;
	private ArrayList<Reserva> reservas;
	
	/**
	 * Constructor del wrapper con su correspondiente parser específico
	 * @param aerolineaLanchita 
	 * @param parser
	 */
	public AerolineaLanchitaWrapper(AerolineaLanchita aerolineaLanchita, Parser parser ){
		this.lanchitaParser= parser;
		this.aerolineaLanchita = aerolineaLanchita;
		this.reservas = new ArrayList<Reserva>();
	}

	/**
	 * Dado un vuelo (origen, destino, fecha), hace una query a AerolineaLanchita para obtener los asientos disponibles
	 * (un array de arrays de String) y los parsea para obtener una lista de Asientos. 
	 */
	@Override
	public List<VueloDirecto> buscarVuelos(Busqueda busqueda) {

		String[][] asientosDisponibles = aerolineaLanchita.getAsientosDisponibles(busqueda.getOrigen(), busqueda.getDestino(), busqueda.getFecha());

		return this.lanchitaParser.parseDisponibles(asientosDisponibles, busqueda, this);
	}

	/**
	 * Dada una lista de Asientos disponibles y un Usuario, usa la interfaz de compra de AerolinaLanchita.
	 * @throws AsientoNoDisponibleException si el asiento no está disponible.
	 */
	@Override
	public void comprarAsiento(Asiento asientoDisponible,Usuario usuario) {
		try{

			aerolineaLanchita.comprar(getCodigoLanchita(asientoDisponible), usuario.getDni());

		}
		catch(EstadoErroneoException e){
			throw new AsientoNoDisponibleException(e);
		}
		catch(CodigoErroneoException e1){
			throw new RuntimeException("Código inválido");
		}
	
		asientoDisponible.setEstado("C");
	}

	private String getCodigoLanchita(Asiento asientoDisponible) {
		return asientoDisponible.getCodigoDeVuelo() + "-" + asientoDisponible.getNumeroDeAsiento();

	}
	@Override
	public Double getPorcentajeDeVenta() {
		return PORCENTAJE_DE_VENTA; 
	}
	
	@Override
	public void reservarAsiento(Asiento asiento, Usuario usuario) {
		try{
			aerolineaLanchita.reservar(getCodigoLanchita(asiento), usuario.getDni());
			asiento.setEstado("R");
			Reserva reserva = this.contieneCodigo(getCodigoLanchita(asiento));
			if(reserva != null){
				if(!this.contieneUsuario(reserva, usuario.getDni()))
					reserva.addUsuario(usuario);
			}else{
				reserva = new Reserva();
				reserva.setCodigo(getCodigoLanchita(asiento));
				reserva.addUsuario(usuario);
				reservas.add(reserva);
			}
		}catch(CodigoErroneoException e){
			throw new RuntimeException("Codigo recibido no existe", e);
		}catch(EstadoErroneoException e1){
			throw new AsientoNoDisponibleException(e1);
		}
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

}
