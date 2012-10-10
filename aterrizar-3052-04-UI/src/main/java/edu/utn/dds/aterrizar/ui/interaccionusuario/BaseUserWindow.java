package edu.utn.dds.aterrizar.ui.interaccionusuario;

import java.util.List;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.ui.componentes.SimpleTable;
import edu.utn.dds.aterrizar.ui.transformers.SalidaAsientoToStringTransformer;
import edu.utn.dds.aterrizar.usuario.Usuario;
import edu.utn.dds.aterrizar.vuelo.Asiento;

abstract public class BaseUserWindow extends Window<Usuario> {

	private static final long serialVersionUID = 6354269494094294267L;

	private List<Asiento> resultados;
	
	public BaseUserWindow(WindowOwner owner, Usuario model) {
		super(owner, model);
		resultados = this.getResultados(model);
	}

	@Override
	public void createContents(Panel panel) {
		panel.setLayout(new VerticalLayout()); 
		
		new Label(panel).setText(getLabelText());
		
		SimpleTable<Asiento> simpleTable = new SimpleTable<Asiento>(panel, Asiento.class)
			.addColumn("Salida", new SalidaAsientoToStringTransformer())
			.addColumn("Aerolinea", "nombreDeAerolinea")
			.addColumn("Vuelo", "codigoDeVuelo")
			.addColumn("Asiento", "numeroDeAsiento")
			.addColumn("Precio", "precio");
		
		simpleTable.bindItemsToProperty("resultados");
		
		
		new Button(panel).setCaption("Cerrar")
			.onClick(new MessageSend(this, "close"));
	}

	abstract protected List<Asiento> getResultados(Usuario model);
	abstract protected String getLabelText();

}
