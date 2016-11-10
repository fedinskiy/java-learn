package ru.stqa.pft.mantis.tests.model;

import java.math.BigInteger;

/**
 * Created by owlowl on 09.11.16.
 */
public class Project {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Project withId(int id) {
		this.id = id;
		return this;
	}
	public Project withId(BigInteger id) {
		return withId(id.intValue());
	}
	
	public Project withName(String name) {
		this.name = name;
		return this;
	}
}
