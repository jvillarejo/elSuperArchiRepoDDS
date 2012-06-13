package edu.utn.dds.aterrizar.usuario;



import java.util.ArrayList;
import java.util.List;

import edu.utn.dds.aterrizar.vuelo.filtros.FiltroAsiento;

public class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	private TipoDeSuscripcion tipoDeSuscripcion;
	private List<ConsultaAsientos> consultasRealizadas;
	
	//TODO Agregar tipo de usuario VIP, Estandar, los que no pagan
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

}
