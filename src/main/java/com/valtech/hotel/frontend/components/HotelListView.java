package com.valtech.hotel.frontend.components;

import com.valtech.hotel.backend.entity.Hotel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import java.util.List;

public class HotelListView extends ListView<Hotel> {
	public HotelListView(final String id, final IModel<? extends List<Hotel>> model) {
		super(id, model);

		setOutputMarkupId(true);
	}

	@Override
	protected void populateItem(final ListItem<Hotel> listItem) {
		  listItem.add(new HotelPanel("hotel", listItem.getModel()));
	}
}
