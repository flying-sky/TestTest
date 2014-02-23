package com.rihui.composite;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestPerson {

	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addClass(Person.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Test
	public void testCompositeKey(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Person person = new Person();
		person.setFirstName("ะก");
		person.setLastName("ร๛");
		person.setDescription("abcdefg");
		
		session.save(person);
		
		transaction.commit();
		session.close();
	}
	
}
