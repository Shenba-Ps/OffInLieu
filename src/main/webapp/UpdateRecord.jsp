<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Update Record</title>
</head>
<body>
    <h2>Update Record for ${record.name}</h2>

    <form action="updateRecordServlet" method="post">
        <input type="hidden" name="recordId" value="${record.id}" />
        Name: <input type="text" name="name" value="${record.name}" /><br/>
        Contact Number: <input type="text" name="contactNumber" value="${record.contactNumber}" /><br/>
        Email: <input type="email" name="email" value="${record.email}" /><br/>
        Sat Duty Date: <input type="date" name="satDutyDate" value="${record.satDutyDate}" /><br/>
        Off Date: <input type="date" name="offDate" value="${record.offDate}" /><br/>
        AM/PM/FULL: <input type="text" name="amPmFull" value="${record.amPmFull}" /><br/>
        Approving Officer ID: <input type="text" name="approvingOfficerId" value="${record.approvingOfficerId}" /><br/>
        Status: <input type="text" name="status" value="${record.status}" /><br/>
        <input type="submit" value="Update" />
    </form>
</body>
</html>