package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by owlowl on 23.10.16.
 */
public class ContactDataGenerator {
	@Parameter(names = "-c", description = "Group count")
	public int count;
	@Parameter(names = "-f", description = "Target file")
	public String file;
	@Parameter(names = "-d", description = "Data format")
	public String data;
	
	public static void main(String[] args) throws IOException {
		ContactDataGenerator generator = new ContactDataGenerator();
		JCommander commander = new JCommander(generator);
		try {
			commander.parse(args);
		} catch (ParameterException ex) {
			commander.usage();
			return;
		}
		generator.run();
		
	}
	
	private void run() throws IOException {
		Contacts contacts = generateContacts(count);
		switch (data) {
			case "json":
				saveAsJSON(contacts, new File(file));
				break;
			case "csv":
			default:
				save(contacts, new File(file));
		}
		
	}
	
	private void saveAsJSON(Contacts groups, File file) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		try (Writer writer = new FileWriter(file)) {
			String json = gson.toJson(groups.getList());
			writer.write(json);
		}
		
	}
	
	private void save(Contacts groups, File file) throws IOException {
		ensureFileExistense(file);
		try (Writer writer = new FileWriter(file)) {
			for (ContactData contact : groups) {
				writer.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress()));
			}
		}
	}
	
	private void ensureFileExistense(File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
	}
	
	private Contacts generateContacts(int count) {
		Contacts contacts = new Contacts();
		for (int i = 0; i < count; i++) {
			String id =String.valueOf(i);
				contacts = contacts.withAdded(new ContactData().withFirstName("GeneratedName"+id).withLastName("GenreatedLastName"+id)
						.withAddress("Test street,\nhome "+id));
		}
		return contacts;
	}
}
