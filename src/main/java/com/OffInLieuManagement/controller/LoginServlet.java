package com.OffInLieuManagement.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final int MAX_ATTEMPTS = 3;
    private Properties users = new Properties();

    @Override
    public void init() throws ServletException {
        
        try (InputStream input = getServletContext().getResourceAsStream("/WEB-INF/user.properties")) {
            if (input == null) {
                throw new FileNotFoundException("Property file not found!");
            }
            users.load(input);
        } catch (IOException e) {
            throw new ServletException("Failed to load user properties", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String staffId = request.getParameter("staffId");
        String password = request.getParameter("password");

        Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
        System.out.println("failed attempt:::::"+failedAttempts);
        if (failedAttempts == null) {
            failedAttempts = 0;
        }

        
        if (request.getParameter("reset") != null) {
            session.setAttribute("failedAttempts", 0);
            response.sendRedirect("LoginPage.jsp");
            return;
        }

       
        String userName = getUserName(staffId);
        if (userName == null) {
            request.setAttribute("error", "User not found. Please check the Staff ID.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("LoginPage.jsp");
            dispatcher.forward(request, response);
            return;
        }
        

        
        if (isValidUser(staffId, password)) {
            session.setAttribute("staffId", staffId);
            session.setAttribute("userName", userName);
            session.setAttribute("role", getUserRole(staffId));
            session.setAttribute("failedAttempts", 0);
            response.sendRedirect("Menu.jsp");
        } else {
            failedAttempts++;
            session.setAttribute("failedAttempts", failedAttempts);

            if (failedAttempts >= MAX_ATTEMPTS) {
                request.setAttribute("error", "You have exceeded the maximum number of attempts. Please reset.");
            } else {
                request.setAttribute("error", "Invalid Staff ID or Password. Attempts left: " + (MAX_ATTEMPTS - failedAttempts));
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("LoginPage.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean isValidUser(String staffId, String password) {
        String storedPassword = users.getProperty(getUserPropertyKey(staffId, "password"));
        return storedPassword != null && storedPassword.equals(password);
    }

    private String getUserName(String staffId) {
        String userNameKey = getUserPropertyKey(staffId, "name");
        return userNameKey != null ? users.getProperty(userNameKey) : null;
    }

    private String getUserRole(String staffId) {
        return users.getProperty(getUserPropertyKey(staffId, "role"));
    }

    private String getUserPropertyKey(String staffId, String property) {
        for (String key : users.stringPropertyNames()) {
            if (key.contains(".staffId") && users.getProperty(key).equals(staffId)) {
                return key.replace(".staffId", "." + property);
            }
        }
        return null;
    }
}
