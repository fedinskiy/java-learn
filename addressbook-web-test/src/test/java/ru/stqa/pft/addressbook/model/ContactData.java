package ru.stqa.pft.addressbook.model;

import java.util.regex.Pattern;

public class ContactData {
	private String firstName;
	private String lastName;
	private String address;
	private String mobilePhone;
	private String email;
	private ThreePartDate birth;
	private ThreePartDate anniversary;
	private String group;
	private int id;
	private final int DEFAULT_ID = Integer.MAX_VALUE;
	
	public ContactData() {
		this.birth = null;
		this.anniversary = null;
	}
	
	public ContactData(String firstName, String lastName, String address, String mobilePhone, String email, String birth, String anniversary, String group) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobilePhone = mobilePhone;
		this.email = email;
		if (null != birth) {
			this.birth = new ThreePartDate(birth);
		} else {
			this.birth = new ThreePartDate();
		}
		if (null != anniversary) {
			this.anniversary = new ThreePartDate(anniversary);
		} else {
			this.anniversary = new ThreePartDate();
		}
		this.id = DEFAULT_ID;
		this.group = group;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public ThreePartDate getBirth() {
		return birth;
	}
	
	public ThreePartDate getAnniversary() {
		return anniversary;
	}
	
	public String getGroup() {
		return group;
	}
	
	public int getId() {
		return id;
	}
	
	public ContactData withId(String id) {
		this.id = Integer.parseInt(id);
		return this;
	}
	
	
	public ContactData withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public ContactData withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}
	
	public ContactData withId(int id) {
		this.id = id;
		return this;
	}
	
	public ContactData withBirth(String birth) {
		this.birth = new ThreePartDate(birth);
		return this;
	}
	
	public ContactData withAnniversary(String anniversary) {
		this.anniversary = new ThreePartDate(anniversary);
		return this;
	}
	public ContactData withGroup(String groupName) {
		this.group = groupName;
		return this;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		ContactData that = (ContactData) o;
		
		return id == that.id || id == DEFAULT_ID || that.id == DEFAULT_ID;
		
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public String toString() {
		return "ContactData{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", id=" + id +
				'}';
	}
	

	
	public class ThreePartDate {
		private int day;
		private int month;
		private String year;
		
		public ThreePartDate() {
			this.day = 0;
			this.month = 0;
			this.year = "";
		}
		
		public ThreePartDate(String date) {
			this(date.split(Pattern.quote(".")));
		}
		
		private ThreePartDate(String[] parts) {
			this(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2]);
		}
		
		public ThreePartDate(int _day, int _month, String _year) {
			this.day = _day+2;
			this.month = _month+1;
			this.year = _year;
		}
		
		
		public int getSelectorDay() {
			return day;
		}
		
		public int getSelectorMonth() {
			return month;
		}
		
		public String getYear() {
			return year;
		}
	}
	
}
