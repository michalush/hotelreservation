package com.valtech.hotel.frontend;

import com.tngtech.jgiven.junit.ScenarioTest;
import com.valtech.hotel.frontend.stages.GivenUserOnHotelPage;
import com.valtech.hotel.frontend.stages.ThenVerifySearchResult;
import com.valtech.hotel.frontend.stages.WhenUserSearches;
import org.junit.Ignore;
import org.junit.Test;
 @Ignore
public class HotelSearchPageTest extends ScenarioTest<GivenUserOnHotelPage, WhenUserSearches, ThenVerifySearchResult> {

    @Test
    public void verify_Searchbar_on_Hotels_Page() {
        given().the_application_is_running()
        .when().user_is_on_the_Hotel_Search_page();

        then().searchbar_is_visible();
    }

}