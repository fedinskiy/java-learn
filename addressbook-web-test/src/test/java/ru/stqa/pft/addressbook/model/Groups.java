package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
	public Groups(List<GroupData> groupList) {
		this.delegate=new HashSet<GroupData>();
		this.delegate.addAll(groupList);
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
