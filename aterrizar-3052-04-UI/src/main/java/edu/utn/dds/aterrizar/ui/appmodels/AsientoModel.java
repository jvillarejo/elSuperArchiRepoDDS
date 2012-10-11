package edu.utn.dds.aterrizar.ui.appmodels;

import edu.utn.dds.aterrizar.ui.transformers.EnumUtils;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class AsientoModel {

	private Asiento asientoOriginal;

	// *************************************************************************
	// ** Constructor
	// *************************************************************************
	
	public AsientoModel(Asiento asientoOriginal) {
		this.setAsientoOriginal(asientoOriginal);
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
