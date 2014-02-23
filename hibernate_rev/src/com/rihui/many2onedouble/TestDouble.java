package com.rihui.many2onedouble;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestDouble {

	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addClass(Customer.class);
		configuration.addClass(Orders.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	/**
	 * 双向关联，保存客户和订单
	 */
	@Test
	public void saveCustomerAndOrders(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Orders orders = new Orders();
		orders.setPrice(34.0);
		Set<Orders> sets = new HashSet<Orders>();
		sets.add(orders);
		
		Customer customer = new Customer();
		customer.setDescription("买东西啦");
		customer.setOrders(sets);
		
		session.save(orders);
		session.save(customer);
		
		tx.commit();
		session.close();
	}
	
	/**
	 * 保存客户不保存订单
	 */
	@Test
	public void saveCustomerButNoOrders(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//临时对象
		Orders orders = new Orders();
		orders.setPrice(34.0);
		Set<Orders> sets = new HashSet<Orders>();
		sets.add(orders);
		
		//临时对象
		Customer customer = new Customer();
		customer.setDescription("买东西啦");
		customer.setOrders(sets);
		
//		session.save(orders);
		//设置了cascade一起进入session的以及缓存中，并转化为持久化对象
		session.save(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void getCustomerAndOrders(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer.getCid() + "   " + customer.getName() + "    " + customer.getDescription());

		for(Orders orders : customer.getOrders()){
			System.out.println(orders.getId() + "     " + orders.getPrice());
		}
		
		tx.commit();
		session.close();
	}
	
	/**
	 * 对象导航
	 */
	@Test
	public void objectNav(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Set<Orders> oSet = new HashSet<Orders>();
		
		Customer customer = new Customer();
		customer.setDescription("买东西了!!!");
		customer.setName("小黑");
		
		Orders o1 = new Orders();
		o1.setPrice(23.0);

		Orders o2 = new Orders();
		o2.setPrice(24.0);
		
		Orders o3 = new Orders();
		o3.setPrice(25.0);
		
		oSet.add(o2);
		oSet.add(o3);
		
		customer.setOrders(oSet);
		
		o1.setCustomer(customer);
	
		session.save(o1);
//		session.save(customer);
//		session.save(o2);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void changeOrderRelation(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = (Customer) session.get(Customer.class, 1);
		
		Orders orders = (Orders) session.get(Orders.class, 7);
		
		customer.getOrders().add(orders);
		
		orders.setCustomer(customer);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void removeOrders(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Orders orders = (Orders) session.get(Orders.class, 4);
//		orders.setCustomer(null);
		
		Customer customer = (Customer) session.get(Customer.class, 2);
		customer.getOrders().remove(orders);
		tx.commit();
		session.close();
	}
	
	@Test
	public void deleteCustomerAndOrders(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = (Customer) session.get(Customer.class, 1);
		session.delete(customer);
		
		tx.commit();
		session.close();
	}
	
}
