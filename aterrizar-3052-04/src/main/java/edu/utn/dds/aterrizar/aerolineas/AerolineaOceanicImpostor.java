package edu.utn.dds.aterrizar.aerolineas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.oceanic.AsientoDTO;


public class AerolineaOceanicImpostor {

	private static AerolineaOceanicImpostor INSTANCE;

	private List<AsientoDTO> asientosDisponibles = new ArrayList<AsientoDTO>();
	private List<Object[]> asientosComprados;
	private List<Object[]> reservas;

	public static AerolineaOceanicImpostor getInstance() {
		if(INSTANCE == null){
			INSTANCE = new AerolineaOceanicImpostor();
		}
		
		return INSTANCE;
		
	}

	protected AerolineaOceanicImpostor() {
		asientosComprados = new ArrayList<Object[]>();
		reservas = new ArrayList<Object[]>();
		asientosDisponibles.add(new AsientoDTO("OC100",10,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Pasillo",false,"_BS","SLA"));
		asientosDisponibles.add(new AsientoDTO("OC100",11,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Centro",false,"_BS","SLA"));		
		asientosDisponibles.add(new AsientoDTO("OC100",12,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Ventana",false,"_BS","SLA"));		
		asientosDisponibles.add(new AsientoDTO("OC100",30,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("6150.98"),"Primera","Pasillo",true,"_BS","SLA"));		
		asientosDisponibles.add(new AsientoDTO("OC202",20,"13/09/2012","13/09/2012","06:40","16:18",new BigDecimal("6150.98"),"Primera","Centro",false,"SLA","_BS"));		
		asientosDisponibles.add(new AsientoDTO("OC202",21,"13/09/2012","13/09/2012","06:40","16:18",new BigDecimal("4550.98"),"Ejecutiva","Pasillo",false,"SLA","_BS"));		
		asientosDisponibles.add(new AsientoDTO("OC202",26,"13/09/2012","13/09/2012","06:40","16:18",new BigDecimal("4450.98"),"Ejecutiva","Ventana",true,"SLA","_BS"));		
		asientosDisponibles.add(new AsientoDTO("OC202",27,"13/09/2012","13/09/2012","06:40","16:18",new BigDecimal("4450.98"),"Ejecutiva","Pasillo",false,"SLA","_BS"));		
		asientosDisponibles.add(new AsientoDTO("OC888",1,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("5150.98"),"Primera","Pasillo",false,"_NY","SLA"));		
		asientosDisponibles.add(new AsientoDTO("OC888",2,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("5150.98"),"Primera","Ventana",false,"_NY","SLA"));		
		asientosDisponibles.add(new AsientoDTO("OC888",17,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("3750.98"),"Ejecutiva","Pasillo",false,"_NY","SLA"));		
		asientosDisponibles.add(new AsientoDTO("OC888",18,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("3750.98"),"Ejecutiva","Centro",false,"_NY","SLA"));		
		asientosDisponibles.add(new AsientoDTO("OC888",19,"30/11/2012","30/11/2012","13:05","17:00",new BigDecimal("3750.98"),"Ejecutiva","Ventana",true,"_NY","SLA"));		
		asientosDisponibles.add(new AsientoDTO("OC042",19,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4100.98"),"Turista","Ventana",true, "SLA","_NY"));		
		asientosDisponibles.add(new AsientoDTO("OC042",20,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4100.98"),"Turista","Pasillo",false, "SLA","_NY"));		
		asientosDisponibles.add(new AsientoDTO("OC042",11,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4500.98"),"Ejecutiva","Ventana",false, "SLA","_NY"));		
		asientosDisponibles.add(new AsientoDTO("OC042",12,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4500.98"),"Ejecutiva","Centro",true,"SLA","_NY"));		
		asientosDisponibles.add(new AsientoDTO("OC042",13,"23/12/2012","24/12/2012","23:50","05:30",new BigDecimal("4500.98"),"Ejecutiva","Pasillo",false,"SLA","_NY"));		
	}

	
	public List<AsientoDTO> asientosDisponiblesParaOrigen(String origen, String fechaDeSalida) {
		List<String> vuelos = this.generarVuelosParaElOrigen(origen);
		
		List<AsientoDTO> disponibles = new ArrayList<AsientoDTO>();
		for (AsientoDTO asiento : this.getAsientosDisponibles()) {
			if(vuelos.contains(asiento.getCodigoDeVuelo()) && asiento.getFechaDeSalida().equals(fechaDeSalida)) {
				disponibles.add(asiento);
			}
		}
		return disponibles;
	}
	
	
	public List<AsientoDTO> asientosDisponiblesParaOrigenYDestino(String origen, String destino, String fechaDeSalida) {
		String vuelo = generarVueloParaOrigenYDestino(origen, destino);
		
		List<AsientoDTO> disponibles = new ArrayList<AsientoDTO>();
		for (AsientoDTO asiento : this.getAsientosDisponibles()) {
			if(vuelo.equals(asiento.getCodigoDeVuelo()) && asiento.getFechaDeSalida().equals(fechaDeSalida)) {
				disponibles.add(asiento);
			}
		}
		return disponibles;
	}

	public Boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo,Integer numeroDeAsiento) {
		AsientoDTO asientoAComprar = buscarAsientoDisponiblo(codigoVuelo,numeroDeAsiento);
		if(asientoAComprar == null || noPuedeComprarAsiento(dni, asientoAComprar)){
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

	protected boolean esReservaValida(AsientoDTO asientoAComprar, String dni) {
		for(Object[] unaReserva: this.getReservas()){
			if(unaReserva[0].equals(asientoAComprar.getCodigoDeVuelo()) && 
				unaReserva[1].equals(asientoAComprar.getNumeroDeAsiento())) {
				return unaReserva[2].equals(dni);
			}
		}
		return false;
	}


	public Boolean reservar(String dni, String codigoDeVuelo, Integer numeroDeAsiento) {
		Object[] nuevaReserva = {codigoDeVuelo, numeroDeAsiento, dni};
		if(!this.estaReservado(codigoDeVuelo, numeroDeAsiento)) {
			this.getReservas().add(nuevaReserva);
			return true;
		}
		return false;
	}
	
	public void eliminarReserva(String codigo, String numeroAsiento){
		Integer i = 0;
		Boolean encontrado = false;
		while(i < this.getReservas().size() && !encontrado){
			if(this.getReservas().get(0).equals(codigo) && this.getReservas().get(1).equals(numeroAsiento))
				encontrado = true;
			i++;
		}
		this.getReservas().remove(i - 1);
	}


	
	protected List<AsientoDTO> getAsientosDisponibles() {
		return this.asientosDisponibles;
	}
	
	protected void setAsientosDisponibles(List<AsientoDTO> asientos) {
		this.asientosDisponibles=asientos;
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
		String vuelo = null;
		if(origen=="SLA" && destino =="_NY") {vuelo = "OC042";}
		if(origen=="_NY" && destino == "SLA") {vuelo = "OC888";}
		if(origen=="_BS" && destino == "SLA") {vuelo = "OC100";}
		if(origen == "SLA" && destino == "_BS") {vuelo = "OC202";}
		return vuelo;
	}
	
	protected AsientoDTO buscarAsientoDisponiblo(String codigoVuelo, Integer numeroDeAsiento) {
		for (AsientoDTO asiento : this.getAsientosDisponibles()) {
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
}