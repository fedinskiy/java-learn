package ru.stqa.pft.mantis.tests.tests;

import org.testng.annotations.Test;

/**
 * Created by owlowl on 04.11.16.
 */
public class RegistrationTest extends MantisTest{
	@Test
	public void testRegistration(){
				app.registration().start("user1", "user1@localhost.localdomain");
	}
}
