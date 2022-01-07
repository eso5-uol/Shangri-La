package net.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.test.dao.TestDAO;
import net.test.models.Test;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TestDAO testDao = new TestDAO();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Test test = new Test();
			PrintWriter out= res.getWriter();
			res.setContentType("text/html");
			String fullname = req.getParameter("fullname");
			test.setFullName(fullname);
			String age = req.getParameter("age");
			int i = Integer.parseInt(age);
			test.setAge(i);
			
			String gender = req.getParameter("gender");
			test.setGender(gender);
	
			String address = req.getParameter("address");
			test.setAddress(address);
			
			String postcode = req.getParameter("postcode");
			test.setPostcode(postcode);
			
			String testresult = req.getParameter("testresult");
			test.setTestResult(testresult);
	
			
			
			String email = req.getParameter("email");
			try {
				if(email!=null && email.length()!=0) {
					if(testDao.searchTests(email)==false) {
						out.println("The provided email is already associated with another HomeTestKit");
					}else {
						test.setEmail(email);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
	
			String ttn = req.getParameter("ttn");
			try {
				if(ttn!=null && ttn.length()!=0) {
					if(testDao.searchTestsTTN(ttn) == false) {
						out.println("TTN code does not match record in the database");
					}else {
						testDao.search2(ttn);
						test.setTtn(ttn);
					}
				}else {
				out.println("ttn is not provided");
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			out.close();
			try {
				testDao.submitTest(test);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}finally {
			RequestDispatcher dispatcher = req.getRequestDispatcher("success.jsp");
			dispatcher.forward(req, res);
			
		}
	
}}
