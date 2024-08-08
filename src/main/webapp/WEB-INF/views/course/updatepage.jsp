<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Course</title>
</head>
<body>
<%@ include file="../admin/navbar.jsp" %>
<h1>Edit Course</h1>

<form action="editform" method="post" >
    <input type="hidden" id="courseid" name="courseid" value="${course.courseid}">
    <label for="courseCode">Course Code:</label><br>
    <input type="text" id="courseCode" name="courseCode" value="${course.courseCode}"><br><br>

    <label for="courseName">Course Name:</label><br>
    <input type="text" id="courseName" name="courseName" value="${course.courseName}" required><br><br>

    <label for="courseDescription">Course Description:</label><br>
    <textarea id="courseDescription" name="courseDescription" rows="4" cols="40" required>${course.courseDescription}</textarea><br><br>

    <label for="courseCredit">Credit Hours:</label><br>
    <input type="number" id="courseCredit" name="courseCredit" value="${course.courseCredit}" required><br><br>
    
    <label for="instructor">Instructor:</label><br>
    <input type="text" id="instructor" name="instructor" value="${course.instructor}"required><br><br>
    
    <label for="seats">Seats:</label><br>
    <input type="number" id="seats" name="seats" value="${course.seats}" required><br><br>
    

    <input type="submit" value="Save Changes">
</form>

</body>
</html>