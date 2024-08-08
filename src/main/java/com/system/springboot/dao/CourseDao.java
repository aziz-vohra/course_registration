package com.system.springboot.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.system.springboot.model.Course;

@Component
public class CourseDao extends DAO{
	public void saveCourse(Course course){ 
        beginTransaction();
        getSession().save(course);
        commitTransaction();
    }
    public List<Course> getAllCoursesWithStudents() {
    	beginTransaction();
        Session session = getSession();
        List<Course> courses = session.createQuery("SELECT c FROM Course c LEFT JOIN FETCH c.student", Course.class).getResultList();
        commitTransaction();
        closeSession();
        return courses;
    }
    public Course getCourseByIdWithHql(int courseId) {
        beginTransaction();
        Session session = getSession();
        String hql = "FROM Course WHERE courseid = :courseid";
        Query<Course> query = session.createQuery(hql, Course.class);
        query.setParameter("courseid", courseId);
        Course course = query.uniqueResult();
        commitTransaction();
        return course;
    }
    
    public int deleteCourseById(int courseId) {
        beginTransaction();
        Query query = getSession().createQuery("DELETE FROM Course WHERE courseid = :courseid");
        query.setParameter("courseid", courseId);
        int deletedCount = query.executeUpdate();
        commitTransaction();
        return deletedCount;
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
    
    public Course getCourseById(int courseId) {
        beginTransaction();
        Query<Course> query = getSession().createQuery("FROM Course WHERE courseid = :courseId", Course.class);
        query.setParameter("courseId", courseId);
        Course course = query.uniqueResult();
        commitTransaction();
        return course;
    }
    public void updateCourse(Course course) {
        beginTransaction();
        getSession().update(course);
        commitTransaction();
    }
}
