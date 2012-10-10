package edu.utn.dds.aterrizar.manejoDeFechas;

import java.util.*;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import edu.utn.dds.aterrizar.manejoDeFechas.*;
import edu.utn.dds.aterrizar.manejoDeFechas.exceptions.*;

@SuppressWarnings("unused")
public class DateTimeConverterTests {
	private Date fechaInicial;
	private Date fechaFinal;
	
	@Before
	public void setup() {
		this.fechaInicial = new GregorianCalendar(1991, 10 - 1, 30).getTime();
		this.fechaFinal = new GregorianCalendar(1991, 11 - 1, 3).getTime();
	}
		
	@Test
	public void instanciarDateTimeConValoresNumericos(){
		DateTime fecha = new DateTime(30, 10, 1991);
		Assert.assertEquals(fechaInicial, fecha.getDate());
	}
	
	@Test
	public void parsearFechaEnFormatoISO8601()  {
		DateTime fechaISO = new DateTime(SimpleDateParser.ISO8601(), "1991-10-30");
		Assert.assertEquals(fechaInicial, fechaISO.getDate());
	}
	
	@Test
	public void parsearFechaEnFormatoLatinoamericano()  {
		DateTime fechaLatinoamericana = new DateTime(SimpleDateParser.LatinAmerican(), "30/10/1991");
		Assert.assertEquals(fechaInicial, fechaLatinoamericana.getDate());
	}

	@Test
	public void parsearFechaEnFormatoNorteamericano()  {		
		DateTime fechaNorteamericana = new DateTime(SimpleDateParser.NorthAmerican(), "10-30-1991");
		Assert.assertEquals(fechaInicial, fechaNorteamericana.getDate());
	}
	
	@Test
	public void parsearFechaEnFormatoFlexible()  {		
		FlexibleDateParser parserFlexible = new FlexibleDateParser(Arrays.<DateParser>asList(SimpleDateParser.NorthAmerican(), SimpleDateParser.LatinAmerican()));				
		DateTime fechaFlexible = new DateTime(parserFlexible, "30/10/1991");
		Assert.assertEquals(fechaInicial, fechaFlexible.getDate());
	}
	
	@Test(expected = DateParserException.class)
	public void intentarParsearFechaEnFormatoFlexible()  {		
		FlexibleDateParser parserFlexible = new FlexibleDateParser(Arrays.<DateParser>asList(SimpleDateParser.LatinAmerican()));				
		DateTime fechaFlexible = new DateTime(parserFlexible, "1991-10-30");
		Assert.assertEquals(fechaInicial, fechaFlexible.getDate());
	}
	
	@Test(expected = DateParserException.class)
	public void intentarParsearFechaConFormatoErroneo()  {		
		DateTime fechaNorteamericana = new DateTime(SimpleDateParser.LatinAmerican(), "10-30-.1991");
		Assert.assertEquals(fechaInicial, fechaNorteamericana.getDate());
	}
	
	@Test
	public void diferenciaDeDias(){		
		DateTime fechaInicial = new DateTime(this.fechaInicial);
		DateTime fechaFinal = new DateTime(this.fechaFinal);
		Assert.assertEquals(4, fechaInicial.diasDeDiferenciaCon(fechaFinal));
	}
	
	@Test
	public void fechaInicialAnteriorFechaFinal(){
		DateTime fechaInicial = new DateTime(this.fechaInicial);
		DateTime fechaFinal = new DateTime(this.fechaFinal);
		Assert.assertTrue(fechaInicial.esAnteriorA(fechaFinal));
	}
}
