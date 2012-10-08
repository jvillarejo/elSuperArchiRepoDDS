package edu.utn.dds.aterrizar.ui.busqueda;

import java.awt.Color;

import edu.utn.dds.aterrizar.homes.UsuarioHome;
import edu.utn.dds.aterrizar.ui.main.AterrizarMainWindow;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

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

	private Column<Asiento> createSimpleColumn(Table<Asiento> table, String title, String propertyName) {
		return new Column<Asiento>(table)
			.setTitle(title)
			.bindContentsToProperty(propertyName);
	}
	
	protected void createResultsGrid(Panel mainPanel) {
		Table<Asiento> table = new Table<Asiento>(mainPanel, Asiento.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("resultados");

		this.describeResultsGrid(table);
	}

	protected void describeResultsGrid(Table<Asiento> table) {
		this.createSimpleColumn(table, "Asiento", "numeroDeAsiento");
		this.createSimpleColumn(table, "Precio", "precio");
		
		new Column<Asiento>(table)
			.setTitle("Ubicacion")
			.bindContentsToTransformer(new UbicacionToStringTransformer());
		
		new Column<Asiento>(table)
			.setTitle("Clase")
			.bindContentsToTransformer(new ClaseToStringTransformer());
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
