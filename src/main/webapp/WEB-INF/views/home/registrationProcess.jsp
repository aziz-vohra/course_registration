<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Success</title>
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
</style>
<script>
    // Function to redirect to main page after 2 seconds
    setTimeout(function(){
        window.location.href = "/";
    }, 2000);
</script>
</head>
<body>
<h1>${fname} is Registered</h1>
</body>
</html>