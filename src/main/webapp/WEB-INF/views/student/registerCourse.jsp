<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Course</title>
</head>
<body>
<%@ include file="Studentnavbar.jsp" %>
<h1>Register Course</h1>

<table border="1">
    <thead>
        <tr> 
            <th>Course Code</th>
            <th>Course Name</th>
            <th>Course Description</th>
            <th>Credit Hours</th>
            <th>Instructor</th>
            <th>Seats</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td>${course.courseCode}</td>
                <td>${course.courseName}</td>
                <td>${course.courseDescription}</td>
                <td>${course.courseCredit}</td>
                <td>${course.instructor}</td>
                <td>${course.seats}</td>
                <td>
                    <c:choose>
                        <c:when test="${course.student != null}">
                            <c:if test="${course.student.email == sessionScope.loggedInAdmin.email}">
                                Course Added
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <a href="addCourse?id=${course.courseid}">Add Course</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>


