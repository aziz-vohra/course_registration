package com.system.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.system.springboot.dao.CourseDao;
import com.system.springboot.dao.StudentDao;
import com.system.springboot.dao.UserDao;
//import com.system.springboot.dao.AdminDao;
//import com.system.springboot.dao.StudentDao;
//import com.system.springboot.dao.UserDao;
import com.system.springboot.model.Admin;
import com.system.springboot.model.Course;
import com.system.springboot.model.Student;
import com.system.springboot.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

	
	
	@GetMapping("StudentProfile")
	public String viewprofile(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("loggedInAdmin");
	    boolean isStudent = user != null && user.getRole().equals("student");
	    if(!isStudent) {
	    	return "home/index";
	    }
	    else {
	    	return "student/Studentprofile";
	    }
	}
	
	
	@GetMapping("registercourse")
	public String registercourse(HttpSession sess,Model model)
	{
		CourseDao courseDao = new CourseDao();
//		Configuration cfg = new Configuration();
//	    SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
//	    Session session = sf.openSession();
//	    Transaction tx = session.beginTransaction();
//	    
//	 // Query to fetch all courses
//	    Query query = session.createQuery("FROM Course");
//	    List<Course> courses = query.list(); // Retrieve all courses from the database
//	    // Print all courses
//	    for (Course course : courses) {
//	        System.out.println(course.toString());
//	    }
//
//	    tx.commit();
//	    session.close();
//	    
//	    User user = (User) sess.getAttribute("loggedInAdmin");
//	    boolean isStudent = user != null && user.getRole().equals("student");
//	    if(!isStudent) {
//	    	return "home/index";
//	    }
//	    else {
//	    	model.addAttribute("courses", courses);
//	    	return "student/registerCourse";
//	    }
		// Fetch all courses using DAO
        List<Course> courses = courseDao.getAllCourses();

        // Print all courses
        for (Course course : courses) {
            System.out.println(course.toString());
        }

        User user = (User) sess.getAttribute("loggedInAdmin");
        boolean isStudent = user != null && user.getRole().equals("student");
        if (!isStudent) {
            return "home/index";
        } else {
            model.addAttribute("courses", courses);
            return "student/registerCourse";
        }
	}
	
	
	@GetMapping("/addCourse")
	public String test(@RequestParam("id") int courseid,HttpSession sess,Model model)
	{
		System.out.println(courseid);
		
		
		 User user = (User) sess.getAttribute("loggedInAdmin");
		    if (user == null || !user.getRole().equals("student")) {
		        return "home/index"; // Redirect to home if not logged in as a student
		    }
		    
		   

		    // Retrieve the logged-in user's ID or email
		    String email = user.getEmail(); // Assuming the user object has an ID field

		    // Fetch the student object based on the user's ID or email
		    Configuration cfg = new Configuration();
            SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
           
		    Query<Student> query = session.createQuery("FROM Student WHERE email = :email", Student.class);
		    query.setParameter("email", email);
		    Student student = query.uniqueResult();
		    Course courseToAdd = session.get(Course.class, courseid);
		    
		    
		    
		    System.out.println(student);
		    System.out.println(courseToAdd); //Everything is right till this point
		    
		    List<Course> studentCourses = student.getCourses();
		    studentCourses.add(courseToAdd);
		    student.setCourses(studentCourses);
		    // Establish the association between student and course
		    courseToAdd.setStudent(student);
		    
		    int updatedSeats = courseToAdd.getSeats() - 1;
		    courseToAdd.setSeats(updatedSeats);
		    
		    session.save(student);
		    session.update(courseToAdd);
		    tx.commit();
            session.close();
		    
         // After setting up the student and adding the course, retrieve the updated list of courses
            List<Course> courses = student.getCourses();
            model.addAttribute("courses", courses);
            return "student/registerCourse";
	       
	      
	}
	@GetMapping("mycourses")
	public String mycourses(HttpSession sess,Model model) {
		System.out.println("we came here");
		    User user = (User) sess.getAttribute("loggedInAdmin");
		    if (user == null || !user.getRole().equals("student")) {
		        return "home/index"; // Redirect to home if not logged in as a student
		    }
		    
		  
		    // Retrieve the logged-in user's ID or email
		    String email = user.getEmail(); // Assuming the user object has an ID field
		    
		    // Fetch the student object based on the user's ID or email
		    Configuration cfg = new Configuration();
            SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
           
            Query<Student> query = session.createQuery("FROM Student s LEFT JOIN FETCH s.courses WHERE s.email = :email", Student.class);
            query.setParameter("email", email);
		    query.setParameter("email", email);
		    Student student = query.uniqueResult();
		    System.out.println(student);
		  
		    
		 // Retrieve the list of courses for the student
		    List<Course> studentCourses = student.getCourses();
		    tx.commit();
            session.close();
            model.addAttribute("studentCourses", studentCourses);
            return "student/mycourse";

		
	}
	
	@GetMapping("/removeCourse")
	public String removeCourse(@RequestParam("id") int courseid, HttpSession sess,Model model) {
		System.out.println("delete");
		User user = (User) sess.getAttribute("loggedInAdmin");
	    if (user == null || !user.getRole().equals("student")) {
	        return "home/index"; // Redirect to home if not logged in as a student
	    }
	    // Retrieve the logged-in user's ID or email
	    String email = user.getEmail(); // Assuming the user object has an ID field
	    
	    // Fetch the student object based on the user's ID or email
	    Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
       
        Query<Student> query = session.createQuery("FROM Student s LEFT JOIN FETCH s.courses WHERE s.email = :email", Student.class);
        query.setParameter("email", email);
	    query.setParameter("email", email);
	    Student student = query.uniqueResult();
	    
	    Course courseToRemove = session.get(Course.class, courseid);
	    System.out.println(student);
	    System.out.println(courseToRemove);
	    
	    // Remove the course from the student's courses
	    student.getCourses().remove(courseToRemove);

	    // Set the student property of the course to null
	    courseToRemove.setStudent(null);
	 // Save the updated student and course entities
	    session.update(student);
	    session.update(courseToRemove);
	    
	    tx.commit();
	    session.close();
	    
	 // After setting up the student and adding the course, retrieve the updated list of courses
        List<Course> courses = student.getCourses();
        model.addAttribute("courses", courses);
        return "student/registerCourse";

	    
	}
}
