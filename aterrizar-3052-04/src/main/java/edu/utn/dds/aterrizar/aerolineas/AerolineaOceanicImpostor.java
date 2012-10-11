package edu.utn.dds.aterrizar.aerolineas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;


public class AerolineaOceanicImpostor extends AerolineaOceanic {
	
	
	private static AerolineaOceanic INSTANCE;

	private List<AsientoDTO> asientos = new ArrayList<AsientoDTO>();
	private List<Object[]> asientosComprados = new ArrayList<Object[]>();
	private List<Object[]> reservas = new ArrayList<Object[]>();
	private final AsientoDTO asientoQueNoSePuedeComprarNiReservar = new AsientoDTO("OC888",2,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("5150.98"),"Primera","Ventana",false,"_NY","SLA");

	public static AerolineaOceanic getInstance() {
		if(INSTANCE == null){
			INSTANCE = new AerolineaOceanicImpostor();
		}
		
		return INSTANCE;
		
	}

	protected AerolineaOceanicImpostor() {
		asientos.add(new AsientoDTO("OC100",10,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Pasillo",false,"_BS","SLA"));
		asientos.add(new AsientoDTO("OC100",11,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Centro",false,"_BS","SLA"));		
		asientos.add(new AsientoDTO("OC100",12,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Ventana",false,"_BS","SLA"));		
		asientos.add(new AsientoDTO("OC100",30,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("6150.98"),"Primera","Pasillo",true,"_BS","SLA"));		
		asientos.add(new AsientoDTO("OC202",20,"13/09/2012","13/09/2012","06:40","16:18",new BigDecimal("6150.98"),"Primera","Centro",false,"SLA","_BS"));		
		asientos.add(new AsientoDTO("OC202",21,"13/09/2012","13/09/2012","06:40","16:18",new BigDecimal("4550.98"),"Ejecutiva","Pasillo",false,"SLA","_BS"));		
		asientos.add(new AsientoDTO("OC202",26,"13/09/2012","13/09/2012","06:40","16:18",new BigDecimal("4450.98"),"Ejecutiva","Ventana",true,"SLA","_BS"));		
		asientos.add(new AsientoDTO("OC202",27,"13/09/2012","13/09/2012","06:40","16:18",new BigDecimal("4450.98"),"Ejecutiva","Pasillo",false,"SLA","_BS"));		
		asientos.add(new AsientoDTO("OC888",1,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("5150.98"),"Primera","Pasillo",false,"_NY","SLA"));		
		asientos.add(asientoQueNoSePuedeComprarNiReservar);		
		asientos.add(new AsientoDTO("OC888",17,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("3750.98"),"Ejecutiva","Pasillo",false,"_NY","SLA"));		
		asientos.add(new AsientoDTO("OC888",18,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("3750.98"),"Ejecutiva","Centro",false,"_NY","SLA"));		
		asientos.add(new AsientoDTO("OC888",19,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("3750.98"),"Ejecutiva","Ventana",true,"_NY","SLA"));		
		asientos.add(new AsientoDTO("OC042",19,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4100.98"),"Turista","Ventana",true, "SLA","_NY"));		
		asientos.add(new AsientoDTO("OC042",20,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4100.98"),"Turista","Pasillo",false, "SLA","_NY"));		
		asientos.add(new AsientoDTO("OC042",11,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4500.98"),"Ejecutiva","Ventana",false, "SLA","_NY"));		
		asientos.add(new AsientoDTO("OC042",12,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4500.98"),"Ejecutiva","Centro",true,"SLA","_NY"));		
		asientos.add(new AsientoDTO("OC042",13,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4500.98"),"Ejecutiva","Pasillo",false,"SLA","_NY"));		
	}

	
	public List<AsientoDTO> asientosDisponiblesParaOrigen(String origen, String fechaDeSalida) {
		List<String> vuelos = this.generarVuelosParaElOrigen(origen);
		
		List<AsientoDTO> disponibles = new ArrayList<AsientoDTO>();
		for (AsientoDTO asiento : this.getAsientosDisponibles()) {
			if(fechaDeSalida != null){
				if(vuelos.contains(asiento.getCodigoDeVuelo()) && asiento.getFechaDeSalida().equals(fechaDeSalida)) {
					disponibles.add(asiento);
				}
			}else{
				if(vuelos.contains(asiento.getCodigoDeVuelo())) {
					disponibles.add(asiento);
				}
			}
		}
		return disponibles;
	}
	
	
	public List<AsientoDTO> asientosDisponiblesParaOrigenYDestino(String origen, String destino, String fechaDeSalida) {
		String vuelo = generarVueloParaOrigenYDestino(origen, destino);
		List<AsientoDTO> disponibles = new ArrayList<AsientoDTO>();
		
		for (AsientoDTO asiento : this.getAsientosDisponibles()) {
			if(fechaDeSalida != null){
				if(vuelo.equals(asiento.getCodigoDeVuelo()) && asiento.getFechaDeSalida().equals(fechaDeSalida)) {
					disponibles.add(asiento);
				}
			}else{
				if(vuelo.equals(asiento.getCodigoDeVuelo())) {
					disponibles.add(asiento);
				}
			}
		}
		return disponibles;
	}

	public Boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo,Integer numeroDeAsiento) {
		AsientoDTO asientoAComprar = buscarAsientoDisponible(codigoVuelo,numeroDeAsiento);
		if(asientoAComprar == null || asientoAComprar.equals(asientoQueNoSePuedeComprarNiReservar) ||  noPuedeComprarAsiento(dni, asientoAComprar)){
			return false;
		}
		this.comprar(dni, asientoAComprar);
		return true;
	}

	public Boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento) {
		for(Object[] unaReserva : this.getReservas()){
			if(unaReserva[0].equals(codigoDeVuelo) && unaReserva[1].equals(numeroDeAsiento)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean estaComprado(String codigoDeVuelo, Integer numeroDeAsiento) {
		for(Object[] unaCompra : this.getCompras()){
			AsientoDTO comprado = (AsientoDTO) unaCompra[0];
			if(comprado.getCodigoDeVuelo().equals(codigoDeVuelo) && comprado.getNumeroDeAsiento().equals(numeroDeAsiento)) {
				return true;
			}
		}
		return false;
	}

	public Boolean reservar(String dni, String codigoDeVuelo, Integer numeroDeAsiento) {
		if( asientoQueNoSePuedeComprarNiReservar.getCodigoDeVuelo().equals(codigoDeVuelo) &&
				asientoQueNoSePuedeComprarNiReservar.getNumeroDeAsiento().equals(numeroDeAsiento)){
			return false;
		}

		Object[] nuevaReserva = {codigoDeVuelo, numeroDeAsiento, dni};
		if(this.existeAsiento(codigoDeVuelo, numeroDeAsiento) && !this.estaReservado(codigoDeVuelo, numeroDeAsiento)) {
			this.getReservas().add(nuevaReserva);
			return true;
		}
		return false;
	}


	
	protected Boolean existeAsiento(String vuelo, Integer numeroDeAsiento) {
		for(AsientoDTO asiento : this.getAsientos()){
			if(asiento.getCodigoDeVuelo().equals(vuelo) &&
					asiento.getNumeroDeAsiento().equals(numeroDeAsiento)){
				return true;
			}
		}
		return false;
	}
	
	public List<AsientoDTO> getAsientos(){
		return this.asientos;
	}
	

	protected List<AsientoDTO> getAsientosDisponibles() {
		List<AsientoDTO> disponibles = new ArrayList<AsientoDTO>();
		for(AsientoDTO asiento : this.asientos){
			if(! this.estaReservadoOComprado(asiento.getCodigoDeVuelo(),asiento.getNumeroDeAsiento())){
				disponibles.add(asiento);
			}
		}
		
		return disponibles;
	}
	
	protected List<AsientoDTO> getAsientosNoComprados() {
		List<AsientoDTO> disponibles = new ArrayList<AsientoDTO>();
		for(AsientoDTO asiento : this.asientos){
			if(! this.estaComprado(asiento.getCodigoDeVuelo(), asiento.getNumeroDeAsiento())){
				disponibles.add(asiento);
			}
		}
		
		return disponibles;
	}
	
	protected Boolean estaReservadoOComprado(String codigoDeVuelo,
			Integer numeroDeAsiento) {
		return this.estaReservado(codigoDeVuelo, numeroDeAsiento) || this.estaComprado(codigoDeVuelo, numeroDeAsiento);
	}

	protected void setAsientosDisponibles(List<AsientoDTO> asientos) {
		this.asientos=asientos;
	}
	
	protected List<Object[]> getReservas() {
		return this.reservas;
	}

	protected List<Object[]> getCompras() {
		return this.asientosComprados;
	}
	
	protected List<String> generarVuelosParaElOrigen(String origen) {
		List<String> vuelos = new ArrayList<String>();
		if(origen.equals("SLA")) {vuelos.add("OC042");vuelos.add("OC202");}
		if(origen.equals("_NY")) {vuelos.add("OC888");}
		if(origen.equals("_BS")) {vuelos.add("OC100");}
		return vuelos;
	}
	
	protected String generarVueloParaOrigenYDestino(String origen, String destino) {
		String vuelo = "";
		if(origen.equals("SLA") && destino.equals("_NY")) {vuelo = "OC042";}
		if(origen.equals("_NY") && destino.equals("SLA")) {vuelo = "OC888";}
		if(origen.equals("_BS") && destino.equals("SLA")) {vuelo = "OC100";}
		if(origen.equals("SLA") && destino.equals("_BS")) {vuelo = "OC202";}
		return vuelo;
	}
	
	protected AsientoDTO buscarAsientoDisponible(String codigoVuelo, Integer numeroDeAsiento) {
		for (AsientoDTO asiento : this.getAsientosNoComprados()) {
			if(asiento.getCodigoDeVuelo().equals(codigoVuelo) && asiento.getNumeroDeAsiento().equals(numeroDeAsiento)) {
				return asiento;
			}
		}
		return null;
	}

	private void comprar(String dni, AsientoDTO asientoAComprar) {
		Object[] nuevaCompra = {asientoAComprar, dni};
		this.getCompras().add(nuevaCompra);
	}

	protected boolean noPuedeComprarAsiento(String dni, AsientoDTO asientoAComprar) {
		return this.estaReservado(asientoAComprar.getCodigoDeVuelo(),asientoAComprar.getNumeroDeAsiento()) && 
				!this.esReservaValida(asientoAComprar,dni);
	}
	
	protected boolean esReservaValida(AsientoDTO asientoAComprar, String dni) {
		for(Object[] unaReserva: this.getReservas()){
			if(unaReserva[0].equals(asientoAComprar.getCodigoDeVuelo()) && 
				unaReserva[1].equals(asientoAComprar.getNumeroDeAsiento())) {
				return unaReserva[2].equals(dni);
			}
		}
		return false;
	}
}