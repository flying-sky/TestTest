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
		customer.setName("С��");

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
	 * load��get����
	 * load�ӳټ���(class������lazyΪtrue)
	 * get����lazy����Ϊʲô������������
	 * 
	 * �����������ӳټ���(load && lazy=true)
	 * �Ǵ������(get || (load && lazy=false))
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
	 * Hibernate.isInitialized(customer)//���ϻ�����Ƿ��Ѿ���ʼ��
	 * Hibernate.initialize(customer);//ǿ�ȼ��ϻ������г�ʼ��
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
