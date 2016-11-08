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
public class ChangePasswordTest extends MantisTest{
	@Test
	public void changePassword() throws IOException, MessagingException {
		String username = "user12";
		String email = username+"@localhost";
		String mailpassword = "password";
		String password = "new_password";
		app.james().drainEmail(username,mailpassword);
		app.navigateTo().login(app.configuration().getUsername(),app.configuration().getPassword());
		app.navigateTo().resetPassword(username);

		List<MailMessage> mailMessages=app.james().waitForMail(username,mailpassword,60000);
		String link = getConfirmationLink(mailMessages,email);
		System.out.println(link);
		app.navigateTo().editPersonalInfo(link,password);
		Assert.assertTrue(app.newSession().login(username,password));
	}
	
	private String getConfirmationLink(List<MailMessage> mailMessages, String email){
		MailMessage mailMessage = mailMessages.stream().filter((m) -> m.getTo().equals(email)).findAny().get();
		VerbalExpression regex= VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
		return regex.getText(mailMessage.getContent());
	}
	
}
