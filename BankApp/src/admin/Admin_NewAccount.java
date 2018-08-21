package admin;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Servlet implementation class Admin_NewAccount
 */
@WebServlet("/AdminNewAccount")
public class Admin_NewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_NewAccount() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		String str=request.getParameter("submit");
		try {
			
			Configuration cfg=new Configuration();
			Configuration c =cfg.configure("hibernate.cfg.xml");
			SessionFactory sf=c.buildSessionFactory();
			Session session=sf.openSession();
			if(str.equals("submit")) {
			Transaction tr=session.beginTransaction();
			
			Bean_Admin_NewAccount add=new Bean_Admin_NewAccount();
			
			add.setAccount_holder_name(request.getParameter("accountHolderName"));
			add.setAccount_number(request.getParameter("accountNumber"));
			add.setLast_name(request.getParameter("lastName"));
			add.setDateofbirth(request.getParameter("dob"));
			add.setAge(Integer.parseInt(request.getParameter("age")));
			add.setMobile_number(request.getParameter("mobile"));
			add.setAadhaar_number(request.getParameter("aadhaar"));
			add.setPan_number(request.getParameter("pan"));
			add.setNominee_name(request.getParameter("nominee"));
			add.setAccount_type(request.getParameter("accountType"));
			add.setAtm_card(request.getParameter("atmCard"));
			add.setCredit_card(request.getParameter("creditCard"));
			add.setCheque_book(request.getParameter("chequeBook"));
			add.setUsername(request.getParameter("userName"));
			add.setPassword(request.getParameter("password"));
			
			session.save(add);
			tr.commit();
			RequestDispatcher rd=request.getRequestDispatcher("AdminIndex.jsp");
			rd.include(request, response);
			
			out.println("<html><body><script>alert(\"Account Created Successfully\");"
					+ "</script></body></html>");
			
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("Admin_NewAccount.jsp");
				rd.include(request, response);
				
				out.println("<html><body><script>alert(\"Account Created is not Successful\");"
						+ "</script></body></html>");
				
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
