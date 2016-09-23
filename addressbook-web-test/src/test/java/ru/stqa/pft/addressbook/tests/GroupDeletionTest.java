package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupDeletionTest extends AddressBookTest  {
	@Test
	public void deleteGroup()
	{
		app.getNavigation().gotoGroupPage();
		app.getGroupHelper().selectGroup();
		app.getGroupHelper().deleteChosenGroups();
	}
}
