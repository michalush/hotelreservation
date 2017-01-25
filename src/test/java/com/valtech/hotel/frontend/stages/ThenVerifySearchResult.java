package com.valtech.hotel.frontend.stages;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.WicketTester;

public class ThenVerifySearchResult extends Stage<ThenVerifySearchResult> {
    @ProvidedScenarioState
    private WicketTester wicketTester;

    public ThenVerifySearchResult searchbar_is_visible() {
        wicketTester.assertComponent("mainsearch", TextField.class);

        return self();
    }
}
