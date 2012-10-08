package edu.utn.dds.aterrizar.ui.busqueda;

import java.awt.Color;

import edu.utn.dds.aterrizar.homes.UsuarioHome;
import edu.utn.dds.aterrizar.ui.main.AterrizarMainWindow;
import edu.utn.dds.aterrizar.vuelo.Busqueda;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;


public class BusquedaVuelosWindow extends SimpleWindow<Busqueda>{

	public BusquedaVuelosWindow(WindowOwner parent, Busqueda model) {
		super(parent, model);
	//	this.getModelObject().search();
	}

	private static final long serialVersionUID = -7226693291529135759L;

	@Override
	protected void addActions(Panel arg0) {
		// TODO Auto-generated method stub
		
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
