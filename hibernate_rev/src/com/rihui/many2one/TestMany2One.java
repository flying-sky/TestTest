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
	 * 先保存订单，再保存客户
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
		customer.setDescription("买东西了");
		customer.setName("小花");
		
		Orders orders = new Orders();
		orders.setCustomer(customer);
		orders.setPrice(35.5);
		
		session.save(orders);
		session.save(customer);
		
		tx.commit();
		session.close();
	}
	
	/**
	 * 先保存客户再保存订单
	 *  Hibernate: select max(id) from customer
		Hibernate: select max(id) from orders
		Hibernate: insert into customer (description, name, id) values (?, ?, ?)
		Hibernate: insert into orders (price, cid, id) values (?, ?, ?)
	 */
	@Test
	public void addCustomerAndOrders(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//临时对象
		Customer customer = new Customer();
		customer.setDescription("买东西了");
		customer.setName("小花");
		
		//临时对象
		Orders orders = new Orders();
		orders.setCustomer(customer);
		orders.setPrice(35.5);
		
		//进入以及缓存，同时提升为持久化对象
		session.save(customer);
		session.save(orders);
		
		/**
		 * 设置cascade，保存对象的同时，将对象中的属性(对象中的对象)同时加载带以及缓存中(成为持久化对象)
		 */
		tx.commit();
		session.close();
	}
	
	/**
	 * 查询订单
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
