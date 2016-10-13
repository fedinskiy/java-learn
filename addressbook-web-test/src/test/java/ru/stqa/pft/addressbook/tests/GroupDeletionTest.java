package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupDeletionTest extends AddressBookTest  {
	
	@BeforeMethod
	public void ensurePreconditions(){
		app.moveTo().groupsPage();
		if (app.groups().getSet().size()==0) {
			app.groups().create(new GroupData().withId("TestGroupName"));
		}
	}
	
	@Test
	public void deleteGroup()
	{
		ensurePreconditions();
		Groups before = app.groups().getSet();
		GroupData toDelete=before.iterator().next();
		
		app.groups().delete(toDelete);
		
		Groups after = app.groups().getSet();
		assertEquals(after.size(), before.size()-1);
		assertThat(after,equalTo(before.without(toDelete)));
	}
	

}
