package com.rihui.state;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestSessionMethod {
	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addClass(Customer.class);
		configuration.addClass(Orders.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Test
	public void testSave(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = (Customer) session.get(Customer.class, 2);
		
		session.evict(customer);
		
		session.update(customer);
		
		
		tx.commit();
		session.close();
	}

	/**
	 * org.hibernate.NonUniqueObjectException: 
	 * a different object with the same identifier value was already associated with 
	 * the session: [com.rihui.state.Customer#2]
	 */
	@Test
	@SuppressWarnings("unused")
	public void testUpdate(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer c1 = (Customer) session.get(Customer.class, 2);
		
		session.evict(c1);
		
		Customer c2 = (Customer) session.get(Customer.class, 2);
		
		session.update(c1);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testSaveOrUpdate(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
//		Customer customer = new Customer();
//		customer.setCid(6);
//		customer.setDescription("saveOrUpdate2");
//		customer.setName("save--update");
//		
//		session.saveOrUpdate(customer);
		
		//游离对象用update
		Customer customer = (Customer) session.get(Customer.class, 6);
		session.evict(customer);
		
		session.saveOrUpdate(customer);
		
		tx.commit();
		session.close();
	}
	
	
	
	
}
