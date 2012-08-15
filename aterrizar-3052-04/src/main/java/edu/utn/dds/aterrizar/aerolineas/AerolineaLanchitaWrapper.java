package edu.utn.dds.aterrizar.aerolineas;

import java.util.List;

import com.lanchita.AerolineaLanchita;
import com.lanchita.excepciones.CodigoErroneoException;
import com.lanchita.excepciones.EstadoErroneoException;

import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.parser.*;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.*;


/**
 * Representa una implementación del pattern Adapter entre AerolineaLanchita y el model
 * @author clari, juani
 *
 */
public class AerolineaLanchitaWrapper extends AerolineaWrapper implements Aerolinea {
   // private static final AerolineaLanchita aerolinea= AerolineaLanchita.getInstance();
	private static final double PORCENTAJE_DE_VENTA = 0.15;
	private Parser lanchitaParser;
	private AerolineaLanchita aerolineaLanchita;
	
	/**
	 * Constructor del wrapper con su correspondiente parser específico
	 * @param aerolineaLanchita 
	 * @param parser
	 */
	public AerolineaLanchitaWrapper(AerolineaLanchita aerolineaLanchita, Parser parser ){
		super();
		this.lanchitaParser= parser;
		this.aerolineaLanchita = aerolineaLanchita;
	}

	/**
	 * Dado un vuelo (origen, destino, fecha), hace una query a AerolineaLanchita para obtener los asientos disponibles
	 * (un array de arrays de String) y los parsea para obtener una lista de Asientos. 
	 */
	@Override
	public List<VueloDirecto> buscarVuelos(Busqueda busqueda) {

//		String[][] asientosDisponibles = aerolineaLanchita.getAsientosDisponibles(busqueda.getOrigen(), busqueda.getDestino(), busqueda.getFecha());
		String[][] asientosDisponibles = new String[][]{{ "01202022220202-3", "159.90", "P", "V", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012" }};

		return this.lanchitaParser.parseDisponibles(asientosDisponibles, busqueda, this);
	}

	/**
	 * Dada una lista de Asientos disponibles y un Usuario, usa la interfaz de compra de AerolinaLanchita.
	 * @throws AsientoNoDisponibleException si el asiento no está disponible.
	 */
	@Override
	public void comprarAsiento(Asiento asientoDisponible,Usuario usuario) {
		super.comprarAsiento(asientoDisponible, usuario);
		try{
			aerolineaLanchita.comprar(getCodigo(asientoDisponible));
		}
		catch(EstadoErroneoException e){
			throw new AsientoNoDisponibleException(e);
		}
		catch(CodigoErroneoException e1){
			throw new RuntimeException("Código inválido");
		}
		asientoDisponible.setEstado("C");
	}

	@Override
	public Double getPorcentajeDeVenta() {
		return PORCENTAJE_DE_VENTA; 
	}
	
	@Override
	public void reservarAsiento(Asiento asiento, Usuario usuario) {
		try{
			aerolineaLanchita.reservar(getCodigo(asiento), usuario.getDni());
			super.reservarAsiento(asiento, usuario);
		}catch(CodigoErroneoException e){
			throw new RuntimeException("Codigo recibido no existe", e);
		}catch(EstadoErroneoException e1){
			throw new AsientoNoDisponibleException(e1);
		}
	}

}
