package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("group")
public class  GroupData {
	@Expose
	private String name;
	@Expose
	private String header;
	@Expose
	private String footer;
	
	@XStreamOmitField
	private String id=String.valueOf(Integer.MAX_VALUE);
	
	public GroupData(String name, String header, String footer) {
		this.name = name;
		this.header = header;
		this.footer = footer;
		id=String.valueOf(Integer.MAX_VALUE);
	}
	public GroupData() {
		this.name = "";
		this.header = "";
		this.footer = "";
		id=String.valueOf(Integer.MAX_VALUE);
	}
	
	@Override
	public String toString() {
		return "GroupData{" +
				"name='" + name + '\'' +
				", id='" + id + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}
	
	public String getHeader() {
		return header;
	}
	
	public String getFooter() {
		return footer;
	}
	
	public String getId() {
		return id;
	}
	
	public int getIdNumber() {
		return Integer.parseInt(this.id);
	}
	
	public GroupData withId(int id) {
		this.id = String.valueOf(id);
		return this;
	}
	public GroupData withId(String id) {
		this.id = id;
		return this;
	}
	public GroupData withName(String name) {
		this.name = name;
		return this;
	}
	
	public GroupData withHeader(String header) {
		this.header = header;
		return this;
	}
	
	public GroupData withFooter(String footer) {
		this.footer = footer;
		return this;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		GroupData groupData = (GroupData) o;
		
		if (!name.equals(groupData.name)) return false;
		return id.equals(groupData.id);
		
	}
	
	@Override
	public int hashCode() {
		int result = name.hashCode();
		if(null==id){
			id=String.valueOf(Integer.MAX_VALUE);
		}
		result = 31 * result + id.hashCode();
		return result;
	}
}
