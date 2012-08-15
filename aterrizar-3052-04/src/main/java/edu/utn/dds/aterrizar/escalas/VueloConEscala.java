package edu.utn.dds.aterrizar.escalas;

import java.util.List;
import java.util.ListIterator;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;

/**
 * Representa los vuelos con escala, que serían dos vuelos, y debería saber el
 * tiempo de conexión
 * 
 * @author clari
 * 
 */
public class VueloConEscala extends Vuelo {
	private List<Vuelo> vuelos;

	public VueloConEscala(Vuelo vuelo, Vuelo next) {
		super(); 
		this.vuelos.add(vuelo);
		this.vuelos.add(next);
	}
@Override
	public long getDuration() {
		long total = 0;
		for (Vuelo vuelo : vuelos) {
			ListIterator<Vuelo> listIterator = vuelos.listIterator();
			while(listIterator.hasNext()){
			Vuelo next = listIterator.next();
			total += vuelo.getDuration() + this.tiempoDeConexion(next);
		}
		}
		return total;
	}

	private long tiempoDeConexion(Vuelo next) {
		return this.fechaLlegada.diasDeDiferenciaCon(next.getFechaSalida());

	}

	@Override
	public void filtrarAsientos(List<Filtro<Asiento>> filtros, Usuario usuario) {
		for (Vuelo vuelo : this.vuelos)
			vuelo.filtrarAsientos(filtros, usuario);
	}
	@Override
	public Double getPrecioMasBarato() {
		Double total= 0.0;
		for(Vuelo vuelo: this.vuelos){
			total+= vuelo.getPrecioMasBarato();
		}
		return total;
	}
}
