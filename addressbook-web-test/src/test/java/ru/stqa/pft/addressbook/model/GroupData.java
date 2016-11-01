package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name="group_list")
public class  GroupData {
	@Expose
	@Column(name="group_name")
	
	private String name;
	@Expose
	@Column(name="group_header")
	@Type(type="text")
	private String header;
	@Expose
	@Column(name="group_footer")
	@Type(type="text")
	private String footer;
	@ManyToMany(mappedBy = "groups")
	private Set<ContactData> contacts= new Contacts();
	
	@Id
	@Column(name="group_id")
	@Type(type="integer")
	@XStreamOmitField
	private int id=Integer.MAX_VALUE;
	
	public GroupData(String name, String header, String footer) {
		this.name = name;
		this.header = header;
		this.footer = footer;
		id=Integer.MAX_VALUE;
	}
	public GroupData() {
		this.name = "";
		this.header = "";
		this.footer = "";
		id=Integer.MAX_VALUE;
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
		return String.valueOf(id);
	}
	
	public int getIdNumber() {
		return this.id;
	}
	
	public Contacts getContacts() {
		return new Contacts(contacts);
	}
	public void setId(int id) {
		this.id =id;
	}
	
	public GroupData withId(int id) {
		this.id = id;
		return this;
	}
	public GroupData withId(String id) {
		this.id = Integer.parseInt(id);
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
		
		if (id != groupData.id) return false;
		return name.equals(groupData.name);
		
	}
	
	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + id;
		return result;
	}
}
