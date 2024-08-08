<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.springboot.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .navbar {
		    display: flex;
		    width:800px;
		    justify-content: space-around;
		    height: 120px;
		    background-color: #00ffff26;
		    color: white;
		    padding: 0 20px;
		    align-items: center; 
		    justify-content: space-between;
        }

        .nav-item {
            width: 100px;
            height: 105px;
            background-color: #9d7171;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 10px;
            cursor: pointer;
        }

        .nav-item:hover {
            background-color: #777;
        }

        .nav-link {
            text-decoration: none;
            color: white;
            font-weight: bold;
        }
        .user-info {
            display: flex;
            align-items: center;
        }

        .welcome-msg {
            margin-right: -40px;
    	    color: red;
        }
        .submit{
        background-color: #9d7171;
        border-radius: 5px;
        cursor: pointer;
        }
    </style>
</head>
<body>
			<%
                User user = (User) session.getAttribute("loggedInAdmin");
            %>

<div class="navbar">
    <div class="nav-item">
        <a href="Profile" class="nav-link">Profile</a>
    </div>
    <div class="nav-item">
        <a href="Viewcoursereg" class="nav-link">View Course Registrations</a>
    </div>
<div class="nav-item">
     <a href="/courses" class="nav-link">Add Courses</a>
</div>
    <div class="user-info">
        <div class="welcome-msg">Welcome <%= user.getFname() %></div>
    </div>
    <div>
        <form action="logout" method="post">
            <button type="submit" class="submit">Logout</button>
        </form>
    </div>

</div>

</body>
</html>