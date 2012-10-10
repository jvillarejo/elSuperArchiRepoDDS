package edu.utn.dds.aterrizar.aerolineas;


import com.lanchita.AerolineaLanchita;

public class AerolineaLanchitaMock extends AerolineaLanchita{
	private static AerolineaLanchitaMock instance;
	private final String asientoQueNoSePuedeComprarNiReservar = "01202022323423-5";
	
	public static synchronized AerolineaLanchitaMock getInstance(){
		if (instance == null) {
			instance = new AerolineaLanchitaMock();
		}
		return instance;
	}
	
	public String[][] getAsientos(){
		return (new String[][] {
				{ "01202022220202-3", "159.90", "P", "V", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012" },
				{ "01202022220123-3", "205.10", "E", "P", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012" },
				{ "01202012315523-8", "154.08", "E", "P", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012" },
				{ "01202022267867-7", "255.98", "E", "P", "D", "", "05:20","14:00","EZE","PER","20/12/2012","20/12/2012" },
				{ "01202022227897-3", "236.10", "P", "C", "D", "", "05:20","14:00","EZE","PER","20/12/2012","20/12/2012" },
				{ "01202022998988-6", "148.23", "P", "V", "D", "", "05:20","14:00","EZE","PER","20/12/2012","20/12/2012" },
				{ "01202022220008-3", "383.22", "T", "V", "D", "", "20:00","08:00","PER","USA","20/12/2012","21/12/2012" },
				{ "01202022256565-3", "282.19", "T", "C", "D", "", "20:00","08:00","PER","USA","20/12/2012","21/12/2012" },
				{ asientoQueNoSePuedeComprarNiReservar, "431.28", "T", "C", "D", "", "20:00","08:00","PER","USA","20/12/2012","21/12/2012" },
				{ "01202022220298-2", "528.81", "P", "V", "D", "", "07:00","08:00","AEO","USH","20/12/2012","21/12/2012"} });
	}
	
}
