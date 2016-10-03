package ru.stqa.pft.addressbook.model;

public class  GroupData {
	private final String name;
	private final String header;
	private final String footer;
	private  String id;
	
	public GroupData(String name, String header, String footer) {
		this.name = name;
		this.header = header;
		this.footer = footer;
		this.id = "0";
	}
	public GroupData(String id,String name, String header, String footer) {
		this.name = name;
		this.header = header;
		this.footer = footer;
		this.id = id;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		GroupData groupData = (GroupData) o;
		
		if (name != null ? !name.equals(groupData.name) : groupData.name != null) return false;
		return id != null ? id.equals(groupData.id) : groupData.id == null;
		
	}
	
	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (id != null ? id.hashCode() : 0);
		return result;
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
	
	public void setId(int id) {
		this.id = String.valueOf(id);
	}
}
