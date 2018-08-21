<%@page import="admin.Bean_Admin_NewAccount"%>
<%@page import="org.hibernate.Session,org.hibernate.SessionFactory,org.hibernate.Transaction,org.hibernate.cfg.Configuration"%>
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
/* Style the tab */
.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
    font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    display: none;
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}
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
				  <a href="Login.jsp" class="a">Log Out</a><br>
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
		<br><br><br><br><br><br><br><br><br><br>
		
			
		
		<%
		Configuration cfg=new Configuration();
		Configuration c =cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=c.buildSessionFactory();
		Session se=sf.openSession();
		Transaction tr=se.beginTransaction();
		
		Query query = se.createQuery("FROM Bean_Admin_NewAccount  where username='"+s+"'"); 
        List<Bean_Admin_NewAccount> objectList =  query.list();
        String st = objectList.get(0).getAccount_number();
		%>
		
		<p>Choose your Service</p>
		
		<div class="tab">
		  <button class="tablinks" onclick="openCity(event, 'ATM')">Block ATM Card</button>
		  <button class="tablinks" onclick="openCity(event, 'Credit')">Block Credit Card</button>
		  <button class="tablinks" onclick="openCity(event, 'Cheque')">Block Cheque Book</button>
		</div>
		
		<div id="ATM" class="tabcontent">
			<form action="BlockCards" method="post">
		  	<table cellpadding="10px" cellspacing="30px" style="margin-left: 350px;">
		  		<tr>
		  			<td>Service</td>
		  			<td><input type="text" name="service" readonly="readonly" value="ATM"></td>
		  		</tr>
		  		<tr>
		  			<td>Enter Your Account Number</td>
		  			<td><input type="text" name="accountNumber" readonly="readonly" value="<%=st %>">
		  		</tr>
		  		<tr>
		  			<td>Enter Your Account Password</td>
		  			<td><input type="password" name="accountpassword">
		  		</tr>
		  		<tr>
		  			<td colspan="2" align="center"><input type="submit"></td>
		  		</tr>
		  	</table>
		  	</form>
		</div>
		
		<div id="Credit" class="tabcontent">
		  <form action="BlockCards" method="post">
		  	<table cellpadding="10px" cellspacing="30px" style="margin-left: 350px;">
		  		<tr>
		  			<td>Service</td>
		  			<td><input type="text" name="service" readonly="readonly" value="Credit"></td>
		  		</tr>
		  		<tr>
		  			<td>Enter Your Account Number</td>
		  			<td><input type="text" name="accountNumber" readonly="readonly" value="<%=st %>">
		  		</tr>
		  		<tr>
		  			<td>Enter Your Account Password</td>
		  			<td><input type="password" name="accountpassword">
		  		</tr>
		  		<tr>
		  			<td colspan="2" align="center"><input type="submit"></td>
		  		</tr>
		  	</table>
		  	</form>
		</div>
		
		<div id="Cheque" class="tabcontent">
		  <form action="BlockCards" method="post">
		  	<table cellpadding="10px" cellspacing="30px" style="margin-left: 350px;">
		  		<tr>
		  			<td>Service</td>
		  			<td><input type="text" name="service" readonly="readonly" value="Cheque"></td>
		  		</tr>
		  		<tr>
		  			<td>Enter Your Account Number</td>
		  			<td><input type="text" name="accountNumber" readonly="readonly" value="<%=st %>">
		  		</tr>
		  		<tr>
		  			<td>Enter Your Account Password</td>
		  			<td><input type="password" name="accountpassword">
		  		</tr>
		  		<tr>
		  			<td colspan="2" align="center"><input type="submit"></td>
		  		</tr>
		  	</table>
		  	</form>
		</div>
		
		<script>
		function openCity(evt, cityName) {
		    var i, tabcontent, tablinks;
		    tabcontent = document.getElementsByClassName("tabcontent");
		    for (i = 0; i < tabcontent.length; i++) {
		        tabcontent[i].style.display = "none";
		    }
		    tablinks = document.getElementsByClassName("tablinks");
		    for (i = 0; i < tablinks.length; i++) {
		        tablinks[i].className = tablinks[i].className.replace(" active", "");
		    }
		    document.getElementById(cityName).style.display = "block";
		    evt.currentTarget.className += " active";
		}
		</script>
     <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>