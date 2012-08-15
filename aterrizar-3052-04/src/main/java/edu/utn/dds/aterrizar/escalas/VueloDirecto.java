package edu.utn.dds.aterrizar.escalas;

import java.util.List;

import static net.sf.staccatocommons.lambda.Lambda.*;
import net.sf.staccatocommons.collections.stream.Streams;

import edu.utn.dds.aterrizar.aerolineas.Aerolinea;
import edu.utn.dds.aterrizar.manejoDeFechas.DateTime;
import edu.utn.dds.aterrizar.manejoDeFechas.SimpleDateParser;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;
import edu.utn.dds.aterrizar.vuelo.ordenamiento.Query;

public class VueloDirecto extends Vuelo {
 public  VueloDirecto(String origen, String destino, String fechaSalida,
		String fechaLlegada, Aerolinea aerolinea) {
	this.origen= origen;
	this.destino= destino;
	this.fechaSalida= new DateTime(SimpleDateParser.LatinAmerican(), fechaSalida);
	this.fechaLlegada = new DateTime(SimpleDateParser.LatinAmerican(), fechaLlegada);
	this.aerolinea= aerolinea;
	}
	
	public VueloDirecto() {
	}

	@Override
	public long getDuration(){
		return this.fechaSalida.diasDeDiferenciaCon(this.fechaLlegada);
	
	}
	
	public void agregarAsiento(Asiento asiento){
		this.asientos.add(asiento);
	}


	public Asiento getPrimerAsiento(){
		return this.asientos.get(0);
	}


@Override
	public Double getPrecioMasBarato() {
		return Streams
			.from(this.getAsientos())
			.minimumOn(lambda($(Asiento.class).getPrecio()))
			.getPrecio();
	}

	@Override
	public void filtrarAsientos(List<Filtro<Asiento>> filtros, Usuario usuario) {
		Query<Asiento> buscador = new Query<Asiento>(this.getAsientos());
		buscador.filter(usuario.getFiltro());
		buscador.agregarFiltros(filtros);
		
		this.asientos = buscador.execute(); 
	}
}
