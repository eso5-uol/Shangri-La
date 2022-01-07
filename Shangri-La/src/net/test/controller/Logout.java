package net.test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID =1L;

	
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	res.setContentType("text/html");
    	Cookie[] cookies = req.getCookies();
    	if(cookies!=null) {
    		for(Cookie cookie: cookies) {
    			if (cookie.getName().equals("JSESSIONID")) {
    				System.out.println("JSESSIONID" + cookie.getValue());
    				break;
    			}
    			
    		}
    	}
    	//invalidate session if exists
        HttpSession session = req.getSession(false);
        System.out.println("User=" + session.getAttribute("user"));
        if(session !=null) {
        	session.invalidate();
        }
        res.sendRedirect("login.jsp");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	doPost(req, res);
    	
    }
}
