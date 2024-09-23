<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Login - Off-in-Lieu Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #d4e8fc; 
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 300px;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: white;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            margin-bottom: 5px;
            display: block; 
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px; 
            border: 1px solid #ccc;
            border-radius: 4px; 
        }

        input[type="submit"],
        input[type="reset"] {
            width: 48%; 
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px; 
        }

        input[type="submit"] {
            background-color: #007bff; 
            color: white;
        }

        input[type="reset"] {
            background-color: #6c757d; 
            color: white;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            opacity: 0.9; 
        }

        .error {
            color: red;
            text-align: center; 
            margin-top: 15px; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form action="login" method="post">
            <label for="staffId">Staff ID:</label>
            <input type="text" id="staffId" name="staffId" required maxlength="5">
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <input type="submit" value="Login">
            <input type="reset" value="Clear">
        </form>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <c:if test="${sessionScope.failedAttempts >= 3}">
            <p class="error">
                You have exceeded the maximum number of attempts. <a href="login?reset=true">Click here to reset.</a>
            </p>
        </c:if>
    </div>
</body>
</html>