package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.List;

/**
 * Created by owlowl on 20.10.16.
 */
public class GroupDataGenerator {
	@Parameter(names = "-c", description = "Group count")
	public int count;
	@Parameter(names = "-f", description = "Target file")
	public String file;
	
	public static void main(String[] args) throws IOException {
		GroupDataGenerator generator = new GroupDataGenerator();
		JCommander commander= new JCommander(generator);
		try{
			commander.parse(args);
		}catch(ParameterException ex){
			commander.usage();
			return;
		}
		generator.run();
		
	}
	
	private void run() throws IOException {
		Groups groups = generateGroups(count);
		save(groups, new File(file));
	}
	
	private void save(Groups groups, File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		Writer writer = new FileWriter(file);
		for (GroupData group : groups) {
			writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
		}
		writer.close();
		
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
