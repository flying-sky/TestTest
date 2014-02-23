package com.rihui.search;

import java.util.List;
import java.util.Random;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestSearch {

	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure("com/rihui/search/hibernate.cfg.xml");
		configuration.addClass(Customer.class);
		configuration.addClass(Orders.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Test
	public void initData(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setAge(23);
		customer.setName("小星");

		for(int i=0;i<=10;i++){
			Orders orders = new Orders();
			orders.setCustomer(customer);
			orders.setPrice(1000d+new Random().nextInt(1000));
			session.save(orders);
		}
		
		tx.commit();
		session.close();
	}
	
	/**
	 * load与get方法
	 * load延迟加载(class中属性lazy为true)
	 * get无聊lazy设置为什么都是立即加载
	 * 
	 * 代理对象才有延迟检索(load && lazy=true)
	 * 非代理对象(get || (load && lazy=false))
	 */
	@Test
	public void testLoad(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Customer customer = (Customer) session.load(Customer.class, 1);
		System.out.println("-----------------");
		System.out.println(customer.getId());
		System.out.println(customer.getName());
		
		tx.commit();
		session.close();
	}
	
	/**
	 * Hibernate.isInitialized(customer)//集合或对象是否已经初始化
	 * Hibernate.initialize(customer);//强迫集合或对象进行初始化
	 */
	@Test
	public void initialMethod(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Customer customer = (Customer) session.load(Customer.class, 1);
		if(!Hibernate.isInitialized(customer)){
			Hibernate.initialize(customer);
		}
		
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void query(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("from Customer");
		List<Customer> customers = query.list();
		
		System.out.println(customers.size());
		tx.commit();
		session.close();
	}
	
}
