<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Courses</title>
</head>
<body>
<%@ include file="Studentnavbar.jsp" %>

<h1>My Courses</h1>

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
        <c:forEach var="course" items="${studentCourses}">
            <tr>
                <td><c:out value="${serialNumber}"/></td>
                <td><c:out value="${course.courseCode}"/></td>
                <td>${course.courseName}</td>
                <td>${course.courseDescription}</td>
                <td>${course.courseCredit}</td>
                <td>${course.instructor}</td>
                <td>${course.seats}</td>
                <td><a href="removeCourse?id=${course.courseid}">Remove Course</a></td>
            </tr>
            <c:set var="serialNumber" value="${serialNumber + 1}" />
        </c:forEach>
    </tbody>
</table>
<c:if test="${not empty refreshScript}">
    ${refreshScript}
</c:if>
</body>
</html>
