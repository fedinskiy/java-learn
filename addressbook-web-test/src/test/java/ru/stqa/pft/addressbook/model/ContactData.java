package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.common.util.impl.Log;

import javax.persistence.*;
import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ru.stqa.pft.addressbook.appamanager.HandyFunctions.STRING_SEPARATOR;
import static ru.stqa.pft.addressbook.appamanager.HandyFunctions.cleanPhone;

@Entity
@Table(name = "addressbook")
public class ContactData {
	@Expose
	@Column(name = "firstname")
	private String firstName;
	@Expose
	@Column(name = "lastname")
	private String lastName;
	@Expose
	@Column
	@Type(type = "text")
	private String address;
	
	@Transient
	private String mobilePhone;
	@Transient
	private String workPhone;
	@Transient
	private String homePhone;
	@Transient
	private String allPhones;
	@Transient
	private String allEmails;
	@Transient
	private String email;
	@Transient
	private String email2;
	@Transient
	private String email3;
	@Transient
	private String fullInfo;
	@Transient
	private ThreePartDate birth;
	@Transient
	private ThreePartDate anniversary;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "address_in_groups",
			joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private Set<GroupData> groups = new Groups();
	@Column(name = "photo")
	@Type(type = "text")
	private String photo;
	@Id
	@Column(name = "id")
	private int id;
	@Transient
	private final int DEFAULT_ID = Integer.MAX_VALUE;
	
	
	public ContactData() {
		this.birth = null;
		this.anniversary = null;
		this.id = DEFAULT_ID;
	}
	
	public ContactData(String firstName, String lastName, String address, String mobilePhone, String email, String birth, String anniversary) {
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
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address.replace("\r\n","\n");
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public String getEmail2() {
		return email2;
	}
	
	public String getEmail3() {
		return email3;
	}
	
	public String getAllEmails() {
		String retval;
		if ((null == allEmails) || allEmails.isEmpty()) {
			retval = Arrays.asList(getEmail(), getEmail2(), getEmail3())
					.stream().filter((s) -> !(null == s || s.isEmpty())).collect(Collectors.joining(STRING_SEPARATOR));
		} else {
			retval = this.allEmails;
		}
		return retval;
	}
	
	public ThreePartDate getBirth() {
		return birth;
	}
	
	public ThreePartDate getAnniversary() {
		return anniversary;
	}
	
	
	public String getMobilePhone(boolean... asPlainText) {
		if (asPlainText.length > 0) {
			return cleanPhone(mobilePhone);
		} else {
			return (null == mobilePhone) ? "" : mobilePhone;
		}
	}
	
	public String getWorkPhone(boolean... asPlainText) {
		if (asPlainText.length > 0) {
			return cleanPhone(workPhone);
		} else {
			return (null == workPhone) ? "" : workPhone;
		}
	}
	
	public String getHomePhone(boolean... asPlainText) {
		if (asPlainText.length > 0) {
			return cleanPhone(homePhone);
		} else {
			return (null == homePhone) ? "" : homePhone;
		}
	}
	
	public String getMobilePhoneWithPrefix() {
		String retval = getMobilePhone();
		return (retval.isEmpty() ? "" : "M: " + retval);
	}
	
	public String getWorkPhoneWithPrefix() {
		String retval = getWorkPhone();
		return (retval.isEmpty() ? "" : "W: " + retval);
	}
	
	public String getHomePhoneWithPrefix() {
		String retval = getHomePhone();
		return (retval.isEmpty() ? "" : "H: " + retval);
	}
	
	public String getAllPhonesWithPrefix() {
		return Arrays.asList(getHomePhoneWithPrefix(), getMobilePhoneWithPrefix(), getWorkPhoneWithPrefix())
				.stream().filter((s) -> !(null == s || s.isEmpty())).collect(Collectors.joining(STRING_SEPARATOR));
	}
	
	public String getAllPhones(boolean... asPlainText) {
		String retval;
		if ((null == allPhones) || allPhones.isEmpty()) {
			retval = Arrays.asList(getHomePhone(asPlainText), getMobilePhone(asPlainText), getWorkPhone(asPlainText))
					.stream().filter((s) -> !(null == s || s.isEmpty())).collect(Collectors.joining(STRING_SEPARATOR));
		} else {
			retval = (asPlainText.length > 0) ? cleanPhone(allPhones) : this.allPhones;
		}
		return retval;
	}

	public String getFullInfo() {
		String retval;
		if ((null == fullInfo) || fullInfo.isEmpty()) {
			retval = Arrays.asList(getFirstName() + " " + getLastName() + STRING_SEPARATOR + getAddress(),
					getAllPhonesWithPrefix(),
					getEmail(), getEmail2(), getEmail3())
					.stream().filter((s) -> !(null == s || s.isEmpty())).collect(Collectors.joining(STRING_SEPARATOR + STRING_SEPARATOR));
		} else {
			retval = this.fullInfo;
		}
		return retval;
	}
	
	
	public int getId() {
		return id;
	}
	
	public File getPhoto() {
		try{
			return new File(photo);
		}catch(Exception e){
			System.out.println(e.getMessage()+" while trying to access "+photo);
			return null;
		}
	}
	
	public ContactData withPhoto(File photo) {
		this.photo = photo.getPath();
		return this;
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
	
	public Groups getGroups() {
		return new Groups(groups);
	}
	
	public ContactData withMobilePhone(String number) {
		this.mobilePhone = number;
		return this;
	}
	
	public ContactData withWorkPhone(String number) {
		this.workPhone = number;
		return this;
	}
	
	public ContactData withHomePhone(String number) {
		this.homePhone = number;
		return this;
	}
	
	public ContactData withAllEmails(String allEmails) {
		this.allEmails = allEmails;
		return this;
	}
	
	public ContactData withAllPhones(String allphones) {
		this.allPhones = allphones;
		return this;
		
	}
	
	public ContactData withEmail(String email) {
		this.email = email;
		return this;
	}
	
	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}
	
	public ContactData withEmail3(String email3) {
		this.email3 = email3;
		return this;
	}
	
	public ContactData withFullInfo(String fullInfo) {
		this.fullInfo = fullInfo;
		return this;
	}
	
	
	public void withGroups(Groups groups) {
		this.groups = groups;
	}
	
	
	public ContactData inGroup(GroupData next) {
		this.groups.add(next);
		return this;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		ContactData that = (ContactData) o;
		
		if (getId() != that.getId()&& getId() != DEFAULT_ID && that.getId() != DEFAULT_ID) return false;
		if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
			return false;
		if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
			return false;
		return getAddress() != null ? getAddress().equals(that.getAddress()) : that.getAddress() == null;
		
	}
	
	@Override
	public int hashCode() {
		int result = getFirstName() != null ? getFirstName().hashCode() : 0;
		result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
		result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
		result = 31 * result + getId();
		return result;
	}
	
	@Override
	public String toString() {
		return "ContactData{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", address='" + address + '\'' +
				", id=" + id +
				"}, ("+this.hashCode()+") Ge\n";
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
			this.day = _day + 2;
			this.month = _month + 1;
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
		
		public String Readable() {
			return String.valueOf(day - 2) + "." + getMonth() + " " + year;
		}
		
		public String getMonth() {
			switch (this.month) {
				case 1:
					return "January";
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
				
			}
			return "";
		}
	}
	
}
