package com.rihui.identity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestCustomer {

	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addClass(Customer.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Test
	public void testId_identity(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setDescription("aaa");
		customer.setName("a");
		
		session.save(customer);
		
		tx.commit();
		session.close();
	}
	
}
