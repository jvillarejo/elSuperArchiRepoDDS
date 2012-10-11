package edu.utn.dds.aterrizar.agencia;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaMock;
import edu.utn.dds.aterrizar.aerolineas.AerolineaLanchitaWrapper;
import edu.utn.dds.aterrizar.escalas.Vuelo;
import edu.utn.dds.aterrizar.escalas.VueloConEscala;
import edu.utn.dds.aterrizar.escalas.VueloDirecto;
//import edu.utn.dds.aterrizar.manejoDeFechas.DateParser;
import edu.utn.dds.aterrizar.parser.Parser;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

public class Agencia {

	public static Agencia instance;

	private List<Aerolinea> aerolineas;

	// ****************************************************************
	// ** CONSTRUCTORES
	// ****************************************************************

	public Agencia(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}

	public Agencia() {
		super();
	}

	public static synchronized Agencia getInstance() {
		if (instance == null) {
			List<Aerolinea> aeros = new ArrayList<Aerolinea>();
			// aeros.add(new
			// AerolineaOceanicWrapper(AerolineaOceanicImpostor.getInstance(), new
			// AerolineaOceanicParser()));
			aeros.add(new AerolineaLanchitaWrapper(AerolineaLanchitaMock
					.getInstance(), new Parser()));
			instance = new Agencia(aeros);
		}
		return instance;
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public List<Vuelo> buscarVuelos(final Busqueda busqueda, final Usuario usuario) {
	
		List<Vuelo> vuelosDirectos = new ArrayList<Vuelo>();
		for (Aerolinea aerolinea : aerolineas) {
			vuelosDirectos.addAll(aerolinea.buscarVuelos(busqueda));
		}

		//List<Vuelo> vuelos = adaptarPreciosParaUsuario(vuelosDirectos, usuario);

		usuario.registrarConsulta(busqueda);

//		for (Vuelo vuelo : vuelosDirectos)
//			vuelo.filtrarAsientos(busqueda.getFiltros(), usuario);
//
//		Query<Vuelo> buscador = new Query<Vuelo>(vuelosDirectos);
//		buscador.addOrderByCriteria(busqueda.getCriterioOrdenamiento());
//
//		return buscador.execute();
		return vuelosDirectos;
	}

	public List<Vuelo> buscarVuelosConEscala(String origen, String destino,
			String fechaSalida, Aerolinea aerolinea) {
		// buscamos los vuelos directos, como siempre
		List<Vuelo> vuelos = new ArrayList<Vuelo>();
		List<VueloDirecto> todosLosVuelos = aerolinea
				.buscarVuelos(new Busqueda(origen, destino, fechaSalida, null,
						null, null));
		todosLosVuelos.addAll(aerolinea.buscarVuelos(new Busqueda(null,
				destino, null, null, null, null)));
		// y agregamos los vuelos con escala
		vuelos.addAll(this.armarVuelosConEscala(todosLosVuelos));
		return vuelos;
	}

	public List<VueloConEscala> armarVuelosConEscala(
			List<VueloDirecto> todosLosVuelos) {
		ListIterator<VueloDirecto> listIterator = todosLosVuelos.listIterator();
		List<VueloConEscala> vuelosConEscala = new ArrayList<VueloConEscala>();
		while (listIterator.hasNext()) {
			Vuelo vuelo = listIterator.next();
			Vuelo next = listIterator.next();
			if (this.esEscala(vuelo, next)) {
				VueloConEscala nuevo = new VueloConEscala(vuelo, next);
				// eventualmente tendría que pasar esto en el constructor
				nuevo.setAerolinea(vuelo.getAerolinea());
				nuevo.setOrigen(vuelo.getOrigen());
				nuevo.setDestino(next.getDestino());
				vuelosConEscala.add(nuevo);
			}
			vuelo = next;
		}

		return vuelosConEscala;
	}

	public boolean esEscala(Vuelo unVuelo, Vuelo otroVuelo) {
		return (unVuelo.getDestino().equals(otroVuelo.getOrigen())
				&& unVuelo.getFechaLlegada().esAnteriorA(
						otroVuelo.getFechaSalida()) && unVuelo.getAerolinea()
				.equals(otroVuelo.getAerolinea()));
	}

	public void comprarAsiento(final Asiento asiento, final Usuario usuario) {
		asiento.comprar(usuario);
		usuario.registrarCompra(asiento);
	}

	public void reservarAsiento(final Asiento asiento, final Usuario usuario) {
		asiento.reservar(usuario);
	}

//	private List<Vuelo> adaptarPreciosParaUsuario(List<VueloDirecto> vuelos,
//			Usuario usuario) {
//		List<Vuelo> vuelosConAsientosAdaptados = new ArrayList<Vuelo>();
//
//		for (VueloDirecto vuelo : vuelos) {
//			for (Asiento asiento : vuelo.getAsientos()) {
//				DateParser formatter= vuelo.getAerolinea().getFormatter();
//				VueloDirecto nuevoVuelo = new VueloDirecto(vuelo.getOrigen(),
//						vuelo.getDestino(), vuelo.getFechaSalida().toString(formatter),
//						vuelo.getFechaLlegada().toString(formatter),
//						asiento.getAerolinea());
//				vuelo.agregarAsiento(asiento
//					.adaptarNuevoAsientoConPrecioPara(usuario)
//						);
//				vuelosConAsientosAdaptados.add(nuevoVuelo);
//			}
//		}
//
//		return vuelosConAsientosAdaptados;
//	}

}
