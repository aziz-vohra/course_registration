<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
         form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"],
        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
</style>
     <script>
        // Function to remove error message after 3 seconds
        setTimeout(function() {
            var errorMessage = document.getElementById("errorMessage");
            if (errorMessage) {
                errorMessage.remove();
            }
        }, 1000); 
    </script>
</head>
<body>
    <h2>Sign In</h2>
    <% if (session.getAttribute("error") != null) { %>
        <div id="errorMessage" style="color: red;"><%= session.getAttribute("error") %></div>
        <% session.removeAttribute("error"); // Clear the error message after displaying it %>
    <% } %>
    <form action="checkUser" method="post">
        Email: <input type="text" name="email"><br>
        Password: <input type="password" name="password"><br>
        Role: <select name="role">
            <option value="admin">Admin</option>
            <option value="student">Student</option>
        </select><br>
        <input type="submit" value="Sign In">
    </form>
</body>
</html>