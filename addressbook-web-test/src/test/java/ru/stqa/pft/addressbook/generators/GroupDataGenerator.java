package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by owlowl on 20.10.16.
 */
public class GroupDataGenerator {
	public static void main(String[] args) throws IOException {
		int count = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		
		Groups groups = generateGroups(count);
		save(groups, file);
	}
	
	private static void save(Groups groups, File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		Writer writer = new FileWriter(file);
		for (GroupData group : groups) {
			writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
		}
		writer.close();
		
	}
	
	private static Groups generateGroups(int count) {
		Groups groups = new Groups();
		for (int i = 0; i < count; i++) {
			
			groups = groups.withAdded(new GroupData().withName("TestName" + String.valueOf(i)).withHeader("header" + String.valueOf(i))
					.withFooter("footer" + String.valueOf(i)));
		}
		return groups;
	}
}
