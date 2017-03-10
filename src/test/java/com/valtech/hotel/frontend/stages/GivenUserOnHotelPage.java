package com.valtech.hotel.frontend.stages;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.valtech.hotel.frontend.HotelSearchPage;
import com.valtech.hotel.spring.WicketApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;

public class GivenUserOnHotelPage extends Stage<GivenUserOnHotelPage> {
    @ProvidedScenarioState
    private WicketTester wicketTester;
    private ApplicationContextMock contextMock;

    public GivenUserOnHotelPage the_application_is_running() {
        wicketTester = new WicketTester(new WicketApplication() {
            @Override
            public void init() {
                final SpringComponentInjector injector = new SpringComponentInjector(this, contextMock);
                getComponentInstantiationListeners().add(injector);
                injector.inject(this);
                super.init();
            }
        });

        return self();
    }

    public GivenUserOnHotelPage user_is_on_the_Hotel_Search_page() {
        wicketTester.startPage(HotelSearchPage.class);

        return self();
    }
}
