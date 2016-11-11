package ru.stqa.pft.rest;


/**
 * Created by owlowl on 11.11.16.
 */
public class Issue {
	private int id;
	private String description ;
	private String subject;
		
	public Issue withId(int newId) {
		this.id=newId;
		return this;
	}
	
	public Issue withDescription(String description) {
		this.description = description;
		return this;
	}
	

	public Issue withSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public String getSubject() {
		return subject;
	}
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Issue{" +
				"id=" + id +
				", description='" + description + '\'' +
				", subject='" + subject + '\'' +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Issue issue = (Issue) o;
		
		if (getId() != issue.getId()) return false;
		if (!getDescription().equals(issue.getDescription())) return false;
		return getSubject().equals(issue.getSubject());
		
	}
	
	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + getDescription().hashCode();
		result = 31 * result + getSubject().hashCode();
		return result;
	}
}
