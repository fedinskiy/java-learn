package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by owlowl on 13.10.16.
 */
public class Contacts extends ForwardingSet<ContactData>{
	private Set<ContactData> delegate;
	
	public Contacts(Contacts contacts) {
		this.delegate = new HashSet<ContactData>(contacts.delegate());
	}
	public Contacts() {
		this.delegate = new HashSet<ContactData>();
	}
	
	@Override
	protected Set<ContactData> delegate() {
		return delegate;
	}
	public Contacts withAdded(ContactData contact){
		Contacts contacts = new Contacts(this);
		contacts.add(contact);
		return contacts;
	}
	public Contacts without(ContactData contact){
		Contacts contacts = new Contacts(this);
		contacts.remove(contact);
		return contacts;
	}
	public List<ContactData> getList() {
		List<ContactData> retval = new ArrayList<ContactData>();
		retval.addAll(delegate);
		return retval;
	}
}
