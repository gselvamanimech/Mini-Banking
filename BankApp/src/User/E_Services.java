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
 * Servlet implementation class E_Services
 */
@SuppressWarnings("deprecation")
@WebServlet("/EServices")
public class E_Services extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public E_Services() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs=request.getSession();
		String s=hs.getAttribute("user").toString();
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		LocalDate date=LocalDate.now();
		
		
		try {
			
			Configuration cfg=new Configuration();
			Configuration c =cfg.configure("hibernate.cfg.xml");
			SessionFactory sf=c.buildSessionFactory();
			Session session=sf.openSession();
			Transaction tr=session.beginTransaction();
			
			String services=request.getParameter("service");
			String mode=request.getParameter("mode");
			
			Query query= session.createQuery("FROM Bean_Admin_NewAccount  where username='"+s+"'"); 
	        List<Bean_Admin_NewAccount> objectList =  query.list();
	        String str=objectList.get(0).getAtm_card();
	        String credit=objectList.get(0).getCredit_card();
	        String Cheque=objectList.get(0).getCheque_book();
	        String accnumber=objectList.get(0).getAccount_number();
	        
	        Query query1= session.createQuery("FROM Bean_NetAmount  where accountnumber='"+accnumber+"'"); 
	        List<Bean_NetAmount> objectList1 =  query1.list();
	        String netAmount=objectList1.get(0).getNetamount();
	        BigInteger beforeNet=new BigInteger(netAmount);
	        BigInteger urgAmountATM=new BigInteger("1000");
	        BigInteger ordAmountATM=new BigInteger("300");
	        BigInteger urgAmountCredit=new BigInteger("1500");
	        BigInteger ordAmountCredit=new BigInteger("500");
	        BigInteger urgAmountCheque=new BigInteger("500");
	        BigInteger ordAmountCheque=new BigInteger("300");
	        BigInteger total=BigInteger.ZERO;
	        
	        Bean_AdminBanking bank=new Bean_AdminBanking();
	        bank.setDate(date.toString());
			bank.setAccountnumber(accnumber);
			bank.setAccountholdername(objectList.get(0).getAccount_holder_name());
			bank.setCreditamount(null);
			
	        if(services.equals("Atm Card")) {
	        	if((str.equals("Not-Issued")) && (mode.equals("Urgent"))) {
	        		
	        		total=beforeNet.subtract(urgAmountATM);
				    String hql1 = "UPDATE Bean_NetAmount set netamount = '"+total+"' where accountnumber = '"+accnumber+"'";
					Query qr1 = session.createQuery(hql1);
					qr1.executeUpdate();
					String hql2 = "UPDATE Bean_Admin_NewAccount set atm_card = 'Issued' where account_number = '"+accnumber+"'";
					Query qr2 = session.createQuery(hql2);
					qr2.executeUpdate();
					
					/*bank.setDate(date.toString());
					bank.setAccountnumber(accnumber);
					bank.setAccountholdername(objectList.get(0).getAccount_holder_name());*/
					bank.setFromwhom("Bank/ATM URG");
					//bank.setCreditamount(null);
					bank.setDebitamount(urgAmountATM.toString());
					
					RequestDispatcher rd=request.getRequestDispatcher("E-Services.jsp");
					rd.include(request, response);
					out.println("<script>"
							+ "alert(\"Your ATM request is Processing.Please check your E-mail for Confirmation.\");"
							+ "</script>");
					
					MailServices.send("Sender Mail","","Receiver Mail","Subject","Message");
	        	}else if ((str.equals("Not-Issued")) && (mode.equals("Ordinary"))) {
	        		total=beforeNet.subtract(ordAmountATM);
				    String hql1 = "UPDATE Bean_NetAmount set netamount = '"+total+"' where accountnumber = '"+accnumber+"'";
					Query qr1 = session.createQuery(hql1);
					qr1.executeUpdate();
					String hql2 = "UPDATE Bean_Admin_NewAccount set atm_card = 'Issued' where account_number = '"+accnumber+"'";
					Query qr2 = session.createQuery(hql2);
					qr2.executeUpdate();
					
					/*bank.setDate(date.toString());
					bank.setAccountnumber(accnumber);
					bank.setAccountholdername(objectList.get(0).getAccount_holder_name());*/
					bank.setFromwhom("Bank/ATM ORD");
					//bank.setCreditamount(null);
					bank.setDebitamount(ordAmountATM.toString());
					
					RequestDispatcher rd=request.getRequestDispatcher("E-Services.jsp");
					rd.include(request, response);
					out.println("<script>"
							+ "alert(\"Your ATM request is Processing.Please check your E-mail for Confirmation.\");"
							+ "</script>");
					
					MailServices.send("Sender Mail","","Receiver Mail","Subject","Message");
				}else if(str.equals("Issued")) {
					RequestDispatcher rd=request.getRequestDispatcher("E-Services.jsp");
					rd.include(request, response);
					out.println("<script>"
							+ "alert(\" You Already have your Atm card. If You want new ATM , You Have to Block your Current ATM card\");"
							+ "</script>");
					
				}
	        }else if (services.equals("Credit Card")) {
	        	if((credit.equals("Not-Issued")) && (mode.equals("Urgent"))) {
	        		
	        		total=beforeNet.subtract(urgAmountCredit);
				    String hql1 = "UPDATE Bean_NetAmount set netamount = '"+total+"' where accountnumber = '"+accnumber+"'";
					Query qr1 = session.createQuery(hql1);
					qr1.executeUpdate();
					String hql2 = "UPDATE Bean_Admin_NewAccount set credit_card = 'Issued' where account_number = '"+accnumber+"'";
					Query qr2 = session.createQuery(hql2);
					qr2.executeUpdate();
					
					/*bank.setDate(date.toString());
					bank.setAccountnumber(accnumber);
					bank.setAccountholdername(objectList.get(0).getAccount_holder_name());*/
					bank.setFromwhom("Bank/Credit URG");
					//bank.setCreditamount(null);
					bank.setDebitamount(urgAmountCredit.toString());
					
					RequestDispatcher rd=request.getRequestDispatcher("E-Services.jsp");
					rd.include(request, response);
					out.println("<script>"
							+ "alert(\" Your Credit card request is Processing.Please check your E-mail for Confirmation.\");"
							+ "</script>");
					
					MailServices.send("Sender Mail","","Receiver Mail","Subject","Message");				
	        	}else if ((credit.equals("Not-Issued")) && (mode.equals("Ordinary"))) {
	        		total=beforeNet.subtract(ordAmountCredit);
				    String hql1 = "UPDATE Bean_NetAmount set netamount = '"+total+"' where accountnumber = '"+accnumber+"'";
					Query qr1 = session.createQuery(hql1);
					qr1.executeUpdate();
					String hql2 = "UPDATE Bean_Admin_NewAccount set credit_card = 'Issued' where account_number = '"+accnumber+"'";
					Query qr2 = session.createQuery(hql2);
					qr2.executeUpdate();
					
					/*bank.setDate(date.toString());
					bank.setAccountnumber(accnumber);
					bank.setAccountholdername(objectList.get(0).getAccount_holder_name());*/
					bank.setFromwhom("Bank/Credit ORD");
					//bank.setCreditamount(null);
					bank.setDebitamount(ordAmountCredit.toString());
					
					RequestDispatcher rd=request.getRequestDispatcher("E-Services.jsp");
					rd.include(request, response);
					out.println("<script>"
							+ "alert(\" Your Credit card request is Processing.Please check your E-mail for Confirmation.\");"
							+ "</script>");
					
					MailServices.send("Sender Mail","","Receiver Mail","Subject","Message");
				}else if(credit.equals("Issued")) {
					RequestDispatcher rd=request.getRequestDispatcher("E-Services.jsp");
					rd.include(request, response);
					out.println("<script>"
							+ "alert(\" You Already have your Credit card.If You want new Credit Card , You Have to Block your Current Credit card\");"
							+ "</script>");
					
				}
				
			}else if (services.equals("Cheque Book")) {
	        	if((Cheque.equals("Not-Issued")) && (mode.equals("Urgent"))) {
	        		
	        		total=beforeNet.subtract(urgAmountCheque);
				    String hql1 = "UPDATE Bean_NetAmount set netamount = '"+total+"' where accountnumber = '"+accnumber+"'";
					Query qr1 = session.createQuery(hql1);
					qr1.executeUpdate();
					String hql2 = "UPDATE Bean_Admin_NewAccount set cheque_book = 'Issued' where account_number = '"+accnumber+"'";
					Query qr2 = session.createQuery(hql2);
					qr2.executeUpdate();
					
					/*bank.setDate(date.toString());
					bank.setAccountnumber(accnumber);
					bank.setAccountholdername(objectList.get(0).getAccount_holder_name());*/
					bank.setFromwhom("Bank/Cheque URG");
					//bank.setCreditamount(null);
					bank.setDebitamount(urgAmountCheque.toString());
					
					RequestDispatcher rd=request.getRequestDispatcher("E-Services.jsp");
					rd.include(request, response);
					out.println("<script>"
							+ "alert(\"Your Cheque book request is Processing.Please check your E-mail for Confirmation.\");"
							+ "</script>");
					MailServices.send("Sender Mail","","Receiver Mail","Subject","Message");			
	        	}else if ((Cheque.equals("Not-Issued")) && (mode.equals("Ordinary"))) {
	        		total=beforeNet.subtract(ordAmountCheque);
				    String hql1 = "UPDATE Bean_NetAmount set netamount = '"+total+"' where accountnumber = '"+accnumber+"'";
					Query qr1 = session.createQuery(hql1);
					qr1.executeUpdate();
					String hql2 = "UPDATE Bean_Admin_NewAccount set cheque_book = 'Issued' where account_number = '"+accnumber+"'";
					Query qr2 = session.createQuery(hql2);
					qr2.executeUpdate();
					
					/*bank.setDate(date.toString());
					bank.setAccountnumber(accnumber);
					bank.setAccountholdername(objectList.get(0).getAccount_holder_name());*/
					bank.setFromwhom("Bank/Cheque ORD");
					//bank.setCreditamount(null);
					bank.setDebitamount(ordAmountCheque.toString());
					
					RequestDispatcher rd=request.getRequestDispatcher("E-Services.jsp");
					rd.include(request, response);
					out.println("<script>"
							+ "alert(\"Your Cheque book request is Processing.Please check your E-mail for Confirmation.\");"
							+ "</script>");
					MailServices.send("Sender Mail","","Receiver Mail","Subject","Message");
				}else if(Cheque.equals("Issued")) {
					RequestDispatcher rd=request.getRequestDispatcher("E-Services.jsp");
					rd.include(request, response);
					out.println("<script>"
							+ "alert(\"You Already have your Cheque book.\nIf You want new ATM , You Have to Block your Current Cheque book.\");"
							+ "</script>");
					
				}
				
			}
	        session.save(bank);
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
