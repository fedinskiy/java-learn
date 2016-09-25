package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests  extends AddressBookTest {

	@Test
	public void testGroupCreation() {
		app.getNavigation().gotoGroupPage();
		app.getGroupHelper().initGroup();
		app.getGroupHelper().fillGroupForm(new GroupData("TestGroupName", null, null));
		app.getGroupHelper().submitGroupCreation();
		app.getGroupHelper().returnToGroupPage();
	}
	
}
