package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Servlet implementation class User_Login
 */
@WebServlet("/UserLogin")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String btn = request.getParameter("submit");
		
		try {
			Configuration cfg=new Configuration();
			Configuration c=cfg.configure("hibernate.cfg.xml");
			SessionFactory sf=c.buildSessionFactory();
			Session session=sf.openSession();
			
			String username1=request.getParameter("user");
			String password1=request.getParameter("pass");
			
			
			if(btn.equals("submit")) {
				
				Query query = session.createSQLQuery("SELECT username,password FROM user_registration WHERE username='"+username1+"' and password='"+password1+"'"); 
		        
				List<Object[]> objectList =  query.list();
				
				HttpSession hs=request.getSession();
				hs.setAttribute("user",username1);
		      
					if(objectList.isEmpty()) {
						RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
						rd.include(request, response);
						out.println("<script>"
								+ "alert(\"Check your UserName and Password.\");"
								+ "</script>");
					}else {
						if(username1.equals("Admin1")) {
							response.sendRedirect("AdminIndex.jsp");
						}else {
						response.sendRedirect("UserIndex.jsp");
						}
					}
				 
		       
			}
		
			}catch (Exception e) {
				System.out.println(e);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
