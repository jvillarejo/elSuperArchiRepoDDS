package edu.utn.dds.aterrizar.aerolineas;

import com.oceanic.AerolineaOceanic;


public class AerolineaOceanicImpostor extends AerolineaOceanic {
	
	private static AerolineaOceanicImpostor INSTANCE;
	
	public static AerolineaOceanicImpostor getInstance() {
		if(INSTANCE == null){
			INSTANCE = new AerolineaOceanicImpostor();
		}
		return INSTANCE;
	}
}