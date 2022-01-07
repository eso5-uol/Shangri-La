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

import net.test.dao.TestDAO;
import net.test.models.Test;

/**
 * Servlet implementation class GetTestServlet
 */
@WebServlet("/")
public class GetTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestDAO testDao = new TestDAO();

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/showtest":
				listTest(request, response);
				break;
			
			case "/showpos":
				listPositive(request, response);
				break;
		
			case "/showneg":
				listNegative(request, response);
				break;
			}
		}catch(Exception ex){
			throw new ServletException(ex);
		}
	
			}
	private void listTest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Test> listTest = testDao.selectAllTests();
		request.setAttribute("listTest", listTest);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listPositive(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Test> listTestPos = testDao.allTests("Positive");
		request.setAttribute("listTestPos", listTestPos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		dispatcher.forward(request, response);
	}

	
	
	private void listNegative(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Test> listTestNeg = testDao.allTests("Negative");
		request.setAttribute("listTestNeg", listTestNeg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		dispatcher.forward(request, response);
	}


}
