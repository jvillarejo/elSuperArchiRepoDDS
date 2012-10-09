package edu.utn.dds.aterrizar.ui.busqueda;

import java.awt.Color;

import edu.utn.dds.aterrizar.ui.transformers.*;
import edu.utn.dds.aterrizar.vuelo.Asiento;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
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
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Buscar")
		.onClick(new MessageSend(this.getModelObject(), "search"))
		.setAsDefault()
		.disableOnError();
		
	}
	
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Buscador de vuelos");
		this.setTaskDescription("Ingrese los parámetros de búsqueda");

		super.createMainTemplate(mainPanel);

		this.createResultsGrid(mainPanel);
		this. createGridActions(mainPanel);
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
	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());
		
		Button buy = new Button(actionsPanel)
		.setCaption("Comprar")
		.onClick(new MessageSend(this, "comprarAsiento"));
		
		Button makeReservation = new Button(actionsPanel)
		.setCaption("Reservar")
		.onClick(new MessageSend(this,"reservarAsiento"));
		
		new Button(actionsPanel)
		.setCaption("Cerrar")
		.onClick(new MessageSend(this, "cerrar"));
		
		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
//		NotNullObservable elementSelected = new NotNullObservable("vueloSeleccionado");
//		buy.bindEnabled(elementSelected);
//		makeReservation.bindEnabled(elementSelected);
	}

	protected void describeResultsGrid(Table<Asiento> table) {
		//this.createSimpleColumn(table, "Aerolinea", "aerolinea");
		//this.createSimpleColumn(table, "Vuelo", "codigo");
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

	public void comprarAsiento(){
		//TODO: acá deberíamos llamar al comprar de la agencia. Eso podría estar en una home
	}
	public void reservarAsiento(){
		//TODO: idem lo de arriba
	}
	
	public void cerrar(){
		this.close();
	}
}
