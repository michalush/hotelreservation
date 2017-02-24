package com.valtech.hotel;

import com.valtech.hotel.frontend.AdminPage;
import com.valtech.hotel.spring.WicketApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */

@Ignore
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(AdminPage.class);

		//assert rendered page class
		tester.assertRenderedPage(AdminPage.class);
	}
}
