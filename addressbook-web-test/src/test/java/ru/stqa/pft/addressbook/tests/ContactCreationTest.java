package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTest extends AddressBookTest{
    
    @Test
    public void createContact() {
        app.getNavigation().openContacts();
        app.getContactHelper().createContact(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001","TestGroupName"));
        app.getNavigation().openContacts();
    }
    
}
