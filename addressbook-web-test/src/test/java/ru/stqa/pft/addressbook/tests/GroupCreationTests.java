package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests  extends AddressBookTest {

	@Test
	public void testGroupCreation() {
		app.getNavigation().gotoGroupPage();
		int groupCountBefore=app.getGroupHelper().getGroupCount();
		app.getGroupHelper().createGroup(new GroupData("TestGroupName", null, null));
		app.getNavigation().gotoGroupPage();
		int groupCountAfter=app.getGroupHelper().getGroupCount();
		Assert.assertEquals(groupCountAfter,groupCountBefore+1);
	}
	
}
