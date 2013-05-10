<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log Table</title>
<style type="text/css">
  .topshadow {
  height: 140px;
  width: 140%;
  margin: -62px;
  background-image:url('http://www.psdgraphics.com/file/red-stage.jpg'); 
  position: absolute; 
  }
.middleshadow {
  top:95px;
  height: 6px;
  width: 140%;
  margin: -10px;
  background-image:url('http://crownedwithhisglory.weebly.com/uploads/1/6/9/6/16963932/8769914.png'); 
  position: absolute; 
  }  
  
body {
background:url('http://img714.imageshack.us/img714/6058/gray16.png');
}

h1 {

  color:#F1F8E0;
  font-size: 25px; 
  position: relative; 
}

h3 {

  color:#F1F8E0;
  font-size:22px; 
  position: relative; 
}

h6 {

  color:#F1F8E0;
  font-size:12px; 
  position: relative; 
}

.table1 {
    float:center;
    margin-left:220px;
  }
 
.table2 {
    float:center;
    margin-left:600px; 
  }  
 
td, th
{
border:2px solid black;
background-color:#610B0B;
color:#F1F8E0;


}

.td1 {
background-color:#151515;
}

p {
  
  color:#F1F8E0;

}
.divmargin {

margin-left:0px;

}

.button {
   border-top: 1px solid #B40404;
   background: #FA8258;
   
  }
  
.button:hover {
   border-top-color: #28597a;
   background: #28597a;
   color: #ccc;
   }
   
 </style>
</head>
<body>
<div class="topshadow">
</div>

<div class="middleshadow">
</div>
<br/>
<h1 style ="font-family:Lucida Console" > &nbsp; &nbsp; Networking at work through lunch </h1>
<h3 style="font-family:verdana;" align="center"> Log Table </h3>
<%
try {
	Connection connect = null;
	Statement statement = null;
	ResultSet rs = null;
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	connect = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=abcd1234");
	statement = connect.createStatement();
	String QueryString = "SELECT * from test.log";
	rs = statement.executeQuery(QueryString);
	%>
	<table class="table3" border="1" align="center">
<tr>
<th>Emp_ID</th>
<th>Monday</th>
<th>Tuesday</th>
<th>Wednesday</th>
<th>Thursday</th>
<th>Friday</th>
</tr>
<%
while (rs.next()) {
%>

<tr>
<td class="td1" align="center"><%=rs.getString(1)%></td>
<td class="td1" align="center"><%=rs.getString(2)%></td>
<td class="td1" align="center"><%=rs.getString(3)%></td>
<td class="td1" align="center"><%=rs.getString(4)%></td>
<td class="td1" align="center"><%=rs.getString(5)%></td>
<td class="td1" align="center"><%=rs.getString(6)%></td>
</tr>
<% } %>
<%
rs.close();
statement.close();
connect.close();
} catch (Exception ex) {
	ex.printStackTrace();
out.println("Unable to connect to database.");
}
%>
</table> 
	<a href = "Start.jsp"> Home</a>
<br/> 
<br/> 
</body>
</html>