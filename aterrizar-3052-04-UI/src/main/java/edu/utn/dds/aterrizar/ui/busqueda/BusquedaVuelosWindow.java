package edu.utn.dds.aterrizar.ui.busqueda;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.model.UserException;

import edu.utn.dds.aterrizar.ui.appmodels.AsientoModel;
import edu.utn.dds.aterrizar.ui.componentes.SimpleTable;
import edu.utn.dds.aterrizar.ui.mensajes.Accion;
import edu.utn.dds.aterrizar.ui.mensajes.MessageWindowBuilder;
import edu.utn.dds.aterrizar.ui.mensajes.SobreReservaMessageWindowBuilder;

public class BusquedaVuelosWindow extends SimpleWindow<BuscadorVuelos>{

	public BusquedaVuelosWindow(WindowOwner parent) {
		super(parent, new BuscadorVuelos());
		//this.getModelObject().search();
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
	
	protected void createResultsGrid(Panel mainPanel) {
		SimpleTable<AsientoModel> simpleTable = new SimpleTable<AsientoModel>(mainPanel, AsientoModel.class);
		simpleTable.setHeigth(200);
		simpleTable.setWidth(450);

		simpleTable
			.addColumn("Asiento", "numeroAsiento")
			.addColumn("Aerolinea", "nombreAerolinea")
			.addColumn("Vuelo", "codigoDeVuelo")
			.addColumn("Precio", "precio")
			.addColumn("Ubicacion", "ubicacion")
			.addColumn("Clase", "clase");

		simpleTable.bindItemsToProperty("resultados");
		simpleTable.bindValueToProperty("asientoSeleccionado");

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
			NotNullObservable elementSelected = new NotNullObservable("asientoSeleccionado");
			buy.bindEnabled(elementSelected);
			makeReservation.bindEnabled(elementSelected);
		}


	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));

		new Label(searchFormPanel).setText("Origen").setForeground(Color.BLACK);
		new TextBox(searchFormPanel).bindValueToProperty("origen");

		new Label(searchFormPanel).setText("Destino").setForeground(Color.BLACK);
		new TextBox(searchFormPanel).bindValueToProperty("destino");
		
		new Label(searchFormPanel).setText("Fecha").setForeground(Color.BLACK);
		new TextBox(searchFormPanel).bindValueToProperty("fechaSalida");
		
	}

	public void comprarAsiento(){
		try {
			this.getModelObject().comprar();
		} catch (RuntimeException e) {
			this.showDialog(Accion.COMPRA, e);
			return;
		}
		
		this.showDialog(Accion.COMPRA);
	}
	
	public void reservarAsiento() {
		try {
			this.getModelObject().reservar();
		} catch (UserException e) {
			showSobreReservaDialog();
			return;
		} catch (RuntimeException e) {
			showDialog(Accion.RESERVA, e);
			return;
		}
		
		this.showDialog(Accion.RESERVA);
	}
	
	private void showSobreReservaDialog() {
		this.openWindow(new SobreReservaMessageWindowBuilder().build(this, this.getModelObject().getAsientoSeleccionado()));
	}
	
	private void showDialog(Accion accion) { 
		this.openWindow(new MessageWindowBuilder(accion).build(this, this.getModelObject().getAsientoSeleccionado()));
	}
	
	private void showDialog(Accion accion, RuntimeException e) {
		this.openWindow(new MessageWindowBuilder(accion).build(this, e));
	}
	
	private void openWindow(Window<?> window) {
		window.open();
	}
	
	public void cerrar(){
		this.close();
	}
	
}
