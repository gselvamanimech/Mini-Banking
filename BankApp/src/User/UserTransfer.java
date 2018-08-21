package User;

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
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import admin.Bean_AdminBanking;
import admin.Bean_Admin_NewAccount;
import admin.Bean_NetAmount;

/**
 * Servlet implementation class UserTransfer
 */
@SuppressWarnings("deprecation")
@WebServlet("/UserTransfer")
public class UserTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		PrintWriter out=response.getWriter();
		Bean_AdminBanking adminBank=new Bean_AdminBanking();
		
		LocalDate date=LocalDate.now();
		BigInteger sendertAmount = BigInteger.ZERO;
		BigInteger sendernetAmount = BigInteger.ZERO;
		BigInteger sendertotalAmount = BigInteger.ZERO;
		BigInteger receivernetAmount = BigInteger.ZERO;
		BigInteger receivertotalAmount = BigInteger.ZERO;
		
		HttpSession hs=request.getSession();
		String s=hs.getAttribute("user").toString();
		
		try {
			
			Configuration cfg=new Configuration();
			Configuration c =cfg.configure("hibernate.cfg.xml");
			SessionFactory sf=c.buildSessionFactory();
			Session session=sf.openSession();
			Transaction tr=session.beginTransaction();
			
			String receiveraccno=request.getParameter("toaccountnumber");
			String receiveraccname=request.getParameter("toaccountholdername");
			String amount=request.getParameter("transferamount");
			int sendAmount=Integer.parseInt(amount);
			
			sendertAmount=new BigInteger(amount);
			
			//Sender Details Starts
			Query query= session.createQuery("FROM Bean_Admin_NewAccount  where username='"+s+"'"); 
	        List<Bean_Admin_NewAccount> objectList =  query.list();
	        String st=objectList.get(0).getAccount_number();
			String name=objectList.get(0).getAccount_holder_name();
			
	        Query query1 = session.createQuery("FROM Bean_NetAmount  where accountnumber='"+st+"'"); 
	        List<Bean_NetAmount> objectList1 =  query1.list();
	        String sendernet=objectList1.get(0).getNetamount();	
		    int senderpreNetAmount=Integer.parseInt(sendernet);    
	       
			if ((senderpreNetAmount-1000)<=sendAmount) {
				out.println("Transfer Amount is Less than NetAmount or Deposit Amount");
			}else if ((senderpreNetAmount-1000)>=sendAmount) {
				sendernetAmount=new BigInteger(sendernet);
				
				
				sendertotalAmount=sendernetAmount.subtract(sendertAmount);
				
				String hql = "UPDATE Bean_NetAmount set netamount = '"+sendertotalAmount+"' where accountnumber = '"+st+"'";
				Query qr = session.createQuery(hql);
				
				qr.executeUpdate();
				
				
				
				
				adminBank.setDate(date.toString());
				adminBank.setAccountnumber(st);
				adminBank.setAccountholdername(name);
				adminBank.setFromwhom(name);
				adminBank.setCreditamount(null);
				adminBank.setDebitamount(amount);
				
				session.save(adminBank);
				session.flush();
				session.clear();
				
			}
			//sender Details Ends
			
			
			//Receiver Details Starts
			
				Query query2 = session.createQuery("FROM Bean_NetAmount  where accountnumber='"+receiveraccno+"'"); 
		        List<Bean_NetAmount> objectList2 =  query2.list();
		        String receivernet=objectList2.get(0).getNetamount();	
				
			    receivernetAmount=new  BigInteger(receivernet);
			    if ((senderpreNetAmount-1000)>=sendAmount) {
			    	receivertotalAmount=receivernetAmount.add(sendertAmount);
				}
			    
			
			    String hql1 = "UPDATE Bean_NetAmount set netamount = '"+receivertotalAmount+"' where accountnumber = '"+receiveraccno+"'";
				Query qr1 = session.createQuery(hql1);
				
				qr1.executeUpdate();
		       				
				Bean_AdminBanking adminBanking=new Bean_AdminBanking();
				
				adminBanking.setDate(date.toString());
				adminBanking.setAccountnumber(receiveraccno);
				adminBanking.setAccountholdername(receiveraccname);
				adminBanking.setFromwhom(name);
				adminBanking.setCreditamount(amount);
				adminBanking.setDebitamount(null);
				
				session.save(adminBanking);
		    
			//Receiver Details Ends
				
				
			tr.commit();
			RequestDispatcher rd=request.getRequestDispatcher("UserIndex.jsp");
			rd.include(request, response);
			
			out.print("<script>"
					+ "alert(\"Amount Transfer Successfull\");"
					+ "</script>");
			
			
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
