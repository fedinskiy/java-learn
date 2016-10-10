package ru.stqa.pft.addressbook.model;

public class  GroupData {
	private String name;
	private String header;
	private String footer;
	private String id=String.valueOf(Integer.MAX_VALUE);
	
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
		result = 31 * result + id.hashCode();
		return result;
	}
}
