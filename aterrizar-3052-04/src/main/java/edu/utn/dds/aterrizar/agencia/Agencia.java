package edu.utn.dds.aterrizar.agencia;

import java.util.ArrayList;
import java.util.List;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.escalas.AsientoIterator;
import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.escalas.VueloConEscala;
import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.usuario.ConsultaAsientos;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.filtros.BuscadorDeAsientos;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

public class Agencia {

	private List<Aerolinea> aerolineas;

	public Agencia(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
	
	public List<Vuelo> buscarVuelos(final Busqueda busqueda, final Usuario usuario, final FiltroAsiento... filtro) {
		List<Vuelo> vuelos = new ArrayList<Vuelo>();		
		for (Aerolinea aerolinea : aerolineas) 
			vuelos.addAll(aerolinea.buscarVuelos(busqueda));

		vuelos = adaptarPreciosParaUsuario(vuelos, usuario);
		
		usuario.registrarConsulta(new ConsultaAsientos(busqueda, filtro));
		//FIXME refactor de los filtros.
		return filtro.filtrar(vuelos);	
	}
	
	//FIXME lo estoy dejando aparte pero esto debería ser lo que ahora es buscar asientos
	public List<Vuelo> getVuelosDisponiblesDirectosYConEscalas(String origen, String destino, String fecha, Aerolinea aerolinea){
		//buscamos los vuelos directos, como siempre
		List<Vuelo>vuelos= new ArrayList<Vuelo>();
		List<Vuelo>todosLosVuelos= aerolinea.buscarVuelos(new Busqueda(origen, destino, fecha));
		//y agregamos los vuelos con escala
	   vuelos.addAll(this.buscarVuelosConEscala(todosLosVuelos));
		return vuelos;
	}
	public List<Vuelo> buscarVuelosConEscala(List<Vuelo> todosLosVuelos){
		List<Vuelo> vuelosConEscala= new ArrayList<Vuelo>();
		for (Vuelo vuelo:todosLosVuelos){
			Vuelo next = todosLosVuelos.listIterator().next();
			//esto es un filter, pero bleh, necesito recorrerlo a mano ¬¬
			if (this.esEscala(vuelo, next)){
				VueloConEscala nuevo = new VueloConEscala(vuelo, next);
				vuelosConEscala.add(nuevo);
			}
		}
		return vuelosConEscala;
	}

	private boolean esEscala(Vuelo unVuelo, Vuelo otroVuelo){
		return unVuelo.getDestino().equals(otroVuelo.getOrigen()) && otroVuelo.getFechaSalida().esAnteriorA(unVuelo.getFechaLlegada());
	}
	

	public void comprarAsiento(final Asiento asiento, final Usuario usuario) {
		asiento.comprar(usuario); 
		usuario.registrarCompra(asiento);
	}		

	private List<Vuelo> adaptarPreciosParaUsuario(List<Vuelo> vuelos,
			Usuario usuario) {
		List<Vuelo> vuelosConAsientosAdaptados= new ArrayList<Vuelo>(); 
for(Vuelo vuelo: vuelos){
		for (Asiento asiento : vuelo.getAsientos()){
			VueloDirecto nuevoVuelo = new VueloDirecto();
			//TODO fuck, hay que pensar que pasa en el composite, creo que querría agregar asientos solo en vuelos directos.
			vuelo.agregarAsiento(asiento.adaptarNuevoAsientoConPrecioPara(usuario));
			vuelosConAsientosAdaptados.add(nuevoVuelo);
		}
	}
return vuelos;
	}
}
