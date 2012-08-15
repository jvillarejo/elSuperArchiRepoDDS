package edu.utn.dds.aterrizar.aerolineas;

import java.util.List;

import com.lanchita.AerolineaLanchita;
import com.lanchita.excepciones.CodigoErroneoException;
import com.lanchita.excepciones.EstadoErroneoException;

import edu.utn.dds.aterrizar.escalas.Vuelo;
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
	
	/**
	 * Constructor del wrapper con su correspondiente parser específico
	 * @param aerolineaLanchita 
	 * @param parser
	 */
	public AerolineaLanchitaWrapper(AerolineaLanchita aerolineaLanchita, Parser parser ){
		this.lanchitaParser= parser;
		this.aerolineaLanchita = aerolineaLanchita;
	}

	/**
	 * Dado un vuelo (origen, destino, fecha), hace una query a AerolineaLanchita para obtener los asientos disponibles
	 * (un array de arrays de String) y los parsea para obtener una lista de Asientos. 
	 */
	@Override
	public List<Vuelo> buscarVuelos(Busqueda busqueda) {

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

}
