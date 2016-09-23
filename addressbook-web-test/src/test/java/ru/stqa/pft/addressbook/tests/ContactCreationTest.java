package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTest extends AddressBookTest{
    
    @Test
    public void createContact() {
        app.getNavigation().openGroups();
        app.getContactHelper().initContact();
        app.getContactHelper().fillContactForm(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001"), ContactCreationTest.this);
        app.getGroupHelper().pressButtonByXPath("//div[@id='content']/form/input[21]");
        app.getNavigation().returnToContacts();
    }
    
}
