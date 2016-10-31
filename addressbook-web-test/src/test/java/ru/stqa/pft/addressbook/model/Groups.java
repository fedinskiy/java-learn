package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.*;


/**
 * Created by owlowl on 11.10.16.
 */
@XStreamAlias("groupinfo")
public class Groups extends ForwardingSet<GroupData> {
	private Set<GroupData> delegate=new HashSet<GroupData>();
	
	public Groups(Groups groups) {
		this((Set<GroupData>) groups.delegate);
	}
	public Groups(Set<GroupData> groupSet) {
		this.delegate=new HashSet<GroupData>(groupSet);
	}
	public Groups(Collection<GroupData> groupList) {
		this.delegate=new HashSet<GroupData>(groupList);
	}
	
	public Groups() {
		this.delegate=new HashSet<GroupData>();
	}
	
	@Override
	protected Set<GroupData> delegate() {
		return delegate;
	}
	
	public List<GroupData> getList() {
		List<GroupData> retval = new ArrayList<GroupData>();
		retval.addAll(delegate);
		return retval;
	}
	
	public Groups withAdded(GroupData group){
		Groups groups = new Groups(this);
		groups.add(group);
		return groups;
	}
	public Groups without(GroupData group){
		Groups groups = new Groups(this);
		groups.remove(group);
		return groups;
	}
	
		
}
