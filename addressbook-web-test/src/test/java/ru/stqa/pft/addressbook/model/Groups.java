package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by owlowl on 11.10.16.
 */
public class Groups extends ForwardingSet<GroupData> {
	
	private Set<GroupData> delegate;
	
	public Groups(Groups groups) {
		this.delegate=new HashSet<GroupData>(groups.delegate);
	}
	
	@Override
	protected Set<GroupData> delegate() {
		return delegate;
	}
	
	public Groups withAdded(GroupData group){
		Groups groups = new Groups(this);
		return groups;
	}
		
}
