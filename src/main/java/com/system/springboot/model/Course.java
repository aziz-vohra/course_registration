package com.system.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID")
    private int courseid;

    @Column(name = "courseCode")
    private String courseCode;

    @Column(name = "courseName")
    private String courseName;

    @Column(name = "courseDescription")
    private String courseDescription;

    @Column(name = "courseCredit")
    private int courseCredit;

    @Column(name = "instructor")
    private String instructor;

    @Column(name = "seats")
    private int seats;

	
	@ManyToOne
	@JoinColumn(name = "student_studentID")
	private Student student;
	
	public Course(int courseid, String courseCode, String courseName, String courseDescription, int courseCredit,
			String instructor, int seats, Student student) {
		super();
		this.courseid = courseid;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseCredit = courseCredit;
		this.instructor = instructor;
		this.seats = seats;
		this.student = student;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getCourseid() {
		return courseid;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public int getCourseCredit() {
		return courseCredit;
	}
	public void setCourseCredit(int courseCredit) {
		this.courseCredit = courseCredit;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	@Override
	public String toString() {
		return "Course [courseid=" + courseid + ", courseCode=" + courseCode + ", courseName=" + courseName
				+ ", courseDescription=" + courseDescription + ", courseCredit=" + courseCredit + ", instructor="
				+ instructor + ", seats=" + seats + "]";
	}
}
