package edu.utn.dds.aterrizar.usuario;

import edu.utn.dds.aterrizar.vuelo.Asiento;

import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroSuperOfertas;

//TODO Refactorizar -> Solo deberia haber una instancia de cada una de las suscripciones, Usar enum o hacer protected el constructor e instanciarlos acá.
public abstract class TipoDeSuscripcion {
	
	final Double RECARGO_DEFAULT = 0.0;
	
	public Filtro<Asiento> getFiltro() {
		return new FiltroSuperOfertas();
	}
	
	public Double getRecargo() {
		return RECARGO_DEFAULT;
	}
	
	public void reservar(Asiento asiento, Usuario usuario){
		throw new UsuarioNoDisponibleException("Este tipo de usuario no puede reservar");
	}
	
}
