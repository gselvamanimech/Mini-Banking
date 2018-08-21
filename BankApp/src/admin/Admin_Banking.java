package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/**
 * Servlet implementation class Admin_Banking
 */
@WebServlet("/AdminBanking")
public class Admin_Banking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Banking() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  
 
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		PrintWriter out=response.getWriter();
		
		   BigInteger netamountI = BigInteger.ZERO;
		   BigInteger bcredit=BigInteger.ZERO;
		   BigInteger bcredit1=BigInteger.ZERO;
		   BigInteger bcredit3=BigInteger.ZERO;
		   String st = null;
		   String  debit=null;
		   String credit = null;
		try {
			
			Configuration cfg=new Configuration();
			Configuration c =cfg.configure("hibernate.cfg.xml");
			SessionFactory sf=c.buildSessionFactory();
			Session session=sf.openSession();
			Transaction tr=session.beginTransaction();
			
			Bean_AdminBanking bank=new Bean_AdminBanking();
			Bean_NetAmount amt=new Bean_NetAmount();
			LocalDate date=LocalDate.now();

			//Store values for list of transaction
			bank.setDate(date.toString());
			bank.setAccountnumber(request.getParameter("accnumber"));
			bank.setAccountholdername(request.getParameter("accholdername"));
			bank.setFromwhom(request.getParameter("bywhom"));
			 credit=request.getParameter("credit");
			 debit=request.getParameter("debit");
			if (debit.isEmpty()) {
				bank.setCreditamount(credit);
				
			}else if (credit.isEmpty()) {
				bank.setDebitamount(debit);
				
			}
			
			session.save(bank);
			
			//Result set for net amount
			Query query = session.createQuery("FROM Bean_NetAmount  where accountholdername='"+bank.getAccountholdername()+"'"); 
	        List<Bean_NetAmount> objectList =  query.list();
	        st = objectList.get(0).getNetamount();
	       
			//Store values for adding and subtraction in net amount
			//amt.setDate(date.toString());
			//amt.setAccountholdername(bank.getAccountholdername());
			//amt.setAccountnumber(bank.getAccountnumber());

			bcredit=new BigInteger(st);
		 
			if (debit.isEmpty()) {
				bcredit1=new BigInteger(credit);
				//amt.setCredit(credit);
				
				netamountI=bcredit.add(bcredit1);
				out.println("<script>"
						+ "alert(\"Amount Deposited\");"
						+ "</script>");
				
			}else if (credit.isEmpty()) {
				 bcredit3=new BigInteger(debit);
				
				netamountI=bcredit.subtract(bcredit3);
				
				out.println("<script>"
						+ "alert(\"Amount Deb ited\");"
						+ "</script>");
			}
			amt.setNetamount(netamountI.toString());
			
			
			String hql = "UPDATE Bean_NetAmount set netamount = '"+netamountI.toString()+"' where accountnumber = '"+bank.getAccountnumber()+"'";
			Query qr = session.createQuery(hql);
			int n=qr.executeUpdate();
			tr.commit();
			
			RequestDispatcher rd=request.getRequestDispatcher("Admin_Banking.jsp");
			rd.include(request, response);
			
			
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
