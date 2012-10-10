package edu.utn.dds.aterrizar.ui.main;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.windows.Window;

import edu.utn.dds.aterrizar.homes.UsuarioHome;
import edu.utn.dds.aterrizar.ui.busqueda.BusquedaVuelosWindow;
import edu.utn.dds.aterrizar.ui.interaccionusuario.ComprasWindow;
import edu.utn.dds.aterrizar.ui.interaccionusuario.ReservasWindow;
import edu.utn.dds.aterrizar.usuario.Usuario;

public class AterrizarMainWindow extends MainWindow<Usuario> {

	public AterrizarMainWindow(Usuario model) {
		super(model);
	}


	private static final long serialVersionUID = -7226693291529135758L;

	public static void main(String[] args) {
		new AterrizarMainWindow(UsuarioHome.getInstance().getDefaultUser()).startApplication();
	}


	@Override
	public void createContents(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		//TODO Bindear el nombre con el label no setearselo a manopla
		new Label(mainPanel).setText("Hola " + this.getModelObject().getNombreCompleto() + "\n\n" + "¿Qué desea hacer?" + "\n");
		
		Panel buttonPanel = new Panel(mainPanel).setLayout(new HorizontalLayout());
		
		new Button(buttonPanel)
			.setCaption("Ver Compras")
			.onClick(new MessageSend(this, "abrirCompras"));
		new Button(buttonPanel)
			.setCaption("Ver Reservas")
			.onClick(new MessageSend(this, "abrirReservas"));
		
		new Button(buttonPanel)
			.setCaption("Buscar Asientos")
			.onClick(new MessageSend(this, "buscarAsientos"));
	}
	
	// ********************************************************
	// ** Acciones
	// ********************************************************
	
	public void abrirCompras() {
		this.openWindow(new ComprasWindow(this, this.getModelObject()));
	}
	
	public void abrirReservas() {
		this.openWindow(new ReservasWindow(this, this.getModelObject()));

	}

	public void buscarAsientos() {
		this.openWindow(new BusquedaVuelosWindow(this));
	}
	
	private void openWindow(Window<?> window) {
		window.open();
	}
}
