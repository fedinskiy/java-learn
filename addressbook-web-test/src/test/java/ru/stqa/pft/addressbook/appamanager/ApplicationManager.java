package ru.stqa.pft.addressbook.appamanager;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.security.auth.login.AppConfigurationEntry;
import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by owlowl on 22.09.16.
 */
public class ApplicationManager {
	private AppNavigation appNavigation;
	private ContactHelper contactHelper;
	private RemoteWebDriver wd;
	private GroupHelper groupHelper;
	private Session session;
	private String browser;
	private AppConfiguration config;
	private final String configPath="configuration/config.xml";
	public ApplicationManager(String browser) {
		this.browser = browser;
	}
	 
	public void init() {
	
		try{
			loadConfiguration();
		}catch(Exception E){
			throw new IllegalArgumentException("Не удалось получит файл конфигурации",E);
		}
		if (browser.equals(BrowserType.FIREFOX)) {
			wd = new FirefoxDriver();
		}else if(BrowserType.CHROME.equals(browser)) {
			wd = new ChromeDriver();
		}else if(BrowserType.IE.equals(browser)) {
			wd = new InternetExplorerDriver();
		
		}

		wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		wd.get(config.getEntryPoint());
		groupHelper = new GroupHelper(wd);
		contactHelper = new ContactHelper(wd);
		appNavigation = new AppNavigation(wd);
		session=new Session(wd);
		getSession().login(config.getUsername(),config.getPassword());
	}
	
	private void loadConfiguration() throws IOException {
		File source = new File(configPath);
		if (!source.exists()) {
			throw new FileNotFoundException("Не найден файл " + source.getAbsolutePath());
		}
		
		String xml = "";
		XStream xstream = new XStream();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
			
			String line = reader.readLine();
			while (null != line) {
				xml += line;
				line = reader.readLine();
			}
			xstream.processAnnotations(AppConfiguration.class);
			this.config = (AppConfiguration) xstream.fromXML(xml);
		}
	}
	
	
	public void stop() {
		wd.quit();
	}
	
	public GroupHelper groups() {
		return groupHelper;
	}
	
	public ContactHelper contacts() {
		return contactHelper;
	}
	
	public AppNavigation moveTo() {
		return appNavigation;
	}
	
	public Session getSession() {
		return session;
	}
	
	@XStreamAlias("config")
	private class AppConfiguration {
		private String username;
		private String password;
		private String entryPoint;
		
		public String getUsername() {
			return username;
		}
		
		public String getPassword() {
			return password;
		}
		
		public String getEntryPoint() {
			return entryPoint;
		}
		 
	}
}
