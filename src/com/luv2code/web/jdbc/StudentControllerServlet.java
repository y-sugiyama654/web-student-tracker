package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;

	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// list the student   ... in MVC fashion
		listStudents(request, response);
	}

	private listStudents(HttpServeltrequest request, HttpServeltResponse response) {
		// get students form db util
		List<Student> students = studentDbUtil.getStudents();

		// add students to the request
		request.setAttribute("STUDENT_LIST", students);

		// send to JSP page
	}

	@Override
	public void init() throws ServletException {
		super.init();

		// create our student db util
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
