package User;

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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import admin.Bean_Admin_NewAccount;

/**
 * Servlet implementation class BlockCards
 */
@SuppressWarnings("deprecation")
@WebServlet("/BlockCards")
public class BlockCards extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlockCards() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		
		HttpSession hs=request.getSession();
		String s=hs.getAttribute("user").toString();
		
		try {
					
			Configuration cfg=new Configuration();
			Configuration c =cfg.configure("hibernate.cfg.xml");
			SessionFactory sf=c.buildSessionFactory();
			Session session=sf.openSession();
			Transaction tr=session.beginTransaction();
			
			String accnumber=request.getParameter("accountNumber");
			String accpassword=request.getParameter("accountpassword");
			String service=request.getParameter("service");
			
			Query query= session.createQuery("FROM Bean_Admin_NewAccount  where username='"+s+"'"); 
	        List<Bean_Admin_NewAccount> objectList =  query.list();
	        String pass=objectList.get(0).getPassword();
	        String atm=objectList.get(0).getAtm_card();
	        String credit=objectList.get(0).getCredit_card();
	        String cheque=objectList.get(0).getCheque_book();
	        
	        if(pass.equals(accpassword)) {
	        	if(service.equals("ATM")) {
	        		if(atm.equals("Issued")) {
	        	String hql1 = "UPDATE Bean_Admin_NewAccount set atm_card = 'Not-Issued' where account_number = '"+accnumber+"'";
				Query qr1 = session.createQuery(hql1);
				qr1.executeUpdate();
				out.println("<script>"
						+ "alert(\"Your ATM card is Blocked.Check Your mail for Confirmation\");"
						+ "</script>");
				RequestDispatcher rd=request.getRequestDispatcher("BlockCards.jsp");
				rd.include(request, response);
				MailServices.send("aboutservlets@gmail.com","about@123","aboutservlets@gmail.com","ATM Card Blocking","Your ATM is Blocked by Your request");
	        		}else {
	        			out.println("<script>"
	    						+ "alert(\"Currently You don't have any ATM card linked with your Account\");"
	    						+ "</script>");
	    				RequestDispatcher rd=request.getRequestDispatcher("BlockCards.jsp");
	    				rd.include(request, response);
	        		}
	        	}else if (service.equals("Credit")) {
	        		if(credit.equals("Issued")) {
	        		String hql1 = "UPDATE Bean_Admin_NewAccount set credit_card = 'Not-Issued' where account_number = '"+accnumber+"'";
					Query qr1 = session.createQuery(hql1);
					qr1.executeUpdate();
					out.println("<script>"
							+ "alert(\"Your Credit card is Blocked.Check Your mail for Confirmation\");"
							+ "</script>");
					RequestDispatcher rd=request.getRequestDispatcher("BlockCards.jsp");
					rd.include(request, response);
					MailServices.send("aboutservlets@gmail.com","about@123","aboutservlets@gmail.com","Credit Card Blocking","Your Credit Card is Blocked by Your request");
	        		}else {
	        			out.println("<script>"
	    						+ "alert(\"Currently You don't have any Credit card linked with your Account\");"
	    						+ "</script>");
	    				RequestDispatcher rd=request.getRequestDispatcher("BlockCards.jsp");
	    				rd.include(request, response);
	        		}
				}else if (service.equals("Cheque")) {
					if(cheque.equals("Issued")) {
	        		String hql1 = "UPDATE Bean_Admin_NewAccount set cheque_book = 'Not-Issued' where account_number = '"+accnumber+"'";
					Query qr1 = session.createQuery(hql1);
					qr1.executeUpdate();
					out.println("<script>"
							+ "alert(\"Your Cheque Book is Blocked.Check Your mail for Confirmation\");"
							+ "</script>");
					RequestDispatcher rd=request.getRequestDispatcher("BlockCards.jsp");
					rd.include(request, response);
					MailServices.send("aboutservlets@gmail.com","about@123","aboutservlets@gmail.com","Cheque Book Blocking","Your Cheque Book is Blocked by Your request");
					}else {
	        			out.println("<script>"
	    						+ "alert(\"Currently You don't have any Cheque Book linked with your Account\");"
	    						+ "</script>");
	    				RequestDispatcher rd=request.getRequestDispatcher("BlockCards.jsp");
	    				rd.include(request, response);
	        		}
				}
	        }else {
	        	out.println("<script>"
						+ "alert(\"Please Check Your password And Then Enter it Correctly.\");"
						+ "</script>");
			}
	        tr.commit();
		
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
