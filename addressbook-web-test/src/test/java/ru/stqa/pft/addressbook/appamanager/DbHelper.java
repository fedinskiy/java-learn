package ru.stqa.pft.addressbook.appamanager;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

/**
 * Created by owlowl on 31.10.16.
 */
public class DbHelper {
	private final SessionFactory sessionFactory;
	
	public DbHelper() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

	}
	public Groups groups(){
		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<GroupData> result = session.createQuery("from GroupData").list();
		
		session.getTransaction().commit();
		session.close();
		return new Groups(result);
		
	}
	public Contacts contacts(){
		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<ContactData> result = session.createQuery("from ContactData where deprecated='0000-00-00'").list();
		
		session.getTransaction().commit();
		session.close();
		return new Contacts(result);
	}
}
