package com.OffInLieuManagement.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.OffInLieuManagement.service.OffInLieuService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;





public class ApplyOffInLieuServlet extends HttpServlet{
	private OffInLieuService service = new OffInLieuService();
    
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("---- APPLY AOFF IN LIEU SERVLET---------");
		File jsonF = new File(getServletContext().getRealPath("/test.json"));
		HttpSession session = request.getSession();
		String staffId = (String) session.getAttribute("staffId");

		String name = request.getParameter("name");
		String contactNumber = request.getParameter("contactNumber");
		String email = request.getParameter("email");
		String satDutyDate = request.getParameter("satDutyDate");
		String offDate = request.getParameter("offDate");
		String amPmFull = request.getParameter("amPmFull");
		String approvingOfficerId = request.getParameter("approvingOfficerId");

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateString = currentDate.format(formatter);
        OffInLieuRecord record = new OffInLieuRecord();
        List<OffInLieuRecord> recordList = service.loadRecords(jsonF);
        System.out.println("recordList ::" +  recordList.size());
        if (recordList.isEmpty()) {
            record.setId("1");
        } else {
            int maxId = recordList.stream()
                    .mapToInt(r -> Integer.parseInt(r.getId())) 
                    .max()
                    .orElse(0); 
            record.setId(String.valueOf(maxId + 1));
            System.out.println("record ID ::" +  record.getId());
        }
        
		
		record.setStaffId(staffId);
		record.setName(name);
		record.setContactNumber(contactNumber);
		record.setEmail(email);
		record.setSatDutyDate(satDutyDate);
		record.setOffDate(offDate);
		record.setAmPmFull(amPmFull);
		record.setApprovingOfficerId(approvingOfficerId);
		record.setDateUpdate(currentDateString);
		record.setStatus("P");

		System.out.println("RECORDS DATA ::" +  new ObjectMapper().writeValueAsString(record));
		System.out.println("offDate:::"+offDate);
		if (service.recordExists(service.loadRecords(jsonF), staffId, offDate)) {
			request.setAttribute("error", "Duplicate Off Date entry!");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("apply.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("---------- DO SAVE------");
			service.saveRecord(record,jsonF);
			response.sendRedirect("menu.jsp");
		}
	}

}