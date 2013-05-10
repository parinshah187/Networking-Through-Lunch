<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*;" %>

<%
Connection connect = null;
Statement statement = null;
ResultSet rs = null;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display table</title>
</head>
<body>
<%
try {
	//System.out.println("hi");
// This will load the MySQL driver, each DB has its own driver
Class.forName("com.mysql.jdbc.Driver").newInstance();
connect = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=abcd1234");
statement = connect.createStatement();
}catch(Exception e) {
	System.out.println("Golmaal");
}
int rowCount = 0;
rs = statement.executeQuery("select * from test.todayslunchpairs;");
%>
<table>
	<tr>
		<th>Emp_Id</th>
		<th>Name</th>
	</tr>
	<%
	try {
		while (rs.next()) {%>
			<tr>
				<td><%=rs.getString(1)%></td>
				<td><%=rs.getString(2)%></td>
			</tr>
		<%}
	} catch (SQLException e) {
		e.printStackTrace(System.out);
	} finally {
		try {statement.close();} catch (Exception e) {
		}
		try {rs.close();} catch (Exception e) {
		}
		try {
			if (null != connect) {connect.close();}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}
	%>
</table>
</body>
</html>