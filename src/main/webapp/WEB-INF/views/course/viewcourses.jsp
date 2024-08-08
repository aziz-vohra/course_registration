<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Courses</title>
</head>
<body>
<%@ include file="../admin/navbar.jsp" %>

<h1>All Courses</h1>

<table border="1">
    <thead>
        <tr> 
            <th>S. No</th>
            <th>Course Code</th>
            <th>Course Name</th>
            <th>Course Description</th>
            <th>Credit Hours</th>
            <th>Instructor</th>
            <th>Seats</th>
        </tr>
    </thead>
    <tbody>
        <c:set var="serialNumber" value="1" />
        <c:forEach var="course" items="${courses}">
            <tr>
                <td><c:out value="${serialNumber}"/></td>
                <td><c:out value="${course.courseCode}"/></td>
                <td>${course.courseName}</td>
                <td>${course.courseDescription}</td>
                <td>${course.courseCredit}</td>
                <td>${course.instructor}</td>
                <td>${course.seats}</td>
                <td><a href="editCourse?id=${course.courseid}">Edit</a></td>
                <td><a href="deleteCourse?id=${course.courseid}">Delete</a></td>
            </tr>
            <c:set var="serialNumber" value="${serialNumber + 1}" />
        </c:forEach>
    </tbody>
</table>

</body>
</html>
