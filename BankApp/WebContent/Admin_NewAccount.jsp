<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AdminIndex</title>
<link rel="stylesheet" type="text/css" href="CSS/Admin_NewAccount.css">
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
		</nav><br><br><br><br><br><br><br><br><br><br>
		
		<form action="AdminNewAccount" method="Post">
			<table cellpadding="5px;" cellspacing="25px;" style="margin-left: 420px; border: 1px solid black;">
				<tr>
					<td>Account holder's Name</td>
					<td><input type="text" name="accountHolderName"></td>
				</tr>
				<tr>
					<td>Account Number</td>
					<td><input type="text" name="accountNumber"></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastName"></td>
				</tr>
				<tr>
					<td>Date of Birth</td>
					<td><input type="date" name="dob"></td>
				</tr>
				<tr>
					<td>Age</td>
					<td><input type="text" name="age"></td>
				</tr>
				<tr>
					<td>Mobile</td>
					<td><input type="text" name="mobile" pattern="[1-9]{10}" title="Please enter 12 digit numbers only"></td>
				</tr>
				<tr>
					<td>Aadhaar Number</td>
					<td><input type="text" name="aadhaar" pattern="[1-9]{12}" title="Please enter 12 digit numbers only"></td>
				</tr>
				<tr>
					<td>Pan Number</td>
					<td><input type="text" name="pan" pattern="[1-9A-Z]{10}" title="Please enter 10 digit Number and Alphabet"></td>
				</tr>
				<tr>
					<td>Nominee Name</td>
					<td><input type="text" name="nominee"></td>
				</tr>
				<tr>
					<td>Account Type</td>
					<td><select style="width: 174px;" name="accountType">
						<option>SB</option>
						<option>DB</option>
						<option>RD</option>
					</select></td>
				</tr>
				<tr>
					<td>ATM Card</td>
					<td><select style="width: 174px;" name="atmCard">
						<option>Issued</option>
						<option>Re-Issued</option>
						<option>Not-Issued</option>
					</select></td>
				</tr>
				<tr>
					<td>Credit Card</td>
					<td><select style="width: 174px;" name="creditCard">
						<option>Not-Issued</option>
						<option>Issued</option>
						<option>Re-Issued</option>
					</select></td>
				</tr>
				<tr>
					<td>Cheque Book</td>
					<td><select style="width: 174px;" name="chequeBook">
						<option>Issued</option>
						<option>Re-Issued</option>
						<option>Not-Issued</option>
					</select></td>
				</tr>
				<tr>
					<td>UserName</td>
					<td><input type="text" name="userName" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><input type="submit" name="submit" value="submit"></td>
				</tr>
				
			</table>
		</form>
		<br><br><br><br><br><br><br>
	</div>
</body>
</html>