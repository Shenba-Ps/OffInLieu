package com.OffInLieuManagement.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.OffInLieuManagement.entity.OffInLieuRecord;
import com.OffInLieuManagement.entity.User;



public class OffInLieuService {
	
	private static final String FILE_PATH = "C:/appl/IBM/Off-In";
	private static final String APPROVING_OFFICER = "Approving Officer";
	private static final String User_Properties_File = "/WEB-INF/user.properties";

	public List<OffInLieuRecord> loadRecords(File file) throws IOException 
	{
		System.out.println("------ INSIDE LOCAL RECORD -----");
		 ObjectMapper objectMapper = new ObjectMapper();
		
		 
		List<OffInLieuRecord> offInLieuLst = null;
		//File file = new File(FILE_PATH);
		if (!file.exists()){
			System.out.println("---- NO FILE EXIST----");
			offInLieuLst = new ArrayList<>();
		}else
		{
			System.out.println("-------FILE EXIST---");
			System.out.println("file::::"+file.getName());
			 
		        try {
		        	offInLieuLst = objectMapper.readValue(file, new TypeReference<List<OffInLieuRecord>>() {});
		           System.out.println("LIST SIZE ::" + offInLieuLst != null ? offInLieuLst.size() : null);
		        	
		        	for (OffInLieuRecord records : offInLieuLst) {
		                System.out.println(objectMapper.writeValueAsString(records));
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}
		
		System.out.println(objectMapper.writeValueAsString(offInLieuLst));
		
		return offInLieuLst;
		
	}

	public void saveRecord(OffInLieuRecord record, File file) throws IOException {
		List<OffInLieuRecord> records = loadRecords(file);
		
		System.out.println(new ObjectMapper().writeValueAsString(records));
		records.add(record);
		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.writeValue(file, records);
		
		 
	}

	public boolean validateStaffId(String staffId) {
		return staffId != null && staffId.matches("[a-zA-Z0-9]{5}");
	}

	public boolean validateDates(LocalDate satDutyDate, LocalDate offDate) {
		return satDutyDate != null && offDate != null
				&& satDutyDate.getDayOfWeek() == java.time.DayOfWeek.SATURDAY
				&& offDate.isBefore(LocalDate.now());
	}

	public boolean recordExists(List<OffInLieuRecord> records, String staffId,String offDate) throws JsonGenerationException, JsonMappingException, IOException {
	System.out.println("--------INSIDE RECORD EXIST METHOD----------");
	System.out.println("--------record----------"+new ObjectMapper().writeValueAsString(records));
	
		for (OffInLieuRecord record : records) {
			if (record.getStaffId().equals(staffId)
					&& record.getOffDate().equals(offDate)) {
				System.out.println("---STAFF ID PRESENT----");
				return true;
			}
		}
		return false;
	}

	public List<User> getDropDownValues(Properties users) 
	{
		 List<User> userList = loadUserPropertiesFile(users);
		 
		return  userList.stream().filter(s -> s.getRole().equalsIgnoreCase(APPROVING_OFFICER)).collect(Collectors.toList());
		
	}

	private List<User> loadUserPropertiesFile(Properties properties) 
	{
		
					List<User> staffList = null;
	
		      

		            List<Integer> staffIndices = properties.stringPropertyNames().stream()
		                    .filter(key -> key.startsWith("staff") && key.endsWith(".name")) // Filter only keys for staff names
		                    .map(key -> key.replaceAll("[^0-9]", "")) // Extract the index number from the key (e.g., "staff1.name" -> "1")
		                    .map(Integer::parseInt) // Convert it to an integer
		                    .sorted() // Sort indices
		                    .collect(Collectors.toList());
		            
		            staffList = staffIndices.stream()
		                    .map(i -> new User(
		                            properties.getProperty("staff" + i + ".name"),
		                            properties.getProperty("staff" + i + ".staffId"),
		                            properties.getProperty("staff" + i + ".password"),
		                            properties.getProperty("staff" + i + ".role")
		                    ))
		                    .filter(staff -> staff.getStaffName() != null && staff.getStaffId() != null)
		                    .collect(Collectors.toList());

		       
		        return staffList;
		    }

	public List<String> getAppliedSaturdayDates(String staffId, File jsonF) throws IOException 
	{
		List<OffInLieuRecord> offInLieuList = loadRecords(jsonF);
		List<String> appliedDatesLst = null;
		if(offInLieuList != null && offInLieuList.size() > 0)
		{
			appliedDatesLst = offInLieuList.stream().filter(s -> s.getStaffId().equals(staffId) && s.getStatus().equalsIgnoreCase("P")).
			map(OffInLieuRecord::getSatDutyDate)
			.collect(Collectors.toList());
		}
		return appliedDatesLst;
	}
		

		
	
}