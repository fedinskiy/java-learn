package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

import org.openqa.selenium.*;



public class ContactCreationTest extends AddressBookTest{
    
    @Test
    public void createContact() {
        openGroups();
        initContact();
        fillContactForm(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.13.1992", "17.09.2001"));
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
        wd.findElement(By.linkText("home")).click();
    }
    
    private void fillContactForm(ContactData contactData) {
        setFieldValue("firstname", contactData.getFirstName());
       // setFieldValue("middlename", "SN");
        setFieldValue("lastname", contactData.getLastName());
       // setFieldValue("nickname", "NicNameForTest");
       // setFieldValue("title", "TestTitle");
        //setFieldValue("company", "TestCompany");
        setFieldValue("address", contactData.getAddress());
        //setFieldValue("home", "homephone");
        setFieldValue("mobile", contactData.getMobilePhone());
        //setFieldValue("work", "workphone");
       //setFieldValue("fax", "faxphone");
        setFieldValue("email", contactData.getEmail());
        //setFieldValue("email2", "email2");
        //setFieldValue("email3", "enail3");
        //setFieldValue("homepage", "homepage");
        
        chooseInSelector(1, contactData.getBirth().getSelectorDay());
        chooseInSelector(2, contactData.getBirth().getSelectorMonth());
        setFieldValue("byear", contactData.getBirth().getYear());
        chooseInSelector(3, contactData.getAnniversary().getSelectorDay());
        chooseInSelector(4, contactData.getAnniversary().getSelectorMonth());
        setFieldValue("ayear", contactData.getAnniversary().getYear());
        
        chooseInSelector(5, 2);
        
       // setFieldValue("address2", "secondaddr");
        //setFieldValue("phone2", "secondhome");
        //setFieldValue("notes", "noteshere");
    }
    
    private void initContact() {
        wd.findElement(By.linkText("add new")).click();
    }
    
    private void openGroups() {
        wd.get("http://localhost/addressbook/group.php");
    }
    
    private void chooseInSelector(final int selectorNumber, final int optionNumber) {
	    String expression = "//div[@id='content']/form/select[" + selectorNumber + "]//option[" + optionNumber + "]";
	    if (!wd.findElement(By.xpath(expression)).isSelected()) {
            wd.findElement(By.xpath(expression)).click();
        }
    }
    
}
