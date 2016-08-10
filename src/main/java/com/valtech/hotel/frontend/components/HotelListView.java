package com.valtech.hotel.frontend.components;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import java.util.List;

public class HotelListView extends ListView<String> {
	public HotelListView(final String id, final IModel<? extends List<String>> model) {
		super(id, model);
	}

	@Override
	protected void populateItem(final ListItem<String> listItem) {

	}
}
