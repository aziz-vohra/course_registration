package com.system.springboot.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.system.springboot.dao.CourseDao;
import com.system.springboot.dao.UserDao;
//
//import com.system.springboot.dao.CourseDao;
import com.system.springboot.model.Course;
import com.system.springboot.model.Student;
import com.system.springboot.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class CourseController {
	@GetMapping("/courses")
	public String courses(HttpServletRequest request)
	{   HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("loggedInAdmin");
	    boolean isAdmin = user != null && user.getRole().equals("admin");
	    if(!isAdmin) {
	    	return "home/index";
	    }
	    else {
	    	return "course/courses";
	    }
		
	}
	@PostMapping("addCourse")
	public String registerCourse(@RequestParam String courseCode,@RequestParam String courseName, 
			@RequestParam String courseDescription, @RequestParam int courseCredit,@RequestParam String instructor,
			@RequestParam int seats, RedirectAttributes redirectAttributes)
	{
		
	    Course course = new Course();
	    course.setCourseCode(courseCode);
	    course.setCourseName(courseName);
	    course.setCourseDescription(courseDescription);
	    course.setCourseCredit(courseCredit);
	    course.setInstructor(instructor);
	    course.setSeats(seats);
		CourseDao CourseDao = new CourseDao();
		CourseDao.saveCourse(course);
        redirectAttributes.addFlashAttribute("message", "Course added successfully!");
		return "redirect:/courses";
	}
	
	@PostMapping("editform")
	public String editform(@RequestParam String courseCode,@RequestParam String courseName, 
			@RequestParam String courseDescription, @RequestParam int courseCredit, 
			@RequestParam int courseid,@RequestParam String instructor,
			@RequestParam int seats, RedirectAttributes redirectAttributes)
	{
//		System.out.println(courseCode);
//		System.out.println(courseName);
//		System.out.println(courseDescription);
//		System.out.println(courseCredit);
//		System.out.println(courseid);
//		
//		Configuration cfg = new Configuration();
//	    SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
//	    Session session = sf.openSession();
//	    Transaction tx = session.beginTransaction();
//		String hql = "UPDATE Course SET courseName = :courseName, courseDescription = :courseDescription, courseCredit = :courseCredit, courseCode = :courseCode, instructor = :instructor, seats = :seats WHERE courseid = :courseid";
//		 Query query = session.createQuery(hql);
//         query.setParameter("courseName", courseName);
//         query.setParameter("courseDescription", courseDescription);
//         query.setParameter("courseCredit", courseCredit);
//         query.setParameter("courseCode", courseCode);
//         query.setParameter("courseid", courseid);
//         query.setParameter("seats", seats);
//         query.setParameter("instructor", instructor);
//         int rowCount = query.executeUpdate();
//         System.out.println("Rows affected: " + rowCount);
//         tx.commit();
// 	    session.close();
// 	   redirectAttributes.addFlashAttribute("message", "Course details updated successfully!");
//		return "redirect:/courses";
		CourseDao courseDao = new CourseDao();
		 Course course = courseDao.getCourseById(courseid);
	        if (course != null) {
	            course.setCourseName(courseName);
	            course.setCourseDescription(courseDescription);
	            course.setCourseCredit(courseCredit);
	            course.setCourseCode(courseCode);
	            course.setInstructor(instructor);
	            course.setSeats(seats);
	            courseDao.updateCourse(course);
	            redirectAttributes.addFlashAttribute("message", "Course details updated successfully!");
	        } else {
	            redirectAttributes.addFlashAttribute("error", "Course not found!");
	        }
	        return "redirect:/courses";
	}
	
	
	@GetMapping("listcourses")
	public String listcourse(HttpSession sess,Model model) {
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
	    
		CourseDao courseDao = new CourseDao();
	    User user = (User) sess.getAttribute("loggedInAdmin");
	    boolean isAdmin = user != null && user.getRole().equals("admin");
	    if(!isAdmin) {
	    	return "home/index";
	    }
	    else {
	    	List<Course> courses = courseDao.getAllCourses();
	    	model.addAttribute("courses", courses);
	        return "course/viewcourses";
	    }
		
	}
    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("id") int courseid) {
        System.out.println(courseid); // For debugging
        CourseDao courseDao = new CourseDao();

//            Configuration cfg = new Configuration();
//            SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
//            Session session = sf.openSession();
//            Transaction tx = session.beginTransaction();
//            String hql = "DELETE FROM Course WHERE courseid = :courseid";
//            Query query = session.createQuery(hql);
//            query.setParameter("courseid", courseid);
//
//            // Execute the delete query
//            int deletedCount = query.executeUpdate();
//            
//            tx.commit();
//            session.close(); 
        int deletedCount = courseDao.deleteCourseById(courseid);
        System.out.println("Number of courses deleted: " + deletedCount);

        return "redirect:/listcourses";
          
        
    }
    @GetMapping("/editCourse")
    public String editCourseForm(@RequestParam("id") int courseid,HttpSession sess,Model model) {
            System.out.println(courseid); // For debugging
//            Configuration cfg = new Configuration();
//            SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
//            Session session = sf.openSession();
//            Transaction tx = session.beginTransaction();
//            String hql = "FROM Course WHERE courseid = :courseid";
//            Query<Course> query = session.createQuery(hql, Course.class);
//            query.setParameter("courseid", courseid);
//            Course course = query.uniqueResult();
//            System.out.println(course); 
//
//            
//            tx.commit();
//            session.close(); 
            CourseDao courseDao = new CourseDao();
            User user = (User) sess.getAttribute("loggedInAdmin");
    	    boolean isAdmin = user != null && user.getRole().equals("admin");
            
    	    if(!isAdmin) {
    	    	return "home/index";
    	    }
    	    else {
    	    	Course course = courseDao.getCourseByIdWithHql(courseid);
    	    	model.addAttribute("course", course);
    	        return "course/updatepage";
    	    }
   
    }
    @GetMapping("/Viewcoursereg")
    public String Viewcoursereg(Model model,HttpSession sess) {
		System.out.println("gh");
		CourseDao courseDao = new CourseDao();
        User user = (User) sess.getAttribute("loggedInAdmin");
	    boolean isAdmin = user != null && user.getRole().equals("admin");
	    if(!isAdmin) {
	    	return "home/index";
	    }
	    else {
	    	List<Course> courses = courseDao.getAllCoursesWithStudents();
	    	model.addAttribute("courses", courses);
	    	return "admin/Viewregistrations";
	    }
    }
}

