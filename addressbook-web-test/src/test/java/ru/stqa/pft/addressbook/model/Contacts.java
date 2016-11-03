package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

/**
 * Created by owlowl on 13.10.16.
 */
public class Contacts extends ForwardingSet<ContactData> {
	private Set<ContactData> delegate;
	
	public Contacts(Contacts contacts) {
		this.delegate = new HashSet<ContactData>(contacts.delegate());
	}
	
	public Contacts() {
		this.delegate = new HashSet<ContactData>();
	}
	
	public Contacts(Collection<ContactData> contactList) {
		this.delegate = new HashSet<ContactData>(contactList);
	}
	
	@Override
	protected Set<ContactData> delegate() {
		return delegate;
	}
	
	public Contacts withAdded(ContactData contact) {
		Contacts contacts = new Contacts(this);
		contacts.add(contact);
		return contacts;
	}
	
	public Contacts without(ContactData contact) {
		Contacts contacts = new Contacts(this);
		contacts.remove(contact);
		return contacts;
	}
	
	public List<ContactData> getList() {
		List<ContactData> retval = new ArrayList<ContactData>();
		retval.addAll(delegate);
		return retval;
	}
	public int getCount(){
		return this.size();
	}
	
	public ContactData getbyID(final int id) {
		for (ContactData contact:this.delegate){
			if(contact.getId()==id){
				return contact;
			}
		}
		return null;
	}
}
