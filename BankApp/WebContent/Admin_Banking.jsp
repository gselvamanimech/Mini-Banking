<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/Admin_NewAccount.css">
<title>Insert title here</title>

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
				 <a href="Admin_NewAccount.jsp" class="a" style="color: white;">New Account</a><br>
				</div>
				<div class="li">
				 <a href="Admin_Banking.jsp" class="a" style="color: white;">Banking</a><br>
				</div>
				<div class="li">
				  <a href="Login.jsp" class="a">Log Out</a><br>
				 </div>
			</div>
		</nav>
		
		<br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	<form action="AdminBanking" method="Post">
		<table cellspacing="20px;" width="450px;" style="border: 1px solid; margin-left: 450px;" >
			<tr>
				<td>Account Number</td>
				<td><input type="text" name="accnumber"></td>
			</tr>
			<tr>
				<td>Account Holder Name</td>
				<td><input type="text" name="accholdername"></td>
			</tr>
			<tr>
				<td>By Whom</td>
				<td><input type="text" name="bywhom"></td>
			</tr>
			<tr>
				<td>Credit Amount</td>
				<td><input type="text" name="credit"></td>
			</tr>
			<tr>
				<td>Debit Amount</td>
				<td><input type="text" name="debit"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="submit" value="submit"></td>
			</tr>
			
		</table>
	</form>
	</div>
</body>
</html>