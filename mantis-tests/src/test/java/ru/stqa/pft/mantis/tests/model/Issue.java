package ru.stqa.pft.mantis.tests.model;

import biz.futureware.mantis.rpc.soap.client.IssueData;

import java.math.BigInteger;

/**
 * Created by owlowl on 10.11.16.
 */
public class Issue {
	private int id;
	private String summary;
	private String description;
	private Project project;
	private boolean closed;
	
	public Issue() {
		this.id = 0;
		this.summary = null;
		this.description = null;
		this.project = null;
	}
	

	
	public Issue(IssueData issue){
		this.withId(issue.getId());
		this.withDescription(issue.getDescription());
		this.withSummary(issue.getSummary());
		this.closed=(issue.getStatus().getName().equals("resolved")&&issue.getResolution().getName().equals("fixed"));
		this.withProject(new Project().withName(issue.getProject().getName()).withId(issue.getProject().getId()));
	}
	
	public int getId() {
		return id;
	}
			
	public String getSummary() {
		return summary;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Project getProject() {
		return project;
	}
	
	public Issue withSummary(String summary) {
		this.summary = summary;
		return this;
	}
	
	public Issue withDescription(String description) {
		this.description = description;
		return this;
	}
	
	public Issue withProject(Project project) {
		this.project = project;
		return this;
	}
	
	public Issue withId(int id) {
		this.id = id;
		return this;
	}
	
	public Issue withId(BigInteger id) {
		return withId(id.intValue());
	}
	
	public boolean isOpen() {
		return !closed;
	}
	
}
