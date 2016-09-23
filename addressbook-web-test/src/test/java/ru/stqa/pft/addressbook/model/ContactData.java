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
	
	public ContactData(String firstName, String lastName, String address, String mobilePhone, String email, String birth, String anniversary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.birth = new ThreePartDate(birth);
		this.anniversary =  new ThreePartDate(anniversary);
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
	
	public class ThreePartDate
	{
		private int day;
		private int month;
		private String year;
		
		public ThreePartDate(String date ){
			this (date.split(Pattern.quote(".")));
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
