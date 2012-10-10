package edu.utn.dds.aterrizar.ui.busqueda;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.homes.UsuarioHome;
import edu.utn.dds.aterrizar.vuelo.Asiento;

public class SobreReservaWindow extends SimpleWindow<Asiento>{
	
	public SobreReservaWindow(WindowOwner owner, Asiento model) {
		super(owner, model);
	}

	
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Sobrereservar").onClick(new MessageSend(this.getModelObject(), "sobreReservar"));
		new Button(actionsPanel).setCaption("Seguir Buscando").onClick(new MessageSend(this, "close"));
	}
	
	public void sobreReservar(){
		this.getModelObject().sobreReservar(UsuarioHome.getInstance().getDefaultUser());
	}


	@Override
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setText("El asiento " + this.getModelObject().getCodigoDeVuelo() + " ya se encuentra reservado.");
		new Label(mainPanel).setText("¿Qué desea hacer?");
		
	}

}
