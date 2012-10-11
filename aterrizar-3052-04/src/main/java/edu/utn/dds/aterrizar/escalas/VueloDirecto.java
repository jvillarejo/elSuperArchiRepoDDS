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
	this.setFechaSalida(fechaSalida);
	this.setFechaLlegada(fechaLlegada);
	this.aerolinea= aerolinea;
	}
	
	public VueloDirecto() {
	}

	private DateTime setFecha(String fecha) {
    return (fecha != null) ?  new DateTime(SimpleDateParser.LatinAmerican(), fecha): null;
	}
	
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida= this.setFecha(fechaSalida);
	   }
	
	public void setFechaLlegada(String fechaLlegada) {
	    this.setFecha(fechaLlegada);
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
		Query<Asiento> buscador = new Query<Asiento>(this.getAsientos())
			.addFilter(usuario.getFiltro());
		
		for (Filtro<Asiento> filtro : filtros)
			buscador.addFilter(filtro);
		
		this.asientos = buscador.execute(); 
	}
}
