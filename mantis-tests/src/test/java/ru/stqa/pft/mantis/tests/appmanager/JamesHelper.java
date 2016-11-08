package ru.stqa.pft.mantis.tests.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import ru.stqa.pft.mantis.tests.model.MailMessage;

import javax.mail.*;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by owlowl on 07.11.16.
 */
public class JamesHelper {
	private ApplicationManager app;
	
	private TelnetClient telnet;
	private java.io.InputStream in;
	private PrintStream out;
	
	private Session mailSession;
	private Store store;
	private String mailServer;
	
	public JamesHelper(ApplicationManager app) {
		this.app = app;
		telnet = new TelnetClient();
		mailSession = Session.getDefaultInstance(System.getProperties());
	}
	
	public boolean doesUserExists(String name) {
		initTelnetSession();
		write("verify " + name);
		String result = readUntil("exist");
		closeTelnetSession();
		return result.trim().equals("User " + name + " exist");
	}
	
	public void createUser(String userName, String password) {
		initTelnetSession();
		write("adduser " + userName + " " + password);
		String result = readUntil("User " + userName + " added");
		closeTelnetSession();
	}
	
	
	public void deleteUser(String userName) {
		initTelnetSession();
		write("deluser " + userName);
		String result = readUntil("User " + userName + " deleted");
		closeTelnetSession();
	}
	
	private void closeTelnetSession() {
		write("quit");
	}
	
	private void initTelnetSession() {
		mailServer = app.configuration().mailServer().host();
		String username = app.configuration().mailServer().admin().getUsername();
		String password = app.configuration().mailServer().admin().getPassword();
		try {
			telnet.connect(mailServer, app.configuration().mailServer().port());
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		readUntil("Login id:");
		write("");
		readUntil("Password:");
		write("");
		
		readUntil("Login id:");
		
		write(username);
		readUntil("Password:");
		write(password);
		
		readUntil("Welcome " + username + ". HELP for a list of commands");
	}
	
	private String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void write(String content) {
		try {
			out.println(content);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MailMessage> waitForMail(String userName, String password, int timeout) throws MessagingException {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() < start + timeout) {
			List<MailMessage> allMail = getAllMail(userName, password);
			if (allMail.size() > 0) {
				return allMail;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		throw new Error("No mail :(");
	}
	
	private List<MailMessage> getAllMail(String userName, String password) throws MessagingException {
		Folder inbox = openInbox(userName, password);
		List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
		closeFolder(inbox);
		return messages;
	}
	
	private static MailMessage toModelMail(Message m) {
		try {
			return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Folder openInbox(String userName, String password) throws MessagingException {
		store = mailSession.getStore("pop3");
		store.connect(mailServer, userName, password);
		Folder folder = store.getDefaultFolder().getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		return folder;
	}
	
	private void closeFolder(Folder folder) throws MessagingException {
		folder.close(true);
		store.close();
	}
	
	public void drainEmail(String username, String password) throws MessagingException {
		Folder inbox = openInbox(username, password);
		for (Message message: inbox.getMessages()){
			message.setFlag(Flags.Flag.DELETED,true);
		}
		closeFolder(inbox);
	}
}
