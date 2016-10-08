package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupModificationTest extends AddressBookTest {
	@BeforeMethod
	public void ensurePreconditions(){
		app.getNavigation().gotoGroupPage();
		if (!app.getGroupHelper().isGroupThere()) {
			app.getGroupHelper().createGroup(new GroupData("TestGroupName", null, null));
		}
	}
	 
	@Test
	public void testGroupModification() {
		int modifiedIndex;
		GroupData modified;
	
		
		List<GroupData> before = app.getGroupHelper().getGroupList();
		modifiedIndex=before.size()-1;
		
		modified=new GroupData(before.get(modifiedIndex).getId(), "t1", "t2", "t3");
		
		app.getGroupHelper().modifyGroup(modifiedIndex, modified);
		
		app.getGroupHelper().returnToGroupPage();
		
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());
		before.remove(modifiedIndex);
		before.add(modified);
		before.sort(app.getGroupHelper().getComparator());
		after.sort(app.getGroupHelper().getComparator());
		Assert.assertEquals(after, before);
	}
	

}
