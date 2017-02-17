package com.valtech.hotel.frontend;

import com.valtech.hotel.backend.service.HotelIndexService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AdminPage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private HotelIndexService hotelIndexService;

	public AdminPage(final PageParameters parameters) {
		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
		add(new AjaxLink<String>("createIndex", Model.of("Create Index")) {
			@Override
			public void onClick(AjaxRequestTarget target) {
				 hotelIndexService.createIndexAndPopulateData();
			}
		});
    }
}
