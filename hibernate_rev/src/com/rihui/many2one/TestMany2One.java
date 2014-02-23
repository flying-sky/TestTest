package com.rihui.many2one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestMany2One {

	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addClass(Customer.class);
		configuration.addClass(Orders.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	/**
	 * �ȱ��涩�����ٱ���ͻ�
	 *  Hibernate: select max(id) from orders
		Hibernate: select max(id) from customer
		Hibernate: insert into orders (price, cid, id) values (?, ?, ?)
		Hibernate: insert into customer (description, name, id) values (?, ?, ?)
		Hibernate: update orders set price=?, cid=? where id=?
	 */
	@Test
	public void addOrdersAndCustomer(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setDescription("������");
		customer.setName("С��");
		
		Orders orders = new Orders();
		orders.setCustomer(customer);
		orders.setPrice(35.5);
		
		session.save(orders);
		session.save(customer);
		
		tx.commit();
		session.close();
	}
	
	/**
	 * �ȱ���ͻ��ٱ��涩��
	 *  Hibernate: select max(id) from customer
		Hibernate: select max(id) from orders
		Hibernate: insert into customer (description, name, id) values (?, ?, ?)
		Hibernate: insert into orders (price, cid, id) values (?, ?, ?)
	 */
	@Test
	public void addCustomerAndOrders(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//��ʱ����
		Customer customer = new Customer();
		customer.setDescription("������");
		customer.setName("С��");
		
		//��ʱ����
		Orders orders = new Orders();
		orders.setCustomer(customer);
		orders.setPrice(35.5);
		
		//�����Լ����棬ͬʱ����Ϊ�־û�����
		session.save(customer);
		session.save(orders);
		
		/**
		 * ����cascade����������ͬʱ���������е�����(�����еĶ���)ͬʱ���ش��Լ�������(��Ϊ�־û�����)
		 */
		tx.commit();
		session.close();
	}
	
	/**
	 * ��ѯ����
	 */
	@Test
	public void getOrders(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Orders orders = (Orders) session.get(Orders.class, 1);
		System.out.println(orders.getId() + " " + orders.getPrice() + " " + orders.getCustomer().getName());
		
		
		transaction.commit();
		session.close();
	}
	
}
