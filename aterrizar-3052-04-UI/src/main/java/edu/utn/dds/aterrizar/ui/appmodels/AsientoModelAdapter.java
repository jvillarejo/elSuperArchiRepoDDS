package edu.utn.dds.aterrizar.ui.appmodels;

import java.util.ArrayList;
import java.util.List;

import edu.utn.dds.aterrizar.vuelo.Asiento;

public class AsientoModelAdapter {
	
	public static List<AsientoModel> toApplicationModel(List<Asiento> asientos) {
		List<AsientoModel> aux = new ArrayList<AsientoModel>(asientos.size());
		for (Asiento asiento : asientos)
			aux.add(new AsientoModel(asiento));
		
		return aux;
	}
	
}
