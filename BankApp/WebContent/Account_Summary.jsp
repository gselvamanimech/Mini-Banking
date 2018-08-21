<%@page import="admin.Bean_Admin_NewAccount"%>
<%@page import="admin.Bean_NetAmount,org.hibernate.Session,org.hibernate.SessionFactory,org.hibernate.Transaction,org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.Query,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AdminIndex</title>
<link rel="stylesheet" type="text/css" href="CSS/Login.css">
<style type="text/css">
	#acc{border: 1px;}
	table{margin-left:500px; }
</style>
</head>
<body bgcolor="#D0D0CE">
	<div id="root">
	<nav align="center">
			<div class="head">
				<div class="nav i"><img src="images/bank.png" class="nav-img"></div>
				<div class="nav c"><h2>Bank App</h2></div>
				
				<!-- <div class="nav s"><input type="text" name="search" placeholder="Search anything..." ></div> -->
			</div>
			
			<div class="link">
				<div class="li">
				  <span>My Account & Profile</span>
				  <div class="li-content1">
				   <a href="Account_Summary.jsp" class="a" style="color: black;">Account Summary</a><br>
				   <a href="E_Statement.jsp" class="a" style="color: black;">e-Statement</a>
				  </div>
				</div>
			 	
				<div class="li">
				  <span>Payment & Transfer</span>
				  <div class="li-content1">
				   <a href="Amount_Transfer.jsp" class="a" style="color: black;">Within Bank</a><br>
				   <a href="Join.html" class="a" style="color: black;">To Other Bank</a>
				  </div>
				</div>
								
				<div class="li">
				  <span>e-Services</span>
				  <div class="li-content2" style="width: 70px;">
				   <a href="E-Services.jsp" class="a" style="color: black;">Card Services</a><br>
				   <a href="BlockCards.jsp" class="a" style="color: black;">Block cards</a><br>
				   <a href="E-Services.jsp" class="a" style="color: black;">Cheque Book</a>
				  </div>
				</div>
				<div class="li">
				  <a href="Login.jsp" class="a" >Log Out</a><br>
				 </div>
				<div class="li" style="color: black;">
					<%
					HttpSession hs=request.getSession();
					String s=hs.getAttribute("user").toString();
					
					out.println("Welcome "+s);
					%>
				</div>

			</div>
			
		</nav>
		<br><br><br><br><br><br><br><br><br><br><br>
		
		<%
		Configuration cfg=new Configuration();
		Configuration c =cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=c.buildSessionFactory();
		Session se=sf.openSession();
		Transaction tr=se.beginTransaction();
		Bean_NetAmount amt=new Bean_NetAmount();
		String st,acc,am;
		Query query = se.createQuery("FROM Bean_Admin_NewAccount  where username='"+s+"'"); 
        List<Bean_Admin_NewAccount> objectList =  query.list();
        st = objectList.get(0).getAccount_holder_name();
        
        Query query1 = se.createQuery("FROM Bean_NetAmount  where accountholdername='"+st+"'"); 
        List<Bean_NetAmount> objectList1 =  query1.list();
        st = objectList1.get(0).getAccountholdername();
        acc=objectList1.get(0).getAccountnumber();
        am=objectList1.get(0).getNetamount();
		%>
		<br><br><br><br><br>
		
		<table cellpadding="5px;" cellspacing="40px;" style="margin-left: 400px; border: 1px solid black; column-rule: 2px;">
			<tr>
				<td>Account Number :</td>
				<td><input type="text" id="acc" readonly="readonly" value="<%= acc%>"></td>
			</tr>
			<tr>
				<td>AccountHolder Name :</td>
				<td><input type="text" id="acc" readonly="readonly" value="<%= st%>"></td>
			</tr>
			<tr>
				<td>Your Net Amount :</td>
				<td><input type="text" id="acc" readonly="readonly" value="<%= am+" INR"%>"></td>
			</tr>
		</table>
		
		
	</div>
</body>
</html>