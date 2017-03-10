package com.valtech.hotel.frontend.components;

import com.valtech.hotel.backend.entity.Hotel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

public class HotelPanel extends GenericPanel<Hotel> {

    public HotelPanel(String id, IModel<Hotel> model) {
        super(id, model);

        final Hotel hotel = model.getObject();
        add(new Label("name", hotel.getName()));
        add(new Label("description", hotel.getDescription()));
        add(new Label("rating", hotel.getRating()));

        setOutputMarkupId(true);
    }
}
