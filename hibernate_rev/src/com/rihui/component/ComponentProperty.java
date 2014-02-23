package com.rihui.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class ComponentProperty {

	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addClass(Customer.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Test
	public void saveCustomer(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Address home_address = new Address("广东省", "广州市", "白云街", "123456789");
		Address com_address = new Address("广东省", "深圳市", "xx街", "xx3456789");
		
		Customer customer = new Customer();
		customer.setName("小爱");
		customer.setComAddr(com_address);
		customer.setHomeAddr(home_address);
		
		session.save(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	@SuppressWarnings("unused")
	public void testGet(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = (Customer) session.get(Customer.class, 1);
//		customer.setComAddr(null);
		
		Address comAddress = customer.getComAddr();
		if(customer.getComAddr() == null){
			comAddress = new Address("1", "2", "3", "4");
			customer.setComAddr(comAddress);
		}
		
		
		
		tx.commit();
		session.close();
	}
}
