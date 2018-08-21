<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="CSS/Login.css">
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
				 
				 
			</div>
			
		</nav><br><br><br><br><br><br><br><br><br><br><br>

	<div id="nav_i">
		<form action="UserLogin" method="Post">
			<span>UseName</span><br>
			<input type="text" name="user"><br><br>
			<span>Password</span><br>
			<input type="Password" name="pass"><br>
			<input id="btn" align="center" type="submit" value="submit" name="submit">
		</form>
	</div><br><br><br><br><br><br><br><br>
	</div>
</body>
</html>