// This program implements updates of log table when new pairs for particular day are formed.

package DefaultPackage;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class LogMaintenanceForWeek5 {
	@SuppressWarnings("deprecation")
	public void function() throws SQLException{
		Date d = new Date();
		String[] DayNames = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		System.out.println(d.getDay());
		String day = DayNames[d.getDay()-1]; // Make it -1 in weekdays
		System.out.println(day);
			Connection connect = null;
			Statement statement = null;
			ResultSet resultSet = null;
		try {
			//System.out.println("hi");
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		//Setup the connection with DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=abcd1234");
		
		//Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		}catch(Exception e) {
			System.out.println("Golmaal");
		}
		// Couting number of rows in TodaysEmployees table
		int rowCount = 0;
		resultSet = statement.executeQuery("select count(*) from TodaysEmployees;");
		while(resultSet.next()){
			rowCount = Integer.parseInt(resultSet.getString(1));
			System.out.println(rowCount);
		}
		int rowCountCopy = rowCount;
		int n = rowCount/2;
//		// Checking empIds with log of seven days to ensure they haven't paired up from last seven days
		Random r = new Random();
		
		// Clearing out the previous data of "TodaysLunchPairs" table data by dropping the table and creating it again
		statement.executeUpdate("drop table `test`.`TodaysLunchpairs`;");
		statement.executeUpdate("CREATE  TABLE `test`.`TodaysLunchpairs` (  `Person1` VARCHAR(30) NOT NULL ,`Person2` VARCHAR(30) NOT NULL );");
		String person1Copy = "";
		String person2Copy = "";
		String str_randomNumber2Copy="";
		// for loop for pairing up starts here
		for(int xLoopVariable=0;xLoopVariable<(n);xLoopVariable++)
		{
			resultSet = statement.executeQuery("select count(*) from TodaysEmployees;");
			while(resultSet.next()){
				rowCount = Integer.parseInt(resultSet.getString(1));
			}
			System.out.println("\nRowcount at xLoopvariable = "+xLoopVariable+" : "+rowCount);
			int r1 = r.nextInt(rowCount);
			System.out.println("r1 is : "+r1);
			if(r1>0)
				r1--;
			resultSet = statement.executeQuery("select * from TodaysEmployees limit 1 offset "+r1);
			String str_randomNumber1 = null;
			while(resultSet.next()){
				str_randomNumber1 = resultSet.getString(1);
			}
			System.out.println("random number 1 : "+str_randomNumber1);
			statement.executeUpdate("delete from TodaysEmployees where Emp_Id = "+str_randomNumber1);
			
			//Declare arraylist of alreadyLunched emp_ids
			ArrayList<String> alreadyLunched = new ArrayList<String>(5);
			
			// Retrieving previous 5 times lunch partner of randomNumber1 from log table and storing in arraylist
			resultSet = statement.executeQuery("select Monday, Tuesday, Wednesday, Thursday, Friday from log where Emp_Id = "+str_randomNumber1);
			while(resultSet.next()){
				alreadyLunched.add(resultSet.getString("Monday"));
				alreadyLunched.add(resultSet.getString("Tuesday"));
				alreadyLunched.add(resultSet.getString("Wednesday"));
				alreadyLunched.add(resultSet.getString("Thursday"));
				alreadyLunched.add(resultSet.getString("Friday"));
			}
			
			// Getting second random emp_id and checking whether that is suitable to pair up with.
			rowCount--;
			String str_randomNumber2=null;
			do{
				int r2 = r.nextInt(rowCount);
				System.out.println("r2 is : "+r2);
				if(r2>0)
					r2--;
				resultSet = statement.executeQuery("select * from TodaysEmployees limit 1 offset "+(r2));
				while(resultSet.next()){
					str_randomNumber2 = resultSet.getString(1);
				}
			}while(alreadyLunched.contains(str_randomNumber2));
			str_randomNumber2Copy=str_randomNumber2;
			System.out.println("random number 2 : "+str_randomNumber2);
			statement.executeUpdate("delete from TodaysEmployees where Emp_Id = "+str_randomNumber2);
						
			// Adding pairs in each iteration in the todaysLunchPairs table
			resultSet = statement.executeQuery("select Name from employee where Emp_Id = "+str_randomNumber1);
			String person1=null;
			String person2=null;
			while(resultSet.next()){
				person1 = resultSet.getString("Name");
				System.out.print(person1+" ");
			}
			
			resultSet = statement.executeQuery("select Name from employee where Emp_Id = "+str_randomNumber2);
			while(resultSet.next()){
				person2 = resultSet.getString("Name");
				System.out.print(person2+"\n");
			}
			person1Copy = person1;
			person2Copy = person2;
			
			System.out.println("Updating log...");
			statement.executeUpdate("UPDATE `test`.`log` SET `"+day+"`='"+str_randomNumber2+"' WHERE `Emp_Id`='"+str_randomNumber1+"'");
			statement.executeUpdate("UPDATE `test`.`log` SET `"+day+"`='"+str_randomNumber1+"' WHERE `Emp_Id`='"+str_randomNumber2+"'");
			statement.executeUpdate("INSERT INTO `test`.`todayslunchpairs` VALUES ('"+person1+"','"+person2+"');");
			System.out.println("Log updated.!");
		}
		
		// Below is a block for the last employee who has not been paired up due to total number of employees available for lunch being odd.
		
		if(rowCountCopy % 2 !=0 ) {
			//System.out.println("hi");
			resultSet = statement.executeQuery("SELECT * FROM test.todaysemployees;");
			String empid="";
			while(resultSet.next()) {
				empid = resultSet.getString(1);
			}
			String person="";
			String personCopy = "";
			resultSet = statement.executeQuery("SELECT * FROM test.todaysemployees;");
			while(resultSet.next()) {
				resultSet = statement.executeQuery("select Name from employee where Emp_Id = "+empid);
				while(resultSet.next()) {
					personCopy = resultSet.getString("Name");
					System.out.println(person);
				}
			}
			resultSet = statement.executeQuery("Select count(*) from test.todayslunchpairs");
			int count = 0;
			while(resultSet.next()) {
				count = Integer.parseInt(resultSet.getString(1));
			}
			resultSet = statement.executeQuery("select person1 from test.todayslunchpairs limit 1 offset "+(count-1));
			while(resultSet.next()) {
				person = resultSet.getString(1);
				System.out.println(person);
			}
			// Updating log..
			statement.executeUpdate("UPDATE `test`.`log` SET `"+day+"`='"+str_randomNumber2Copy+"' WHERE `Emp_Id`='"+empid+"'");
			//Log updated.!
			// Updating in todayslunchpairs table...
			statement.executeUpdate("UPDATE `test`.`todayslunchpairs` SET `Person2`='"+person2Copy+" and "+personCopy+"' WHERE `Person1`='"+person+"'");
		}
	}
		}

//int randomNumber2 = r2 + 200;
//System.out.println("random number 2 : "+randomNumber2);
////System.out.println(UsedRandoms.contains(r1));
////System.out.println(UsedRandoms.contains(r2));
//int r2Cached;
//if(r1==r2){
//	r2 = r.nextInt(10);
//	while(r1==r2 || UsedRandoms.contains(r2))
//		r2 = r.nextInt(10);
//	randomNumber2 = r2+200;	 
//	System.out.println("random number 2 : "+randomNumber2);
//}
//if(UsedRandoms.contains(r1))
//{
//		r1 = r.nextInt(10);
//		while(UsedRandoms.contains(r1) || r1==r2)
//			r1 = r.nextInt(10);
//		randomNumber1 = r1 +200;
//		System.out.println("randomNumber1 is already assigned earlier");
//		System.out.println("random number 1 : "+randomNumber1);
//}
//if(UsedRandoms.contains(r2))
//{
//	r2 = r.nextInt(10);
//	while(UsedRandoms.contains(r2) || r1==r2)
//		r2 = r.nextInt(10);
//	randomNumber2 = r2+200;	 
//	System.out.println("randomNumber2 is already assigned earlier");
//	System.out.println("random number 2 : "+randomNumber2);
//}
/*		
for(int iLoopVariable = 0; iLoopVariable<5;iLoopVariable++)
{
	if(log[r1][iLoopVariable]==randomNumber2)
	{
		r2Cached=r2;
		System.out.println("They already had lunch. Match found of "+randomNumber1+" and "+randomNumber2);
		r2 = r.nextInt(10);
		while(r2==r2Cached || UsedRandoms.contains(r2) || r1==r2)
			r2 = r.nextInt(10);
		randomNumber2 = r2+200;
		System.out.println("random number 2 : "+randomNumber2);
		continue;
	}
}
// If no pairing is found from log of last seven days, then we are good to pair them up.

TodaysLunchPairs[r1][0]=randomNumber2;
r2 = randomNumber2-200;
TodaysLunchPairs[r2][0]=randomNumber1;

UsedRandoms.add(r1);UsedRandoms.add(r2);

for(int iLoopVariable = 0; iLoopVariable<10;iLoopVariable++)
{
	System.out.print(TodaysLunchPairs[iLoopVariable][0]+" ");
}
System.out.print("\n");
} */
