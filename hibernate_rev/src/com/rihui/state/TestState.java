package com.rihui.state;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestState {

	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addClass(Customer.class);
		configuration.addClass(Orders.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Test
	public void testFlush(){
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Customer customer = (Customer) session.get(Customer.class, 2);
		/**
		 * �����棬������ջ���
		 */
		session.flush();
		
		Customer customer2 = (Customer) session.get(Customer.class, 2);
		
		/**
		 * ��ջ���
		 */
		session.clear();
		
		Customer customer3 = (Customer) session.get(Customer.class, 2);
		
		transaction.commit();
		session.close();
	}
	
	/**
	 * refresh
	 * �����ݿ��е�����ͬ����������
	 */
	@Test
	public void testRefresh(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = (Customer) session.get(Customer.class, 2);
//		customer.setName("xxx");
		
		session.refresh(customer);
		
		tx.commit();
		session.close();
	}
	
	/**
	 * ������ģʽ
	 */
	@Test
	public void testFlushMode(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.setFlushMode(FlushMode.AUTO);
		
		Customer customer = new Customer();
		customer.setDescription("��ð�");
		customer.setName("����");
		
		session.save(customer);
		
		tx.commit();
		session.close();
	}

	/**
	 * ��������
	 */
	@Test
	public void testBatch(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.setFlushMode(FlushMode.NEVER);
		
		for(int i=0;i<1000000;i++){
			Customer customer = new Customer();
			customer.setName("a " + i);
			session.save(customer);
			if(i % 1000 == 0){
				session.flush();
				/**
				 * ��ջ��棬������ڴ����
				 */
				session.clear();
			}
		}
		
		session.flush();
		
		tx.commit();
		session.close();
	}
	
	@Test @SuppressWarnings("unused")
	public void testEvict(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer1 = (Customer) session.get(Customer.class, 2);
		session.evict(customer1);
		Customer customer2 = (Customer) session.get(Customer.class, 2);
		
		tx.commit();
		session.close();
	}
}
