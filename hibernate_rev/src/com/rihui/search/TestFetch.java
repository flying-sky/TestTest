package com.rihui.search;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestFetch {

	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure("com/rihui/search/hibernate.cfg.xml");
		configuration.addClass(Customer.class);
		configuration.addClass(Orders.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	/**
	 * 迫切左外连接(关联检索)
	 * fetch="join" lazy="true"
	 * fetch="join" lazy="false"
	 * fetch="join" lazy="extra"
	 * 以上，全部立即检索
	 * Hibernate: select customer0_.id as id0_1_, customer0_.age as age0_1_, customer0_.name as name0_1_, orders1_.cid as cid0_3_, orders1_.id as id3_, orders1_.id as id1_0_, orders1_.price as price1_0_, orders1_.cid as cid1_0_ from customer customer0_ 
	 * left outer join orders orders1_ on customer0_.id=orders1_.cid where customer0_.id=?
	 */
	@Test
	public void test01(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	
		Customer customer = (Customer) session.load(Customer.class, 1);
		
		System.out.println("--------------------------");
		
		Set<Orders> orders = customer.getOrders();
		
		for(Orders o : orders){
			System.out.println(o.getId() + "    " + o.getPrice());
		}
		
		tx.commit();
		session.close();
	}
	
	/**
	 * query接口方法可以延时检索
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testQuery(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	
		Query query = session.createQuery("from Customer");
		List<Customer> customers = query.list();
		
		
		for(Customer customer : customers){
			System.out.println(customer.getId() + "   " + customer.getName() + "   "+customer.getOrders().size());
		}
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void test(){
		
	}
	
	
	
}
