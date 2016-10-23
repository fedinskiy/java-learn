package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by owlowl on 20.10.16.
 */
public class GroupDataGenerator {
	@Parameter(names = "-c", description = "Group count")
	public int count;
	@Parameter(names = "-f", description = "Target file")
	public String file;
	@Parameter(names = "-d", description = "Data format")
	public String data;
	
	public static void main(String[] args) throws IOException {
		GroupDataGenerator generator = new GroupDataGenerator();
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
		Groups groups = generateGroups(count);
		switch (data) {
			case "xml":
				saveAsXML(groups, new File(file));
				break;
			case "json":
				saveAsJSON(groups, new File(file));
				break;
			case "csv":
			default:
				save(groups, new File(file));
		}
		
	}
	
	private void saveAsJSON(Groups groups, File file) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		try (Writer writer = new FileWriter(file)) {
			String json = gson.toJson(groups.getList());
			writer.write(json);
		}
		
	}
	
	private void saveAsXML(Groups groups, File file) throws IOException {
		ensureFileExistense(file);
		XStream xstream = new XStream();
		xstream.processAnnotations(Groups.class);
		xstream.processAnnotations(GroupData.class);
		try (Writer writer = new FileWriter(file)) {
			String xml = xstream.toXML(groups.getList());
			writer.write(xml);
		}
	}
	
	private void save(Groups groups, File file) throws IOException {
		ensureFileExistense(file);
		try (Writer writer = new FileWriter(file)) {
			for (GroupData group : groups) {
				writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
			}
		}
	}
	
	private void ensureFileExistense(File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
	}
	
	private Groups generateGroups(int count) {
		Groups groups = new Groups();
		for (int i = 0; i < count; i++) {
			
			groups = groups.withAdded(new GroupData().withName("TestName" + String.valueOf(i)).withHeader("header" + String.valueOf(i))
					.withFooter("footer" + String.valueOf(i)));
		}
		return groups;
	}
}
