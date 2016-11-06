package ru.stqa.pft.mantis.tests.appmanager;


import org.apache.commons.net.ftp.FTPClient;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by owlowl on 05.11.16.
 */
public class FTPHelper {
	 private final ApplicationManager app;
	private FTPClient ftp;
	
	public FTPHelper(ApplicationManager app) {
		this.app = app;
		ftp = new FTPClient();
	}
	
	public void upload(File file, String target, String backup) throws IOException {
		ftp.connect(app.configuration().getFTPHost());
		Assert.assertTrue(ftp.login(app.configuration().FTP().getUsername(),app.configuration().FTP().getPassword()));
		ftp.deleteFile(backup);
		ftp.rename(target,backup);
		ftp.enterLocalPassiveMode();
		ftp.storeFile(target,new FileInputStream(file));
		ftp.disconnect();
	}
	public void restore(String backup, String target) throws IOException {
		ftp.connect(app.configuration().getFTPHost());
		ftp.login(app.configuration().FTP().getUsername(),app.configuration().FTP().getPassword());
		ftp.deleteFile(target);
		ftp.rename(backup,target);
		ftp.disconnect();
	}
}
