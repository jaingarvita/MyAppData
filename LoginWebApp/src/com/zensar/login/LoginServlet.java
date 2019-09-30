package com.zensar.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.sql.Connection;
import com.zensar.dao.UserDao;
import com.zensar.dao.UserDaoImpl;
import com.zensar.entities.User;
import com.zensar.exception.ServiceException;
import com.zensar.services.UserService;
import com.zensar.services.UserServiceImpl;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Call business layer Component
	private UserService  userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ServletContext context = config.getServletContext();
		    String driverClassName = context.getInitParameter("jdbcDriver");
			String dbUrl = context.getInitParameter("jdbcUrl");
			String dbUsername = context.getInitParameter("dbUser");
			String dbPassword = context.getInitParameter("dbPassword");
		
		try {
			//load jdbc driver class
			Class.forName(driverClassName);
			context.log("JDBC Driver is loaded successfully!");
			Connection con = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
			context.log("Mysql database connection is established successfully");
			
			
			//create userdao obeject
			UserDao userDao = new UserDaoImpl();
			((UserDaoImpl) userDao).setConnection(con);

//create userservie object 
			UserService userService = new UserServiceImpl();
		 ((UserServiceImpl) userService).setUserDao(userDao);
			
			//set userservice to a servlet
			setUserService(userService);
			context.log("UserService is set in LoginServlet");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet called");
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("passwd");
		User clientUser = new User();
		clientUser.setUsername(username);
		clientUser.setPassword(password);
		PrintWriter out = response.getWriter();
		
		try {
			//invoking business logic of business layer is presentation layer 
			if(userService.validateUser(clientUser))
			{
				
				//out.println("<p> Dear " + username + " Welcome to Online Shopping</p>");
				RequestDispatcher rd = request.getRequestDispatcher("welcome");
				rd.forward(request, response );
				
			}
			else
			{
				
				out.println("<p>Sorry! Invalid username or password</p>");
		
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("doPost is called");
		doGet(request, response);
	}

	

}
