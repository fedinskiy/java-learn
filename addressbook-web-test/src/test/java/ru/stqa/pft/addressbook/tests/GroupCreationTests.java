package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends AddressBookTest {
	
	@Test
	public void testGroupCreation() {
		GroupData newGroup;
		int maxId;
		app.getNavigation().gotoGroupPage();
		List<GroupData> before = app.getGroupHelper().getGroupList();
		newGroup=new GroupData("TestGroupName", null, null);
		app.getGroupHelper().createGroup(newGroup);
		app.getNavigation().gotoGroupPage();
		List<GroupData> after = app.getGroupHelper().getGroupList();
		
		Assert.assertEquals(after.size(), before.size() + 1);
		
		maxId=0;
		for(GroupData group:after){
			if(maxId<group.getIdNumber()){maxId=group.getIdNumber();}
		}
		newGroup.setId(maxId);
		before.add(newGroup);
		Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
	}
	
}
