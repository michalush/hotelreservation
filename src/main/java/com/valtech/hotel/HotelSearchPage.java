package com.valtech.hotel;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.EmptyPanel;

public class HotelSearchPage extends WebPage {

	public HotelSearchPage() {
		super();
		add(new TextField<String>("mainsearch"));
		add(new EmptyPanel("hotels"));
	}
}
