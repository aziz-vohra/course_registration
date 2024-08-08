<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.springboot.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Information</title>
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <h1>User Information</h1>

    <table border="1">
        <thead>
            <tr>
                <th>User ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Retrieving user information from the session
                User loggedInAdmin = (User) session.getAttribute("loggedInAdmin");
            %>
                    <tr>
                        <td><%= user.getUserid() %></td>
                        <td><%= user.getFname() %></td>
                        <td><%= user.getLname() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getPassword() %></td>
                        <td><%= user.getRole() %></td>
                    </tr>
        </tbody>
    </table>
</body>
</html>