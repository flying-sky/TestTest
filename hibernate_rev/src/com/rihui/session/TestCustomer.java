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
	
	//session��һ������
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
	
	//�ڴ����
	@Test
	public void testKuaiZhao(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//��ѯ�����Ƕ������һ�������Լ������Է��ڿ�������
		Customer customer = (Customer) session.get(Customer.class, 1);
		customer.setDescription("abc");
		//�Լ������е�����������е����ԱȽϣ�����ͬ�򷢳�update���
		session.flush();
		
		//ˢ�»�����ύ����
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
		
		//�����Լ�������
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
