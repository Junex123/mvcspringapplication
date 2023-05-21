package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeDao 
{
	private Configuration con;
	private SessionFactory factory;
	private Session session;
	private Transaction t;
														// User Login
	public boolean login(Employee emp) 
	{
		boolean flag = false;
		con = new Configuration().configure("hibernate.cfg.xml");
		factory = con.buildSessionFactory();
		session = factory.openSession();
		t = session.beginTransaction();
		List lis = session.createQuery("from Employee").list();
		Iterator it = lis.iterator();
		while(it.hasNext())
		{
			Employee obj = (Employee)it.next();
			if(obj.getEmpcode()==emp.getEmpcode() && obj.getEmpname().equals(emp.getEmpname()))
			{
				flag = true;
			}
		}
		return flag;
	}
															// Save Record
	public void saveData(Employee e) 
	{
		con = new Configuration().configure("hibernate.cfg.xml");
		factory = con.buildSessionFactory();
		session = factory.openSession();
		t = session.beginTransaction();
		session.save(e);
		t.commit();
	}
															// Display Record
	public List displayData() 
	{
		con = new Configuration().configure("hibernate.cfg.xml");
		factory = con.buildSessionFactory();
		session = factory.openSession();
		t = session.beginTransaction();
		List list = session.createQuery("from Employee").list();
		return list;
	}
															// Search Record
	public List searchData(Employee e) 
	{
		List li = new ArrayList();
		con = new Configuration().configure("hibernate.cfg.xml");
		factory = con.buildSessionFactory();
		session = factory.openSession();
		t = session.beginTransaction();
		List list = session.createQuery("from Employee").list();
		Iterator it = list.iterator();
		while(it.hasNext())
		{
			Employee emp = (Employee)it.next();
			if((e.getEmpcode()==emp.getEmpcode()) || (e.getEmpname().equals(emp.getEmpname()) || (e.getDesignation().equals(emp.getDesignation()) || (e.getEmail().equals(emp.getEmail())))))
			{
				Employee obj = new Employee();
				obj.setEmpcode(emp.getEmpcode());
				obj.setEmpname(emp.getEmpname());
				obj.setDesignation(emp.getDesignation());
				obj.setEmail(emp.getEmail());
				li.add(obj);
			}
		}
		return li;
	}
															// Update Record
	public void updateData(Employee emp) 
	{
		con = new Configuration().configure("hibernate.cfg.xml");
		factory = con.buildSessionFactory();
		session = factory.openSession();
		t = session.beginTransaction();
		Employee obj = session.get(Employee.class, emp.getEmpcode());
		obj.setEmpname(emp.getEmpname());
		obj.setDesignation(emp.getDesignation());
		obj.setEmail(emp.getEmail());
		session.update(obj);
		t.commit();
	}
															// Delete Record
	public void deleteData(Employee emp) 
	{
		con = new Configuration().configure("hibernate.cfg.xml");
		factory = con.buildSessionFactory();
		session = factory.openSession();
		t = session.beginTransaction();
		Employee obj = session.get(Employee.class, emp.getEmpcode());
		session.delete(obj);
		t.commit();
	}
}
