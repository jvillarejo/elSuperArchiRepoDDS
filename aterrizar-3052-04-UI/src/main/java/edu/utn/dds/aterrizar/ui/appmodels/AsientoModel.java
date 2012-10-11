package edu.utn.dds.aterrizar.ui.appmodels;

import org.uqbar.commons.utils.Observable;

import edu.utn.dds.aterrizar.manejoDeFechas.DateParser;
import edu.utn.dds.aterrizar.manejoDeFechas.SimpleDateParser;
import edu.utn.dds.aterrizar.ui.utils.EnumUtils;
import edu.utn.dds.aterrizar.vuelo.Asiento;

@Observable
public class AsientoModel {

	private Asiento asientoOriginal;
	private DateParser dateParser;

	// *************************************************************************
	// ** Constructor
	// *************************************************************************
	
	public AsientoModel(Asiento asientoOriginal) {
		this.setAsientoOriginal(asientoOriginal);
		this.dateParser = SimpleDateParser.LatinAmerican();
	}
	
	// *************************************************************************
	// ** Computed properties
	// *************************************************************************
	
	public String getNombreAerolinea() {
		return this.getAsientoOriginal().getAerolinea().getName();
	}
	
	public String getCodigoDeVuelo() {
		return this.getAsientoOriginal().getCodigoDeVuelo();
	}
	
	public String getPrecio() {
		return this.getAsientoOriginal().getPrecio().toString();
	}
	
	public String getUbicacion() {
		return EnumUtils.toPascalCase(this.getAsientoOriginal().getUbicacion());
	}

	public String getClase() {
		return EnumUtils.toPascalCase(this.getAsientoOriginal().getClase());
	}
	
	public String getNumeroAsiento() {
		return this.getAsientoOriginal().getNumeroDeAsiento().toString();
	}
	
	public String getFechaSalida() {
		return this.dateParser.toString(this.getAsientoOriginal().getBusqueda().getFechaSalida());
	}
	
	// *************************************************************************
	// ** Accesors
	// *************************************************************************

	private Asiento getAsientoOriginal() {
		return this.asientoOriginal;
	}
	
	public void setAsientoOriginal(Asiento asientoOriginal) {
		this.asientoOriginal = asientoOriginal;
	}
}
