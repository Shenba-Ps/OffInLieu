<!DOCTYPE html>
<html>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <%@ page import="java.util.*" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
    <script src="<%= request.getContextPath() %>/jquery-3.6.0.min.js"></script>
    <script src="<%= request.getContextPath() %>/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui.css"></script>
    <title>Apply Off-in-Lieu</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #d4e8fc; 
            margin: 50px;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh; 
        }

        h1 {
            color: #2c3e50; 
            margin-bottom: 30px;
            text-align: center;
        }

        form {
            background-color: #ffffff; 
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); 
            width: 100%; 
            max-width: 400px; 
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            color: #34495e; 
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="date"],
        select {
            padding: 10px;
            margin-bottom: 15px; 
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        .radio-group {
            margin-bottom: 15px; 
        }

        input[type="radio"] {
            margin-right: 5px; 
            cursor: pointer; 
        }

        button {
            padding: 12px;
            border: none;
            border-radius: 5px;
            background-color: #3498db; 
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
        }

        button:hover {
            background-color: #2980b9;
            transform: translateY(-2px); 
        }

        .error {
            color: red;
            text-align: center;
            margin-top: 15px;
            font-weight: bold;
        }
        
         /* Ensure no custom styles are hiding navigation icons */
         .ui-datepicker {
            font-size: 14px;
        }
    </style>
    <%
	     	 List<String> blockDateList = (List<String>) session.getAttribute("AppliedSatDateList") != null 
	   		 ? (List<String>) session.getAttribute("AppliedSatDateList") 
	    	: new ArrayList<String>();
        
           
            StringBuilder jsArray = new StringBuilder("[");
            if(blockDateList.size() > 0)
            {
	           	 for (int i = 0; i < blockDateList.size(); i++) 
	           	 {
	                jsArray.append("\"").append(blockDateList.get(i)).append("\"");
	                if (i < blockDateList.size() - 1) {
	                    jsArray.append(",");
	                }
	            }
	            jsArray.append("]");
	        }
        %>
</head>
<body>
    <h1>Apply Off-in-Lieu for ${sessionScope.userName}</h1>
    <form action="applyOffInLieu" method="post">
        <p class="error">${error}</p>
        <label for="name">Name:</label>
        <input type="text" name="name" maxlength="50" required>
        
        <label for="contactNumber">Contact Number:</label>
        <input type="text" name="contactNumber" maxlength="8" required>
        
        <label for="email">Email:</label>
        <input type="email" name="email" required>
        
        <label for="satDutyDate">Sat Duty Date:</label>
        <!-- <input type="date" name="satDutyDate" id="satDutyDate" required> -->
        <input type="text" id="dateInput" name="satDutyDate" />
        
        <label for="offDate">Off Date:</label>
        <input type="date" name="offDate" id="offDate" required>
        
        <label>AM/PM/FULL:</label>
        <div class="radio-group">
            <input type="radio" name="amPmFull" value="AM"> AM
            <input type="radio" name="amPmFull" value="PM"> PM
            <input type="radio" name="amPmFull" value="FULL"> FULL
        </div>
        
        <label for="approvingOfficerId">Approving Officer ID:</label>
        <select id="role" name="approvingOfficerId" required>
            <option value="">--Select Approving Officer Role--</option>
            <c:forEach var="role" items="${ApprovingOfficerList}">
                <option value="${role.staffId}">${role.staffName} (${role.staffId})</option>
            </c:forEach>
        </select>
        
        <button type="submit">Apply</button>
    </form>
     <script>
     
        document.addEventListener("DOMContentLoaded", function() {
        const today = new Date().toISOString().split('T')[0];
        document.getElementById("offDate").setAttribute("min", today);
        
        
        
    $(function() {
   			 var blockDates = <%= jsArray %>;
   			 console.log("BLOCK DATES ::", blockDates);

			    $('#dateInput').datepicker({
			      dateFormat: "yy-mm-dd",
			        beforeShowDay: function(date) {
			            const today = new Date();
			            const day = date.getDay();
			            const isSaturday = day === 6;
			            const isPastDate = date < today; // Only allow past dates
			
			            // Format date to match the blockDates array
			            const formattedDate = $.datepicker.formatDate('yy-mm-dd', date);
			
			            // Check if the date is in the blockDates array
			            const isBlocked = blockDates.includes(formattedDate);
			
			            // Return true only if it's a past Saturday and not in the blocked dates
			            return [isSaturday && isPastDate && !isBlocked, isBlocked ? "Unavailable" : ""];
			        },
			        showButtonPanel: true,
			        changeMonth: true,
			        changeYear: true,
			        maxDate: "+0D"  // Prevent selecting future dates
			    });
			});

        });
    </script>
</body>
</html>