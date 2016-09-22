package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTest extends AddressBookTest{
    
    @Test
    public void createContact() {
        app.openGroups(ContactCreationTest.this);
        app.initContact(ContactCreationTest.this);
        app.fillContactForm(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.13.1992", "17.09.2001"), ContactCreationTest.this);
        app.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
        app.returnToContacts(ContactCreationTest.this);
    }
    
}
