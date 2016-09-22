package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests  extends AddressBookTest {

	@Test
	public void testGroupCreation() {
		app.gotoGroupPage(GroupCreationTests.this);
		app.initGroup(GroupCreationTests.this);
		app.fillGroupForm(new GroupData("TestGroupName", "TestGroupHeader", "TestGroupFooter"), GroupCreationTests.this);
		app.submitGroupCreation(GroupCreationTests.this);
		app.returnToGroupPage(GroupCreationTests.this);
	}
	
}
