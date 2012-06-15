package edu.utn.dds.aterrizar.usuario;



import java.util.ArrayList;
import java.util.List;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

public class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	private TipoDeSuscripcion tipoDeSuscripcion;
	private List<ConsultaAsientos> consultasRealizadas;
	private List<Asiento> comprasEfectuadas;
	
	public Usuario(String nombre, String apellido, String dni, TipoDeSuscripcion tipoDeSuscripcion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.tipoDeSuscripcion = tipoDeSuscripcion;
		
		consultasRealizadas = new ArrayList<ConsultaAsientos>();
	}
	
	public void registrarConsulta(ConsultaAsientos consulta) {
		this.consultasRealizadas.add(consulta);
	}
	
	public String getDni() {
		return this.dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
	
	public Double getRecargo() {
		return this.getTipo().getRecargo();
	}
	
	public FiltroAsiento getFiltro() {
		return this.getTipo().getFiltro();
	}
	public void setTipo(TipoDeSuscripcion tipo) {
		this.tipoDeSuscripcion = tipo;
	}
	public TipoDeSuscripcion getTipo() {
		return tipoDeSuscripcion;
	}


	public double getTotalCompras() {
		double total= 0;
		for (Asiento asiento: this.comprasEfectuadas){
			total += asiento.getPrecio();
		}
		return total;
	}

	public void registrarCompra(Asiento asiento) {
			this.comprasEfectuadas.add(asiento.adaptarNuevoAsientoConPrecioPara(this));
	}

}
