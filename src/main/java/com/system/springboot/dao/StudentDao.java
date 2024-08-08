package com.system.springboot.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.system.springboot.model.Course;
import com.system.springboot.model.Student;

import jakarta.persistence.Query;


@Component
public class StudentDao extends DAO{
	 public void saveStudent(Student student){ 
	        beginTransaction();
	        getSession().save(student);
	        commitTransaction();
	    }
	 
	    public List<Course> getAllCourses() {
	        Session session = getSession();
	        try {
	            // Begin transaction
	            session.beginTransaction();

	            // Query to fetch all courses
	            List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();

	            // Commit transaction
	            session.getTransaction().commit();

	            return courses;
	        } catch (Exception ex) {
	            // Rollback transaction if an exception occurs
	            session.getTransaction().rollback();
	            throw ex; // Re-throw the exception
	        } finally {
	            // Close session
	            session.close();
	        }
	    }
}
