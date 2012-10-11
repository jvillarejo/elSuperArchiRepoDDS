package edu.utn.dds.aterrizar.ui.busqueda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import edu.utn.dds.aterrizar.aerolineas.AsientoNoDisponibleException;
import edu.utn.dds.aterrizar.agencia.Agencia;
import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.homes.UsuarioHome;
import edu.utn.dds.aterrizar.ui.appmodels.AsientoModel;
import edu.utn.dds.aterrizar.ui.appmodels.AsientoModelAdapter;
import edu.utn.dds.aterrizar.ui.appmodels.UsuarioModel;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

@Observable
public class BuscadorVuelos implements Serializable {

	/**
	 * @author clari
	 */
	
	private static final long serialVersionUID = 2589233978711139624L;
	private final Agencia agencia = Agencia.getInstance();
	// por ahora hardcodeamos el usuario
	private final UsuarioModel user = UsuarioHome.getInstance().getDefaultUser();
	private String origen;
	private String destino;
	private String fechaSalida;
	private List<AsientoModel> resultados;
	private Asiento asientoSeleccionado;

	// ********************************************************
	// ** Acciones
	// ********************************************************
	public void search() {
		Busqueda busqueda = new Busqueda()
			.setOrigen(this.origen)
			.setDestino(this.destino)
			.setFechaSalida(fechaSalida);
		
		this.resultados = this.getAsientos(busqueda, this.user.getUsuarioOriginal());
	}

	private List<AsientoModel> getAsientos(final Busqueda busqueda, final Usuario usuario) {
		List<Asiento> asientos = new ArrayList<Asiento>();
		List<Vuelo> vuelos = agencia.buscarVuelos(busqueda, usuario);
		for (Vuelo vuelo : vuelos) {
			asientos.addAll(vuelo.getAsientos());
		}
		
		return AsientoModelAdapter.toApplicationModel(asientos);
	}

	public void comprar() {
		try {
			this.agencia.comprarAsiento(this.asientoSeleccionado, this.user.getUsuarioOriginal());
			throw new UserException("El asiento "
					+ this.asientoSeleccionado.getCodigoDeVuelo()
					+ " ha sido comprado exitosamente");
		} catch(AsientoNoDisponibleException e){
			throw new UserException("Ha ocurrido un error en su reserva: "
					+ e.getMessage() + ". Por favor intente nuevamente");
		}
	}

	public void reservar() {
		try {
			this.agencia.reservarAsiento(this.asientoSeleccionado, this.user.getUsuarioOriginal());
			throw new UserException("El asiento "
					+ this.asientoSeleccionado.getCodigoDeVuelo()
					+ " ha sido reservado exitosamente");
		} catch (RuntimeException re) {
			throw new UserException("Ha ocurrido un error en su reserva: "
					+ re.getMessage() + ". Por favor intente nuevamente");
		}
	}
	

	// ********************************************************
	// ** Accessors
	// ********************************************************

	public Asiento getAsientoSeleccionado() {
		return this.asientoSeleccionado;
	}

	public void setAsientoSeleccionado(Asiento asiento) {
		this.asientoSeleccionado = asiento;
	}

	public List<AsientoModel> getResultados() {
		return this.resultados;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(String fecha) {
		this.fechaSalida = fecha;
	}

}
