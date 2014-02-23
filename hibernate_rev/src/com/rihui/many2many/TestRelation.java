package com.rihui.many2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestRelation {

	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		configuration.configure("com/rihui/many2many/hibernate.cfg.xml");
		configuration.addClass(Course.class);
		configuration.addClass(Student.class);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Test
	public void saveStudentAndCourse(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Student student1 = new Student("张三");
		Student student2 = new Student("李四");
		
		Course course1 = new Course("语文");
		Course course2 = new Course("数学");
		
		student1.getCourses().add(course1);
		student1.getCourses().add(course2);
		student2.getCourses().add(course1);
		student2.getCourses().add(course2);
		
		course1.getStudents().add(student1);
		course1.getStudents().add(student2);
		course2.getStudents().add(student1);
		course2.getStudents().add(student2);
		
		session.save(course1);
		session.save(course2);
		session.save(student1);
		session.save(student2);
		
		tx.commit();
		session.close();
		
	}
	
	/**
	 * 解除1号学生和1号课程之间的关联关系
	 */
	@Test
	public void removeRelationBetweenStudentAndCourse(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		
		Student student = (Student) session.get(Student.class, 1);
		Course course = (Course) session.get(Course.class, 1);
		
//		course.getStudents().remove(student);
		
		student.getCourses().remove(course);
		
		tx.commit();
		session.close();
	}
	
	/**
	 * 解除1号学生和2号课程之间的关系，
	 * 并建立1号学生和1号课程之间的关系
	 */
	@Test
	public void removeRelationAndRebuild(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Student s1 = (Student) session.get(Student.class, 1);
		Course c1 = (Course) session.get(Course.class, 1);
		Course c2 = (Course) session.get(Course.class, 2);
		
		s1.getCourses().remove(c2);
		s1.getCourses().add(c1);
		
		tx.commit();
		session.close();
	}
	
	/**
	 * 删除课程inverse = false(抛出异常)
	 */
	@Test
	public void removeCourse(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		
		Course c2 = (Course) session.get(Course.class, 2);
		session.delete(c2);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void removeStudent(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Student s2 = (Student) session.get(Student.class, 2);
		session.delete(s2);
		
		tx.commit();
		session.close();
	}
	
}
