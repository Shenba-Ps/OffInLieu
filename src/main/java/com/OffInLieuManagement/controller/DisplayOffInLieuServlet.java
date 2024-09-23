package com.OffInLieuManagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;

import com.OffInLieuManagement.entity.OffInLieuRecord;
import com.OffInLieuManagement.service.OffInLieuService;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class DisplayOffInLieuServlet extends HttpServlet{

	private OffInLieuService service = new OffInLieuService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String staffId = (String) session.getAttribute("staffId");
		File jsonF = new File(getServletContext().getRealPath("/test.json"));
		List<OffInLieuRecord> records = service.loadRecords(jsonF);
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println("View Records::::::"+mapper.writeValueAsString(records));
	/*	request.setAttribute(
				"records",
				records.stream()
						.filter(record -> record.getStaffId().equals(staffId)
								&& "P".equals(record.getStatus())).toList());*/
		
		request.setAttribute(
			    "records",
			    records.stream()
			           .filter(record -> record.getStaffId().equals(staffId)
			                             && "P".equals(record.getStatus()))
			           .collect(Collectors.toList())
			);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("Display.jsp");
		dispatcher.forward(request, response);
	}
}