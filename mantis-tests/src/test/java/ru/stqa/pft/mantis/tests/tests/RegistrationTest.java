package ru.stqa.pft.mantis.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.tests.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by owlowl on 04.11.16.
 */
public class RegistrationTest extends MantisTest{
	public void startMailServer(){
		app.mail().start();
	}
	@Test
	public void testRegistration() throws IOException, MessagingException {
		app.getDriver().get(app.configuration().getEntryPoint());
		
		long now=12;//System.currentTimeMillis();
		String email = "user"+now+"@localhost";
		String userName = "user"+now;
		String password = "password";
		app.james().createUser(userName,password);
		app.registration().start(userName, email);
		//List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
		List<MailMessage> mailMessages=app.james().waitForMail(userName,password,60000);
		String confirmationLink = getConfirmationLink(mailMessages, email);
		
		app.registration().finish(confirmationLink, password);
		
		Assert.assertTrue(app.newSession().login(userName,password));
	}
	
	private String getConfirmationLink(List<MailMessage> mailMessages, String email){
		MailMessage mailMessage = mailMessages.stream().filter((m) -> m.getTo().equals(email)).findAny().get();
		VerbalExpression regex= VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
		return regex.getText(mailMessage.getContent());
	}

	public void stopMailServer(){
		app.mail().stop();
	}
}
