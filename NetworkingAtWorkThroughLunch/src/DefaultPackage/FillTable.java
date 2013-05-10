// This program fills todaysemployee table for 200 to 209 empIds for testing purpose.

package DefaultPackage;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class FillTable {

	/**
	 * @param args
	 */
	public void function() {
		try {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//Setup the connection with DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=abcd1234");
			
			//Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			
//			statement.executeUpdate("drop table `test`.`todaysemployees`;");
//			statement.executeUpdate("CREATE  TABLE `test`.`TodaysEmployees` (`Emp_Id` INT NOT NULL ,  PRIMARY KEY (`Emp_Id`) ,  CONSTRAINT `fk1`    FOREIGN KEY (`Emp_Id` )    REFERENCES `test`.`employee` (`Emp_Id` )    ON DELETE CASCADE    ON UPDATE CASCADE);");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('200');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('201');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('202');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('203');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('204');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('205');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('206');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('207');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('208');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('209');");
//			statement.executeUpdate("INSERT INTO `test`.`todaysemployees` (`Emp_Id`) VALUES ('210');");
			
			//statement.executeQuery("CREATE TABLE test.emp1 (  `Emp_Id` int(11) NOT NULL,  `Name` varchar(45) NOT NULL,  PRIMARY KEY (`Emp_Id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8$$");
			statement.executeUpdate("delete from test.employee");
			statement.executeUpdate("delete from test.log");
			statement.executeUpdate("delete from test.todaysemployees");
			System.out.println("Table Created in MySQL !");
			statement.executeUpdate("LOAD DATA INFILE 'E:/upload/emp1.csv' INTO TABLE test.employee fields terminated by ',' lines terminated by '\n'");
			System.out.println("Data loaded from external file to MySQL database");
			System.out.println("Fetching available employees for today...");
			statement.executeUpdate("Insert into test.todaysemployees (Emp_Id) select Emp_Id from test.employee");
			System.out.println("Today's employees Fetched");
			statement.executeUpdate("delete from test.log");
			statement.executeUpdate("Insert into test.log (Emp_Id) select Emp_Id from test.employee");
			System.out.println("Employees entries done in log table");
			LogMaintenanceForWeek5 object = new LogMaintenanceForWeek5();
			object.function();
			
			} catch(Exception e) {
			System.out.println("Golmaal");
			e.printStackTrace();
		}
	}
}
