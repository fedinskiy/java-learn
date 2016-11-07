package ru.stqa.pft.mantis.tests.model;

/**
 * Created by owlowl on 06.11.16.
 */
public class MailMessage {
	private String to;
	
	public String getTo() {
		return to;
	}
	
	public String getContent() {
		return content;
	}
	
	private String content;
	public MailMessage(String to, String content){
		this.to=to;
		this.content=content ;
	}
}
