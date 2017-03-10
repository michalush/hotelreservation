package com.valtech.hotel.frontend.components;

import com.valtech.hotel.backend.entity.Hotel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.util.StringUtils;

public class HotelPanel extends GenericPanel<Hotel> {

    public HotelPanel(String id, IModel<Hotel> model) {
        super(id, model);

        final Hotel hotel = model.getObject();
        add(new Label("name", hotel.getName()));
        add(new Label("description", createDescription(hotel)));
        add(new Label("rating", hotel.getRating()));

        setOutputMarkupId(true);
    }

    private String createDescription(Hotel hotel) {
        final String description = hotel.getDescription();

        if (StringUtils.isEmpty(description)) {
            return "No information available";
        }

        return description;
    }
}
