package edu.utn.dds.aterrizar.ui.busqueda;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import edu.utn.dds.aterrizar.agencia.Agencia;
import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.homes.UsuarioHome;
import edu.utn.dds.aterrizar.usuario.ConsultaVuelos;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroDummy;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.OrdenPorTiempoDeVuelo;

//import edu.utn.dds.aterrizar.vuelo.Clase;
//import edu.utn.dds.aterrizar.vuelo.Ubicacion;

@Observable	
public class BuscadorVuelos implements Serializable {
	
	/**
	 * @author clari
	 */
	private static final long serialVersionUID = 2589233978711139624L;
	private final Agencia agencia = Agencia.getInstance();
	//por ahora hardcodeamos el usuario
	private final Usuario user = UsuarioHome.getInstance().getDefaultUser();
	private String origen;
	private String destino;
	private String fechaSalida;
	private List<Asiento> resultados;
	private Asiento asientoSeleccionado;
	
//	public BuscadorVuelos() {
//		this.inventarAsientos();
//	}
//	
//	private void inventarAsientos() {
//		Asiento asiento1 = new Asiento();
//		asiento1.setPrecio(100.0);
//		asiento1.setNumeroDeAsiento(1);
//		asiento1.setUbicacion(Ubicacion.PASILLO);
//		asiento1.setClase(Clase.PRIMERA);
//
//		Asiento asiento2 = new Asiento();
//		asiento2.setPrecio(200.0);
//		asiento2.setNumeroDeAsiento(2);
//		asiento2.setUbicacion(Ubicacion.VENTANILLA);
//		asiento2.setClase(Clase.TURISTA);
//		
//		this.setResultados(Arrays.asList(asiento1, asiento2));
//	}
	
	// ********************************************************
	// ** Acciones
	// ********************************************************
     public void search(){
    	//FIXME: Hay que unificar query, busqueda y consultaVuelos URGENTE
    	 List<Filtro<Asiento>> fs = new ArrayList<Filtro<Asiento>>();
    	 fs.add(new FiltroDummy());
		this.setResultados(this.getAsientos(new ConsultaVuelos(new Busqueda(origen, destino, fechaSalida, null, null, null), fs, new OrdenPorTiempoDeVuelo()), this.user));
     }
     
     private List<Asiento> getAsientos(final ConsultaVuelos consulta, final Usuario usuario){
         List<Asiento> asientos= new ArrayList<Asiento>();
		List<Vuelo> vuelos = (agencia.buscarVuelos(consulta, usuario));
     for (Vuelo vuelo : vuelos) {
		asientos.addAll(vuelo.getAsientos());
	}
     return asientos;
     }
     
     public void comprar(){
    	 this.agencia.comprarAsiento(this.asientoSeleccionado,this.user);
     }
     
     public void reservar(){
    	 this.agencia.reservarAsiento(this.asientoSeleccionado, this.user);
     }
	// ********************************************************
		// ** Accessors
		// ********************************************************
	
	public Asiento getAsientoSeleccionado(){
		return this.asientoSeleccionado;
	}
	
	public void setAsientoSeleccionado(Asiento asiento){
		this.asientoSeleccionado= asiento;
	}
	

	public List<Asiento> getResultados() {
		return this.resultados;
	}

	public void setResultados(List<Asiento> resultados) {
		this.resultados = resultados;
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
