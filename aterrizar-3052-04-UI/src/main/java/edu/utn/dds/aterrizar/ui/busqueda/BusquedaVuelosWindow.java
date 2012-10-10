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

import edu.utn.dds.aterrizar.ui.componentes.SimpleTable;
import edu.utn.dds.aterrizar.ui.transformers.AsientoToAerolineaNameTransformer;
import edu.utn.dds.aterrizar.ui.transformers.AsientoToCodigoVueloTransformer;
import edu.utn.dds.aterrizar.ui.transformers.ClaseToStringTransformer;
import edu.utn.dds.aterrizar.ui.transformers.UbicacionToStringTransformer;
import edu.utn.dds.aterrizar.vuelo.Asiento;


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
		SimpleTable<Asiento> simpleTable = new SimpleTable<Asiento>(mainPanel, Asiento.class);
		simpleTable.setHeigth(200);
		simpleTable.setWidth(450);

		simpleTable.addColumn("Asiento", "numeroDeAsiento")
			.addColumn("Aerolinea", new AsientoToAerolineaNameTransformer())
			.addColumn("Vuelo", new AsientoToCodigoVueloTransformer())
			.addColumn("Precio", "precio")
			.addColumn("Ubicacion", new UbicacionToStringTransformer())
			.addColumn("Clase", new ClaseToStringTransformer());

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
		this.getModelObject().comprar();
			}
	
	public void reservarAsiento() {
		try {
			this.getModelObject().reservar();
		} catch (UserException are) {
			this.openWindow(new SobreReservaWindow(this, this.getModelObject()
					.getAsientoSeleccionado()));
		}
	}
	
	private void openWindow(Window<?> window) {
		window.open();
	}
	
	public void cerrar(){
		this.close();
	}
	
}
