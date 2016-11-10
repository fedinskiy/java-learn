package ru.stqa.pft.mantis.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.tests.model.Issue;
import ru.stqa.pft.mantis.tests.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by owlowl on 09.11.16.
 */
public class SoapTests extends MantisTest{
	@Test
	public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
		Set<Project> projects =app.soap().getProjects();
		System.out.println(projects.size());
		for(Project proj:projects){
			System.out.println(proj.getName());
		}
	}
	
	@Test
	public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
		skipIfNotFixed(1);
		Project project=app.soap().getProjects().iterator().next();
		Issue issue = new Issue().withSummary("Test issue").withDescription("Test Description").withProject(project);
		Issue created = app.soap().addIssue(issue);
		Assert.assertEquals(issue.getSummary(),created.getSummary());
	}
}
