<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Registrations</title>
</head>
<body>
<%@ include file="navbar.jsp" %>

<h1>All Course Registrations</h1>

<table border="1">
    <thead>
        <tr> 
            <th>Course ID</th>
            <th>Course Code</th>
            <th>Course Name</th>
            <th>Course Description</th>
            <th>Course Credit</th>
            <th>Instructor</th>
            <th>Seats</th>
            <th>Student ID</th>
            <th>Student Name</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td><c:out value="${course.courseid}"/></td>
                <td><c:out value="${course.courseCode}"/></td>
                <td>${course.courseName}</td>
                <td>${course.courseDescription}</td>
                <td>${course.courseCredit}</td>
                <td>${course.instructor}</td>
                <td>${course.seats}</td>
                <c:if test="${not empty course.student}">
                    <td><c:out value="${course.student.studentid}"/></td>
                    <td>${course.student.fname} ${course.student.lname}</td>
                    <td>${course.student.email}</td>
                </c:if>
                <c:if test="${empty course.student}">
                    <td colspan="3">No student registered</td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
