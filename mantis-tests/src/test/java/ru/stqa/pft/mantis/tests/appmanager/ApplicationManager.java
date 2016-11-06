package ru.stqa.pft.mantis.tests.appmanager;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by owlowl on 22.09.16.
 */
public class ApplicationManager {
	private RemoteWebDriver wd;
	private String browser;
	private AppConfiguration config;
	private RegistrationHelper regHelper;
	private FTPHelper ftp;
	
	public ApplicationManager(String browser) {
		this.browser = browser;
	}
	
	public void init() {
		try {
			loadConfiguration();
		} catch (Exception E) {
			throw new IllegalArgumentException("Не удалось получить файл конфигурации ", E);
		}
	}
	
	private void loadConfiguration() throws IOException {
		String properties = System.getProperty("target", "local");
		String configPath = String.format("src/test/resources/%s.properties", properties);
		File source;
		try {
			source = new File(configPath);
		} catch (Exception e) {
			throw new FileNotFoundException("Не найден файл " + configPath);
		}
		
		if (null == source || !source.exists()) {
			throw new FileNotFoundException("Не найден файл " + source.getAbsolutePath());
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
			this.config = new AppConfiguration(reader);
			
		}
		
	}
	
	
	public void stop() {
		if (null != wd) wd.quit();
	}
	
	
	public AppConfiguration configuration() {
		return config;
	}
	
	public Session newSession() {
		return new Session(this);
	}
	
	public RegistrationHelper registration() {
		if (null == regHelper) {
			regHelper = new RegistrationHelper(this);
		}
		return regHelper;
	}
	
	public FTPHelper ftp() {
		if (null == ftp) {
			ftp = new FTPHelper(this);
		}
		return ftp;
		
	}
	
	public WebDriver getDriver() {
		if (null == wd) {
			if (browser.equals(BrowserType.FIREFOX)) {
				wd = new FirefoxDriver();
			} else if (BrowserType.CHROME.equals(browser)) {
				wd = new ChromeDriver();
			} else if (BrowserType.IE.equals(browser)) {
				wd = new InternetExplorerDriver();
				
			}
			
			wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			wd.get(config.getEntryPoint());
		}
		return wd;
	}
	
	
	@XStreamAlias("config")
	public class AppConfiguration {
		private final Properties properties;
		private String baseUrl;
		private String loginPage;
		private String startPage;
		private String signPage;
		private LoginData ftpData;
		private String FTPHost;
		
		public LoginData FTP() {
			return ftpData;
		}
		
		public LoginData Web() {
			return webData;
		}
		
		private LoginData webData;
		private boolean useUIChecks;
		
		public AppConfiguration(BufferedReader reader) throws IOException {
			properties = new Properties();
			properties.load(reader);
			baseUrl = properties.getProperty("web.baseUrl");
			loginPage = properties.getProperty("web.loginPage");
			startPage = properties.getProperty("web.entryPoint");
			signPage = properties.getProperty("web.signPoint");
			FTPHost = properties.getProperty("ftp.host");
			webData = new LoginData("web");
			ftpData = new LoginData("ftp");
			useUIChecks = Boolean.getBoolean("verifyUI");
		}
		
		public String getUsername() {
			return webData.getUsername();
		}
		
		public String getPassword() {
			return webData.getPassword();
		}
		
		public String getMainPage() {
			return baseUrl + startPage;
		}
		
		public String getEntryPoint() {
			return baseUrl + loginPage;
		}
		
		public String getSignPage() {
			return baseUrl + signPage;
		}
		
		public boolean isUseUIChecks() {
			return useUIChecks;
		}
		
		public String getFTPHost() {
			return FTPHost;
		}
		
		public class LoginData {
			private String username;
			private String password;
			
			public LoginData(String username, String password) {
				this.username = username;
				this.password = password;
			}
			
			public LoginData(String propertiesPrefix) {
				username = properties.getProperty(propertiesPrefix + ".username");
				password = properties.getProperty(propertiesPrefix + ".password");
			}
			
			public String getUsername() {
				return username;
			}
			
			public String getPassword() {
				return password;
			}
		}
		
	}
}
