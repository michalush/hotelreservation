package com.valtech.hotel.frontend;

import com.valtech.hotel.backend.entity.Hotel;
import com.valtech.hotel.backend.repository.HotelSearchRepository;
import com.valtech.hotel.frontend.components.HotelListView;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.util.StringUtils;

import java.util.List;

public class HotelSearchPage extends WebPage {
	@SpringBean
	private HotelSearchRepository repository;

	private String searchTerm;

	public HotelSearchPage() {
		super();

		final WebMarkupContainer hotelsContainer = new WebMarkupContainer("hotelsContainer");
		hotelsContainer.setOutputMarkupId(true);
		final HotelListView hotelListView = new HotelListView("hotels", new HotelsModel());
		hotelsContainer.add(hotelListView);
		add(hotelsContainer);

		final Form searchForm = new Form("searchForm");
		final TextField<String> searchField = new TextField<>("search", new PropertyModel<>(this, "searchTerm"));
		searchField.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(hotelsContainer);
			}
		});
		searchForm.add(searchField);
		add(searchForm);

	}

	private class HotelsModel extends LoadableDetachableModel<List<Hotel>> {
		@Override
        protected List<Hotel> load() {
			String orderColumn = "_score";

			if (StringUtils.isEmpty(searchTerm)) {
				orderColumn = "rating";
			}

			return repository.findHotel(searchTerm, orderColumn);
        }
	}
}
