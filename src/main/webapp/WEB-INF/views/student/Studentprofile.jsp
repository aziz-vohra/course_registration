<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.system.springboot.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Information</title>
</head>
<body>
    <%@ include file="Studentnavbar.jsp" %>

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
                User loggedInAdmin = (User) session.getAttribute("loggedInAdmin");

                if (loggedInAdmin != null) {
                    // Displaying user information in the table
            %>
                    <tr>
                        <td><%= loggedInAdmin.getUserid() %></td>
                        <td><%= loggedInAdmin.getFname() %></td>
                        <td><%= loggedInAdmin.getLname() %></td>
                        <td><%= loggedInAdmin.getEmail() %></td>
                        <td><%= loggedInAdmin.getPassword() %></td>
                        <td><%= loggedInAdmin.getRole() %></td>
                    </tr>
            <%
                } else {
                    // if User information not found in the session then display this
            %>
                    <tr>
                        <td colspan="6">User information not available</td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>