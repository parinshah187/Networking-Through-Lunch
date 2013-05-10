

/* INSTRUCTION: This is a command line application. So please execute this template with the following arguments:

		arg[0] = username
		arg[1] = password
*/

/**
 * @author (Your Name Here)
 *
 */
 
import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * This is a test template
 */

  public class Calendar {
	  
    public static void main(String[] args) throws Exception {
    	
      try {
        
    	  String dbURL="jdbc:mysql://localhost/lunchData?user=root&password=kidu";
          Connection con;
          ResultSet rs;
          try{
              Class.forName("com.mysql.jdbc.Driver").newInstance();
          }catch(Exception ex){
              System.out.println("Error occurred while instantiating the driver");
          }
          try{
              con=DriverManager.getConnection(dbURL);
              PreparedStatement psinput=con.prepareStatement("select eMail,password from employee;");
              rs=psinput.executeQuery();
              
              while(rs.next()){
              String userid=rs.getString(1);
              String pwd=rs.getString(2);
    	  
    	  URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/private/full");
    	  
    	  CalendarQuery myQuery = new CalendarQuery(feedUrl);
    	  myQuery.setMinimumStartTime(DateTime.parseDateTime("2013-05-07T12:00:00"));
    	  myQuery.setMaximumStartTime(DateTime.parseDateTime("2013-05-08T14:30:00"));
    	  System.out.println("max="+myQuery.getMaximumStartTime().toString());
    	  System.out.println("min="+myQuery.getMinimumStartTime().toString());

    	  
    	  CalendarService myService = new CalendarService("exampleCo-exampleApp-1");
    	  myService.setUserCredentials(userid, pwd);

    	  // Send the request and receive the response:
    	 CalendarEventFeed resultFeed = myService.query(myQuery, CalendarEventFeed.class);
        
        List<CalendarEventEntry> entries = resultFeed.getEntries();
        for(int i=0; i<entries.size(); i++) {
          CalendarEventEntry entry = entries.get(i);
          System.out.println("\t" + entry.getTitle().getPlainText());
        }
        System.out.println("\nTotal Entries: "+entries.size());
        
        String flag=null;
        
        if(entries.size()==0)
        	flag="Y";
        else
        	flag="N";
              
        psinput=con.prepareStatement("update employee set flag = '"+flag+"' where eMail = '"+userid+"';"); 
        if(psinput.executeUpdate()==1)
        	System.out.println(">>>>>>>Success!<<<<<<<<");
        else
        	System.out.println(">>>>>>>Fail!<<<<<<<<");
              
              }}
      
      
      catch(AuthenticationException e) {
        e.printStackTrace();
      }
      }
      catch(MalformedURLException e) {
        e.printStackTrace();
      }
      catch(ServiceException e) {
        e.printStackTrace();
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    
  }}