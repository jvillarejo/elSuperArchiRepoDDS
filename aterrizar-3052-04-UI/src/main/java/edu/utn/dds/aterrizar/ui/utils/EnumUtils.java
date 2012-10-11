package edu.utn.dds.aterrizar.ui.utils;

public class EnumUtils {

	public static String toPascalCase(Enum<?> value) {
		String stringValue = value.name();
		return stringValue.substring(0, 1).toUpperCase() + stringValue.substring(1).toLowerCase(); 
	}

}
