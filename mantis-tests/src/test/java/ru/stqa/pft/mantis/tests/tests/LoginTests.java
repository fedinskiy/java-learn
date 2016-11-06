package ru.stqa.pft.mantis.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.tests.appmanager.Session;

import java.io.IOException;

/**
 * Created by owlowl on 04.11.16.
 */
public class LoginTests extends MantisTest {
	@Test
	public void testLogin() throws IOException {
		Session session = app.newSession();
		Assert.assertTrue(session.login(app.configuration().getUsername(),app.configuration().getPassword()));
		Assert.assertTrue(session.isLoggedAs("administrator"));
	}
}
