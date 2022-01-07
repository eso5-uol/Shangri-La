package net.test.controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.test.dao.UserDAO;
import net.test.models.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	public void doPost(HttpServletRequest req, 
			        HttpServletResponse res) 
	            throws ServletException, IOException {

			
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		UserDAO userDao=new UserDAO();
		try {
			
			if (userDao.checkLogin(username, password) != null) {
				HttpSession se=req.getSession();
				se.setAttribute("user", username);
				Cookie userName = new Cookie("user", username);
				userName.setMaxAge(30*60);
				res.addCookie(userName);
				res.sendRedirect("admin.jsp");
			}else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				PrintWriter out = res.getWriter();
				out.println("Incorrect Login Info");
				rd.include(req, res);
				res.sendRedirect("error.jsp?errorid=1");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		
		
	
	}
	
		

}

	
