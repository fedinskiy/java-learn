package ru.stqa.pft.addressbook.appamanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.*;

import static ru.stqa.pft.addressbook.appamanager.HandyFunctions.setFieldValue;

/**
 * Created by owlowl on 22.09.16.
 */
public class GroupHelper extends BaseHelper {
	private Groups groupCache;
	private static final Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getIdNumber(), g2.getIdNumber());
	
	public Comparator<? super GroupData> getComparator() {
		return byId;
	}
	
	public GroupHelper(RemoteWebDriver wd) {
		super(wd);
	}
	
	public void openGroup() {
		pressButton("edit");
	}
	
	private void selectById(int idNumber) {
		wd.findElement(By.cssSelector("input[value='" + idNumber + "']")).click();
	}
	
	public void returnToGroupPage() {
		wd.findElement(By.linkText("group page")).click();
	}
	
	public void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}
	
	public void fillForm(GroupData groupData) {
		setFieldValue("group_name", groupData.getName(), wd);
		setFieldValue("group_header", groupData.getHeader(), wd);
		setFieldValue("group_footer", groupData.getFooter(), wd);
	}
	
	public void init() {
		wd.findElement(By.name("new")).click();
	}
	
	public void saveChanges() {
		this.pressButton("update");
	}
	
	
	public void deleteChosen() {
		this.pressButton("delete");
	}
	
	
	public void create(GroupData groupData) {
		init();
		fillForm(groupData);
		submitGroupCreation();
		groupCache = null;
		returnToGroupPage();
	}
	
	public Groups getSet(Boolean resetCache) {
		if (!(resetCache || null == groupCache)) {
			return new Groups(groupCache);
		}
		groupCache = new Groups();
		List<WebElement> pageElements = wd.findElements(By.cssSelector("span.group"));
		for (WebElement we : pageElements) {
			String name = we.getText();
			String id = we.findElement(By.tagName("input")).getAttribute("value");
			groupCache.add(new GroupData().withId(id).withName(name));
		}
		return new Groups(groupCache);
	}
	
	public Groups getSet() {
		return getSet(false);
	}
	
	public void modify(GroupData modified) {
		selectById(modified.getIdNumber());
		openGroup();
		fillForm(modified);
		groupCache = null;
		saveChanges();
	}
	
	public void delete(GroupData toDelete) {
		selectById(toDelete.getIdNumber());
		deleteChosen();
		groupCache = null;
		returnToGroupPage();
	}
	
	public int getCount() {
		return wd.findElements(By.name("selected[]")).size();
	}
	
	public Groups loadFromDefault() throws IOException {
		File source = new File("src/test/resources/groups.csv");
		if (!source.exists()) {
			throw new FileNotFoundException("Не найден файл " + source.getAbsolutePath());
		}
		return load(source);
	}
	
	public Groups loadFromDefaultXML() throws IOException {
		File source = new File("src/test/resources/groups.xml");
		if (!source.exists()) {
			throw new FileNotFoundException("Не найден файл " + source.getAbsolutePath());
		}
		return loadXML(source);
	}
	
	public Groups loadFromDefaultJSON() throws IOException {
		File source = new File("src/test/resources/groups.json");
		if (!source.exists()) {
			throw new FileNotFoundException("Не найден файл " + source.getAbsolutePath());
		}
		return loadJSON(source);
	}
	
	private Groups loadJSON(File source) throws IOException {
		Groups groups;
		String json = "";
		Gson gson = new Gson();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(source))) {
			String line = reader.readLine();
			while (null != line) {
				json += line;
				line = reader.readLine();
			}
			groups = gson.fromJson(json, Groups.class);
			return groups;
		}
	}
	
	private Groups loadXML(File file) throws IOException {
		Groups groups;
		String xml = "";
		XStream xstream = new XStream();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = reader.readLine();
			while (null != line) {
				xml += line;
				line = reader.readLine();
			}
			xstream.processAnnotations(Groups.class);
			xstream.processAnnotations(GroupData.class);
			groups = new Groups((List<GroupData>) xstream.fromXML(xml));
			return groups;
		}
	}
	
	private Groups load(File file) throws IOException {
		String line;
		String[] fromFile;
		
		Groups groups = new Groups();
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			line = reader.readLine();
			while (null != line) {
				fromFile = line.split(";");
				groups = groups.withAdded(new GroupData().withName(fromFile[0]).withHeader(fromFile[1]).withFooter(fromFile[2]));
				line = reader.readLine();
			}
			return groups;
		}
	}
	
}
