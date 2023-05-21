package com.galaxy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Employee;
import model.EmployeeDao;
import model.User;
@Controller
public class ControllerDemo 
{
	private ApplicationContext conn;
															// Goto login Page
	@RequestMapping("/login")
	public String view1(Model m) 
	{
		conn = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Employee obj = conn.getBean("info", Employee.class);
		m.addAttribute("bean", obj);
		return "login";
	}
															// User Login
	@RequestMapping("/sign")
	public String login(@ModelAttribute("bean") Employee emp, HttpServletRequest request)
	{
		conn = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeDao obj = conn.getBean("dao", EmployeeDao.class);
		boolean flag = obj.login(emp);
		if(flag)
		{
			User usr = conn.getBean("ur", User.class);
			usr.setEmpcode(emp.getEmpcode());
			usr.setEmpname(emp.getEmpname());
			usr.setFlag(1);
			HttpSession session = request.getSession();
			session.setAttribute("data", usr);
			return "home";
		}
		else
		{
			return "redirect:index.jsp";
		}
	}
															// Goto Register Page
	@RequestMapping("/register")
	public String view2(Model m)
	{
		conn = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Employee emp = conn.getBean("info", Employee.class);
		m.addAttribute("bean", emp);
		return "register";
	}
															// Save Record
	@RequestMapping("/store")
	public String view3(@ModelAttribute("bean") Employee e, Model m)
	{
		conn = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeDao obj = conn.getBean("dao", EmployeeDao.class);
		obj.saveData(e);
		m.addAttribute("msg", "Record insert successfully...");
		return "register";
	}
															// Display Record
	@RequestMapping("/display")
	public String view4(Model m, HttpServletRequest request)
	{
		conn = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		HttpSession session = request.getSession();
		User use = (User)session.getAttribute("data");
		if(use.getFlag()==1)
		{
		EmployeeDao obj = conn.getBean("dao", EmployeeDao.class);
		List list = obj.displayData();
		if(!list.isEmpty())
		{
		m.addAttribute("data", list);
		}
		else
		{
			m.addAttribute("msg" ,"Oops! Data not found");
		}
		return "display";
		}
		else
		{
			m.addAttribute("msg", "Please login first");
			return "redirect:index.jsp";
		}
	}
																// Goto Search Page
	@RequestMapping("/find")
	public String view5(Model m)
	{
		conn = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Employee emp = conn.getBean("info", Employee.class);
		m.addAttribute("bean", emp);
		return "search";
	}
																// Search Record
	@RequestMapping("/search")
	public String view5(@ModelAttribute("bean") Employee e, Model m, HttpServletRequest request)
	{
		conn = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		HttpSession session = request.getSession();
		User use = (User)session.getAttribute("data");
		if(use.getFlag()==1)
		{
		EmployeeDao obj = conn.getBean("dao", EmployeeDao.class);
		List list = obj.searchData(e);
		if(!list.isEmpty())
		{
			m.addAttribute("li", list);
		}
		else
		{
			m.addAttribute("msg", "Oops! record not found");
		}
		return "search";
		}
		else
		{
			m.addAttribute("msg", "Please login first");
			return "redirect:index.jsp";
		}
	}
																// Update Record
	@RequestMapping("/update")
	public String view6(@ModelAttribute("bean") Employee emp, Model m)
	{
		conn = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeDao obj = conn.getBean("dao", EmployeeDao.class);
		obj.updateData(emp);
		m.addAttribute("msg", "Record update successfully...");
		return "search";
	}
																// Delete Record
	@RequestMapping("/delete")
	public String view7(@ModelAttribute("bean") Employee emp, Model m)
	{
		conn = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeDao obj = conn.getBean("dao", EmployeeDao.class);
		obj.deleteData(emp);
		m.addAttribute("msg", "Rcord delete successfully...");
		return "search";
	}
															// Logout
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User obj = (User)session.getAttribute("data");
		obj.setFlag(0);
		return "redirect:index.jsp";
	}
}
