package edu.utn.dds.aterrizar.ui.mensajes;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;

public class SimpleMessageWindow extends Window<String> {

	private Panel buttonPanel;

	public SimpleMessageWindow(WindowOwner owner, String message) {
		super(owner, message);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void createContents(Panel panel) {
		panel.setLayout(new VerticalLayout());
		
		new Label(panel).setText(this.getModelObject());
		
		buttonPanel = new Panel(panel).setLayout(new HorizontalLayout());
	}

	public SimpleMessageWindow addActionButton(String caption, Action action) { 
		new Button(buttonPanel)
			.setCaption(caption)
			.onClick(action);
		return this;
	}

}
