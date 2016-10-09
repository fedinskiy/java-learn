package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupDeletionTest extends AddressBookTest  {
	
	@BeforeMethod
	public void ensurePreconditions(){
		app.moveTo().groupsPage();
		if (app.groups().getList().size()==0) {
			app.groups().create(new GroupData("TestGroupName", null, null));
		}
	}
	
	@Test(enabled = false)
	public void deleteGroup()
	{
		int deletedIndex;
		app.moveTo().groupsPage();
		if(app.groups().getList().size()==0){
			app.groups().create(new GroupData("TestGroupName", null, null));
		}
		List<GroupData> before = app.groups().getList();
		deletedIndex=before.size() - 1;
		
		app.groups().delete(deletedIndex);
	
			List<GroupData> after = app.groups().getList();
		Assert.assertEquals(after.size(), before.size()-1);
		before.remove(deletedIndex);
		
		Assert.assertEquals(before,after);
	}
	

}
