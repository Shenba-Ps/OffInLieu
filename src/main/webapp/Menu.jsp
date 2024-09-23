<!DOCTYPE HTML>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Page</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #d4e8fc; 
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh; 
        }

        h1 {
            color: #2c3e50; 
            margin-bottom: 40px;
            text-align: center;
        }

        form {
            margin: 15px 0; 
            width: 100%; 
            max-width: 300px;
        }

        button {
            width: 100%; 
            padding: 12px;
            border: none;
            border-radius: 5px;
            background-color: #007bff; 
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
        }

        button:hover {
            background-color: #2980b9; 
            transform: translateY(-2px); 
        }

        button:active {
            transform: translateY(0); 
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); 
        }

        
        @media (max-width: 400px) {
            h1 {
                font-size: 1.5em; 
            }

            button {
                font-size: 14px; 
            }
        }
    </style>
    <script type="text/javascript">
    	function loadDatas()
    	{
    		alert();
    		document.getElementById("appluOffInLieu").action = "ApplyOffInLieuServlet";
    		document.getElementById("appluOffInLieu").method="POST";
            document.getElementById("appluOffInLieu").submit();
    	}
    </script>
</head>
<body>
    <h1>Welcome ${sessionScope.userName}</h1>
    <form id="appluOffInLieu">
    	<input type="hidden" name="actions" id="actions" value="loadValues">
        <button onclick="loadDatas()" type="submit">Apply Off-in-Lieu</button>
    </form>
    <form action="displayOffInLieu" method="get">
        <button type="submit">Display Off-in-Lieu</button>
    </form>
    <form action="LoginPage.jsp" method="post">
        <button type="submit">Return to Login</button>
    </form>
</body>
</html>