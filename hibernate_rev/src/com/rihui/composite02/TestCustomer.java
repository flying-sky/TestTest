package com.rihui.composite02;

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
	public void testComposition(){
	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		CustomerID customerID = new CustomerID();
		customerID.setFirstName("a");
		customerID.setLastName("bc");
		
		Customer customer = new Customer();
		customer.setId(customerID);
		customer.setDesription("aabbcc");
		
		session.save(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testCompositeGet(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		CustomerID customerID = new CustomerID();
		customerID.setFirstName("a");
		customerID.setLastName("bc");
		
		Customer customer = (Customer) session.get(Customer.class, customerID);
		System.out.println("-----------");
		
		CustomerID customerID02 = new CustomerID();
		customerID02.setFirstName("a");
		customerID02.setLastName("bc");
		Customer customer02 = (Customer) session.get(Customer.class, customerID02);
		
		tx.commit();
		session.close();
		
		
		
	}
	
	
}
