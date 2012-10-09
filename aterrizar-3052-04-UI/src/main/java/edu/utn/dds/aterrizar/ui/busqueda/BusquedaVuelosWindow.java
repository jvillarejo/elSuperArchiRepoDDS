package edu.utn.dds.aterrizar.ui.busqueda;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.ui.componentes.SimpleTable;
import edu.utn.dds.aterrizar.ui.transformers.ClaseToStringTransformer;
import edu.utn.dds.aterrizar.ui.transformers.UbicacionToStringTransformer;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

public class BusquedaVuelosWindow extends SimpleWindow<Busqueda>{

	public BusquedaVuelosWindow(WindowOwner parent) {
		super(parent, new Busqueda());
	//	this.getModelObject().search();
	}

	private static final long serialVersionUID = -7226693291529135759L;

	@Override
	protected void addActions(Panel arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Buscador de vuelos");
		this.setTaskDescription("Ingrese los parámetros de búsqueda");

		super.createMainTemplate(mainPanel);

		this.createResultsGrid(mainPanel);
	}

	// *************************************************************************
	// ** RESULTADOS DE LA BUSQUEDA
	// *************************************************************************

	 protected void createResultsGrid(Panel mainPanel) {
		SimpleTable<Asiento> simpleTable = new SimpleTable<Asiento>(mainPanel, Asiento.class);
		simpleTable.setHeigth(200);
		simpleTable.setWidth(450);
		
		simpleTable.addColumn("Asiento", "numeroDeAsiento")
			.addColumn("Precio", "precio")
			.addColumn("Ubicacion",new UbicacionToStringTransformer())
			.addColumn("Clase", new ClaseToStringTransformer());

		simpleTable.bindItemsToProperty("resultados");

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));

		new Label(searchFormPanel).setText("Origen").setForeground(Color.BLUE);
		new TextBox(searchFormPanel).bindValueToProperty("origen");

		new Label(searchFormPanel).setText("Destino").setForeground(Color.BLUE);
		new TextBox(searchFormPanel).bindValueToProperty("destino");
		
		new Label(searchFormPanel).setText("Fecha").setForeground(Color.BLUE);
		new TextBox(searchFormPanel).bindValueToProperty("fechaSalida");
	}

}
