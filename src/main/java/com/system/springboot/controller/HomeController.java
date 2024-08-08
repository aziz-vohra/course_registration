package com.system.springboot.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.system.springboot.dao.AdminDao;
import com.system.springboot.dao.StudentDao;
import com.system.springboot.dao.UserDao;
//import com.system.springboot.dao.AdminDao;
//import com.system.springboot.dao.StudentDao;
//import com.system.springboot.dao.UserDao;
import com.system.springboot.model.Admin;
import com.system.springboot.model.Student;
import com.system.springboot.model.User;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home()
	{
		return "home/index";
	}
	
	@GetMapping("/signup")
	public String signup()
	{
		return "home/signup";
	}
	
	@GetMapping("/signin")
	public String signin(Model model)
	{	 model.addAttribute("error", null);
		return "home/signin";
	}
	
	@PostMapping("checkUser")
	public String checkUser(@RequestParam String email, @RequestParam String password, @RequestParam String role, HttpSession sess) {
	    try {
	        UserDao userDao = new UserDao();
	        User user = userDao.getUserByEmailAndPasswordAndRole(email, password, role);

	        System.out.println(user);
	        if (user != null) {
	            if ("admin".equals(role)) {
	                sess.setAttribute("loggedInAdmin", user);
	                return "admin/Adminhomepage"; // Redirect to admin homepage
	            } else {
	                sess.setAttribute("loggedInAdmin", user);
	                return "student/Studentview"; // Redirect to user homepage
	            }
	        } else {
	            // Add error message to the session
	            sess.setAttribute("error", "Incorrect email, password, or role. Please try again.");
	            return "redirect:/signin"; // Return to the sign-in page with error message
	        }
	    } catch (NoResultException e) {
	        // Handle the case where no user is found
	        sess.setAttribute("error", "No user found with the provided email, password, and role.");
	        return "redirect:/signin"; // Return to the sign-in page with error message
	    }
	}
	
	
	
	@PostMapping("addUser")
	public ModelAndView registrationProcess(@RequestParam String fname, @RequestParam String lname, @RequestParam String email, @RequestParam String password, @RequestParam String role)
	{	
		System.out.println("First Name: " + fname);
	    System.out.println("Last Name: " + lname);
	    System.out.println("Email: " + email);
	    System.out.println("Password: " + password);
	    System.out.println("Role: " + role);
	    User user = new User();
	    user.setFname(fname);
	    user.setLname(lname);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);	
		UserDao userDao = new UserDao();
		userDao.saveUser(user);
	    
		if (user.getRole().equals("admin")) {
			Admin admin = new Admin();
			admin.setFname(fname);
			admin.setLname(lname);
			admin.setEmail(email);
			admin.setPassword(password);
			AdminDao adminDao = new AdminDao();
			adminDao.saveAdmin(admin);		
		}
		if (user.getRole().equals("student")) {
			Student student = new Student();
			student.setFname(fname);
			student.setLname(lname);
			student.setEmail(email);
			student.setPassword(password);
			StudentDao studentDao = new StudentDao();
			studentDao.saveStudent(student);	
		}

	    ModelAndView mv = new ModelAndView("home/registrationProcess");
	    mv.addObject("fname",fname);
		return mv;
	}
	@GetMapping("Profile")
	public String viewprofile(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("loggedInAdmin");
	    boolean isAdmin = user != null && user.getRole().equals("admin");
	    if(!isAdmin) {
	    	return "home/index";
	    }
	    else {
	    	return "admin/profile";
	    }
	}
	@PostMapping("logout")
	public String logout(HttpServletRequest request)
	{   HttpSession session = request.getSession(false);
	 if (session != null) {
         session.invalidate();
     }
	
	 return "redirect:/";
	}
}
