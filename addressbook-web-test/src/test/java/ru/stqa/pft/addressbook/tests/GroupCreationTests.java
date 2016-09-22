package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests  extends AddressBookTest {

	@Test
	public void testGroupCreation() {
		app.getNavigation().gotoGroupPage(GroupCreationTests.this);
		app.getGroupHelper().initGroup(GroupCreationTests.this);
		app.getGroupHelper().fillGroupForm(new GroupData("TestGroupName", "TestGroupHeader", "TestGroupFooter"), GroupCreationTests.this);
		app.getGroupHelper().submitGroupCreation(GroupCreationTests.this);
		app.getGroupHelper().returnToGroupPage(GroupCreationTests.this);
	}
	
}
