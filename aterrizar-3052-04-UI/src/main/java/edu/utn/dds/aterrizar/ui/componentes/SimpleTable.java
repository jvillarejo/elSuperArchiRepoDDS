package edu.utn.dds.aterrizar.ui.componentes;

import org.uqbar.arena.widgets.Container;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;

import com.uqbar.commons.collections.Transformer;

public class SimpleTable<T> extends Table<T> {

	public SimpleTable(Container container, Class<T> itemType) {
		super(container, itemType);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3977678464350866681L;

	public SimpleTable<T> addColumn(String title, String propertyName) {
		new Column<T>(this)
			.setTitle(title)
			.bindContentsToProperty(propertyName);
		
		return this;
	}

	public SimpleTable<T> addColumn(String title,
			Transformer<T, String> transformer) {
		new Column<T>(this)
			.setTitle(title)
			.bindContentsToTransformer(transformer);
		
		return this;
		
	}
}
