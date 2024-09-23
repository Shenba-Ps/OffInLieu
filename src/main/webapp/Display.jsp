<!DOCTYPE HTML>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Off-in-Lieu Records</title>
<style type="text/css">
body {
    font-family: Arial, sans-serif;
    margin: 50px;
    background-color: #f4f4f9;
}

h1 {
    text-align: center;
    color: #333;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

th, td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

th {
    background-color: #007BFF;
    color: white;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

tr:hover {
    background-color: #e9ecef;
}

button {
    display: block;
    margin: 20px auto;
    padding: 10px 20px;
    font-size: 16px;
    color: white;
    background-color: #28a745;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #218838;
}

.action-buttons {
    display: flex;
    gap: 10px;
}

.update-btn {
    background-color: #ffc107;
}

.update-btn:hover {
    background-color: #e0a800;
}

.delete-btn {
    background-color: #dc3545;
}

.delete-btn:hover {
    background-color: #c82333;
}

</style>
</head>
<body>
	<h1>Displaying Off-in-Lieu Records for ${sessionScope.userName}</h1>
	<p>Number of records: ${fn:length(records)}</p>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Contact Number</th>
			<th>Email</th>
			<th>Sat Duty Date</th>
			<th>Off Date</th>
			<th>AM/PM/FULL</th>
			<th>Approving Officer</th>
			<th>Date Update</th>
			<th>Status</th>
			<th>Actions</th> <!-- Added column for action buttons -->
		</tr>
		<c:forEach var="record" items="${records}">
			<tr>
				<td>${record.name}</td>
				<td>${record.contactNumber}</td>
				<td>${record.email}</td>
				<td>${record.satDutyDate}</td>
				<td>${record.offDate}</td>
				<td>${record.amPmFull}</td>
				<td>${record.approvingOfficerId}</td>
				<td>${record.dateUpdate}</td>
				<td>${record.status}</td>
				<td class="action-buttons">
					<!-- Update Button -->
					<form action="UpdateRecord.jsp" method="post" style="display:inline;">
						<input type="hidden" name="recordId" value="${record.id}" />
						<button type="submit" class="update-btn">Update</button>
					</form>

					<!-- Delete Button -->
					<form action="deleteRecord" method="post" style="display:inline;">
						<input type="hidden" name="recordId" value="${record.id}" />
						<button type="submit" class="delete-btn" onclick="return confirm('Are you sure you want to delete this record?');">Delete</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- Return to Menu Button -->
	<form action="Menu.jsp" method="post">
		<button type="submit">Return to Menu</button>
	</form>
</body>
</html>