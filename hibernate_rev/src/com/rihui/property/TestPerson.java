package com.rihui.property;

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
		sessionFactory = configuration.buildSessionFactory();
	}

	@Test
	public void testSave(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Person person = new Person();
		person.setDescription(null);
		person.setPname("e");
		session.save(person);
		
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testUpdate(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		

		Person person = (Person) session.get(Person.class, 1);
		person.setDescription("aaaaeeee");
		person.setPname("eee");
		
		transaction.commit();
		session.close();
	}
}
