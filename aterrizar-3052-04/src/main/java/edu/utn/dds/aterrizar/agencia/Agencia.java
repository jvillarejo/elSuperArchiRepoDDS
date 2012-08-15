package edu.utn.dds.aterrizar.agencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.escalas.VueloConEscala;
import edu.utn.dds.aterrizar.escalas.VueloDirecto;
import edu.utn.dds.aterrizar.usuario.ConsultaVuelos;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.filtros.BuscadorDeAsientos;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.Query;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.BuscadorDeVuelos;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

public class Agencia {

	private List<Aerolinea> aerolineas;

	public Agencia(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
	
	public Agencia() {
	super();
	}

	public List<Vuelo> buscarVuelos(final ConsultaVuelos consulta, final Usuario usuario) {
		List<VueloDirecto> vuelosDirectos = new ArrayList<VueloDirecto>();		
		for (Aerolinea aerolinea : aerolineas) {
			vuelosDirectos.addAll( aerolinea.buscarVuelos(consulta.getBusqueda()));
		}
//esto si o si se tiene que hacer ANTES de armar las escalas (no tiene sentido hacerlo después)
		List<Vuelo> vuelos = adaptarPreciosParaUsuario(vuelosDirectos, usuario);
		
		usuario.registrarConsulta(consulta);

		for (Vuelo vuelo : vuelos)
			vuelo.filtrarAsientos(consulta.getFiltros(), usuario);
			
		Query<Vuelo> buscador = new BuscadorDeVuelos(vuelos);
		buscador.ordenarPor(consulta.getCriterioOrdenamiento());
		
		return buscador.buscar();	
	}
	
	
	public List<Vuelo>buscarVuelosConEscala(String origen, String destino, String fechaSalida, Aerolinea aerolinea){
		//buscamos los vuelos directos, como siempre
		List<Vuelo>vuelos= new ArrayList<Vuelo>();
		List<VueloDirecto>todosLosVuelos= aerolinea.buscarVuelos(new Busqueda(origen, "", fechaSalida, "", "",""));
				todosLosVuelos.addAll(aerolinea.buscarVuelos(new Busqueda("",destino,"","","","")));
		//y agregamos los vuelos con escala
	   vuelos.addAll(this.armarVuelosConEscala(todosLosVuelos));
		return vuelos;
	}
	
	public List<Vuelo> armarVuelosConEscala(List<VueloDirecto> todosLosVuelos){
		List<Vuelo> vuelosConEscala= new ArrayList<Vuelo>();
		for (Vuelo vuelo:todosLosVuelos){
			ListIterator<VueloDirecto> listIterator = todosLosVuelos.listIterator();
			while(listIterator.hasNext()){
			Vuelo next = listIterator.next();
			//esto es un filter, pero bleh, necesito recorrerlo a mano ¬¬
			if (this.esEscala(vuelo, next)){
				VueloConEscala nuevo = new VueloConEscala(vuelo, next);
				vuelosConEscala.add(nuevo);
			}
			}
		}
		return vuelosConEscala;
	}

	public boolean esEscala(Vuelo unVuelo, Vuelo otroVuelo){
		return unVuelo.getDestino().equalsIgnoreCase(otroVuelo.getOrigen()) && otroVuelo.getFechaSalida().esAnteriorA(unVuelo.getFechaLlegada());
	}
	

	public void comprarAsiento(final Asiento asiento, final Usuario usuario) {
		asiento.comprar(usuario); 
		usuario.registrarCompra(asiento);
	}		

	private List<Vuelo> adaptarPreciosParaUsuario(List<VueloDirecto> vuelos, Usuario usuario) {
		List<Vuelo> vuelosConAsientosAdaptados = new ArrayList<Vuelo>();
		
		for (VueloDirecto vuelo : vuelos) {
			for (Asiento asiento : vuelo.getAsientos()) {
				VueloDirecto nuevoVuelo = new VueloDirecto(vuelo.getOrigen(),
						vuelo.getDestino(), vuelo.getFechaSalida().toString(),
						vuelo.getFechaLlegada().toString(),
						vuelo.getAerolinea());
				vuelo.agregarAsiento(asiento
						.adaptarNuevoAsientoConPrecioPara(usuario));
				vuelosConAsientosAdaptados.add(nuevoVuelo);
			}
		}
	
		return vuelosConAsientosAdaptados;
	}
}
