<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Course</title>
    <style>
        #message {
            color: red;
        }
    </style>
    <script>
        // Function to hide the message after a specified time
        function hideMessage() {
            var messageElement = document.getElementById('message');
            setTimeout(function() {
                messageElement.style.display = 'none';
            }, 1000); 
        }
    </script>
</head>
<body onload="hideMessage()">
<%@ include file="../admin/navbar.jsp" %>
<c:if test="${not empty message}">
        <div id="message">${message}</div>
    </c:if>

<form action="addCourse" method="post">
	<label for="courseCode">Course Code:</label><br>
    <input type="text" id="courseCode" name="courseCode" required><br><br>
    
    <label for="courseName">Course Name:</label><br>
    <input type="text" id="courseName" name="courseName" required><br><br>

    <label for="courseDescription">Course Description:</label><br>
    <textarea id="courseDescription" name="courseDescription" rows="3" cols="40" required></textarea><br><br>

    <label for="courseCredit">Credit Hours:</label><br>
    <input type="number" id="courseCredit" name="courseCredit" required><br><br>
    
    <label for="instructor">Instructor:</label><br>
    <input type="text" id="instructor" name="instructor" required><br><br>
    
    <label for="seats">Seats:</label><br>
    <input type="number" id="seats" name="seats" required><br><br>

    <input type="submit" value="Add Course">
</form>
<br>
<!-- Button for listing all courses -->
<form action="listcourses" method="get">
    <button type="submit">List All Courses</button>
</form>



</body>
</html>