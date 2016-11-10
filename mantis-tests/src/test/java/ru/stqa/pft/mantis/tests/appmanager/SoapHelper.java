package ru.stqa.pft.mantis.tests.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.tests.model.Issue;
import ru.stqa.pft.mantis.tests.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by owlowl on 09.11.16.
 */
public class SoapHelper {
	ApplicationManager app;
	public SoapHelper(ApplicationManager app) {
		this.app=app;
	}
	
	public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
		MantisConnectPortType mc = getMantisConnect();
		ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "secret");
		return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
	}
	
	public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
		return new MantisConnectLocator()
					.getMantisConnectPort(new URL(app.configuration().getSoapPage()));
	}
	
	public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
		BigInteger projectID = BigInteger.valueOf(issue.getProject().getId());
		ApplicationManager.AppConfiguration.LoginData mantisAdmin =app.configuration().Web();
		MantisConnectPortType mc = getMantisConnect();
		
		String[] categories = mc.mc_project_get_categories(mantisAdmin.getUsername(),mantisAdmin.getPassword(), projectID);
		IssueData issueData= new IssueData();
		issueData.setSummary(issue.getSummary());
		issueData.setDescription(issue.getDescription());

		issueData.setProject(new ObjectRef(projectID,issue.getProject().getName()));
		issueData.setCategory(categories[0]);
		BigInteger issueId = mc.mc_issue_add(mantisAdmin.getUsername(),mantisAdmin.getPassword(), issueData);
		IssueData createdIssueData = mc.mc_issue_get(mantisAdmin.getUsername(),mantisAdmin.getPassword(), issueId);
		return new Issue(createdIssueData);
	}
	
	public Issue getIssue(int issueId){
		MantisConnectPortType mc = null;
		try {
			mc = getMantisConnect();
		ApplicationManager.AppConfiguration.LoginData mantisAdmin =app.configuration().Web();
		IssueData issueData = mc.mc_issue_get(mantisAdmin.getUsername(), mantisAdmin.getPassword(), BigInteger.valueOf(issueId));
		return new Issue(issueData);
		} catch (ServiceException|MalformedURLException|RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
}
