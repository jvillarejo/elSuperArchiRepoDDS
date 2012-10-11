package edu.utn.dds.aterrizar.usuario;



import java.util.ArrayList;
import java.util.List;

import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;
import edu.utn.dds.aterrizar.vuelo.filtros.Filtro;

public class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	private TipoDeSuscripcion tipoDeSuscripcion;
	private List<Busqueda> consultasRealizadas;
	private List<Asiento> comprasEfectuadas = new ArrayList<Asiento>();
	private List<Asiento> reservas = new ArrayList<Asiento>();
	
	public Usuario(String nombre, String apellido, String dni, TipoDeSuscripcion tipoDeSuscripcion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.tipoDeSuscripcion = tipoDeSuscripcion;
		
		this.consultasRealizadas = new ArrayList<Busqueda>();
	}
	
	public void reservar(Asiento asiento) {
		this.tipoDeSuscripcion.reservar(asiento, this);
	}
	
	public void sobreReservar(Asiento asiento) {
		this.tipoDeSuscripcion.sobreReservar(asiento, this);
	}
	
	public void registrarConsulta(Busqueda consulta) {
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
	
	public Filtro<Asiento> getFiltro() {
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
			this.comprasEfectuadas.add(asiento/*.adaptarNuevoAsientoConPrecioPara(this)*/);
	}
	
	public void addReserva(Asiento asiento) {
		this.reservas.add(asiento);
	}
	
	public String getNombreCompleto() {
		return this.nombre + " " + this.apellido;
	}
	
	public List<Asiento> getComprasEfectuadas() {
		return new ArrayList<Asiento>(this.comprasEfectuadas);
	}
	
	public List<Asiento> getReservas() {
		return new ArrayList<Asiento>(this.reservas);
	}
	
}
