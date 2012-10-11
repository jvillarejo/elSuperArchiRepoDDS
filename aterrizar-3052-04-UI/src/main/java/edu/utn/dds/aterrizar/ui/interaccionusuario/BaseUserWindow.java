package edu.utn.dds.aterrizar.ui.interaccionusuario;

import java.util.List;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import edu.utn.dds.aterrizar.ui.appmodels.AsientoModel;
import edu.utn.dds.aterrizar.ui.appmodels.AsientoModelAdapter;
import edu.utn.dds.aterrizar.ui.appmodels.UsuarioModel;
import edu.utn.dds.aterrizar.ui.componentes.SimpleTable;
import edu.utn.dds.aterrizar.vuelo.Asiento;

abstract public class BaseUserWindow extends Window<UsuarioModel> {

	private static final long serialVersionUID = 6354269494094294267L;

	public BaseUserWindow(WindowOwner owner, UsuarioModel model) {
		super(owner, model);
		model.setResultados(this.getResultados(model));
	}

	@Override
	public void createContents(Panel panel) {
		panel.setLayout(new VerticalLayout()); 
		
		new Label(panel).setText(getLabelText());
		
		SimpleTable<AsientoModel> simpleTable = new SimpleTable<AsientoModel>(panel, AsientoModel.class)
			.addColumn("Salida", "fechaSalida")
			.addColumn("Aerolinea", "nombreAerolinea")
			.addColumn("Vuelo", "codigoDeVuelo")
			.addColumn("Asiento", "numeroAsiento")
			.addColumn("Precio", "precio");
		
		simpleTable.bindItemsToProperty("resultados");
		
		
		new Button(panel).setCaption("Cerrar")
			.onClick(new MessageSend(this, "close"));
	}

	protected List<AsientoModel> getResultados(UsuarioModel model) {
		return AsientoModelAdapter.toApplicationModel(this.getAsientos(model));
	}
	
	protected abstract List<Asiento> getAsientos(UsuarioModel model);
	protected abstract String getLabelText();
}
