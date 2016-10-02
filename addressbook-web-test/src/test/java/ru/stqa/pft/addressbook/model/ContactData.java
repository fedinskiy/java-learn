package ru.stqa.pft.addressbook.model;

import java.util.regex.Pattern;

public class ContactData {
	private final String firstName;
	private final String lastName;
	private final String address;
	private final String mobilePhone;
	private final String email;
	private final ThreePartDate birth;
	private final ThreePartDate anniversary;
	private String group;
	
	public ContactData(String firstName, String lastName, String address, String mobilePhone, String email, String birth, String anniversary, String group) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobilePhone = mobilePhone;
		this.email = email;
		if (null!=birth) {
			this.birth = new ThreePartDate(birth);
		}else{
			this.birth = new ThreePartDate();
		}
		if (null!=anniversary) {
			this.anniversary =  new ThreePartDate(anniversary);
		}else{
			this.anniversary = new ThreePartDate();
		}

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
	
	public class ThreePartDate
	{
		private int day;
		private int month;
		private String year;
		
		public ThreePartDate(){
			this.day=0;
			this.month=0;
			this.year="";
		}
		public ThreePartDate(String date ){
			this(date.split(Pattern.quote(".")));
		}
		private ThreePartDate(String[] parts){
			this(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]), parts[2]);
		}
		public ThreePartDate(int _day, int _month, String _year){
			this.day=_day;
			this.month=_month;
			this.year=_year;
		}
		
		
		public int getSelectorDay() {
			return day+2;
		}
		
		public int getSelectorMonth() {
			return month+1;
		}
		
		public String getYear() {
			return year;
		}
	}
}
