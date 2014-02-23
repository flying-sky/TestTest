package com.rihui.session;

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
	public void saveCustomer(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setDescription("bbb");
		customer.setName("b");
		session.save(customer);
		
		transaction.commit();
		session.close();
	}
	
	//session的一级缓存
	@Test
	public void getCustomer(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Customer c1 = (Customer) session.get(Customer.class, 1);
		Customer c2 = (Customer) session.get(Customer.class, 2);
		Customer c3 = (Customer) session.get(Customer.class, 1);
		
		System.out.println(c1 == c2);
		System.out.println(c1 == c3);
		
		transaction.commit();
		session.close();
		
	}
	
	//内存快照
	@Test
	public void testKuaiZhao(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//查询出来是对象放在一级缓存以及将属性放在快照区中
		Customer customer = (Customer) session.get(Customer.class, 1);
		customer.setDescription("abc");
		//以及缓存中的数据与快照中的属性比较，不相同则发出update语句
		session.flush();
		
		//刷新缓存和提交事务
		tx.commit();
		session.close();
		
	}
	
	@Test
	public void testSaveId(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setDescription("hahaha");
		customer.setName("ha");
		
		//放入以及缓存中
		session.save(customer);
		
		@SuppressWarnings("unused")
		Customer customer2 = (Customer) session.get(Customer.class, customer.getId());
		
		customer2.setDescription("ffffffffffff");
		
		session.flush();
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testIncrement(){
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setDescription("bbb");
		customer.setName("b");
		session.save(customer);
		
		transaction.commit();
		session.close();
	}
	
}
