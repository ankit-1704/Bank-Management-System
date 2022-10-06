package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Transaction_history {
	private long account;
	private String name;
	private int counter=0;
	public Transaction_history(int pin1,String pass) {
		System.out.println("*************Transaction History*************");
		Connection connection = null;
	       try 
	       {
	   		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms",
					"root", "password");
	   		Statement statement;

			statement = connection.createStatement();
			ResultSet resultSet1,rs1,rs2;
			resultSet1 = statement.executeQuery("select * from customer where 4digitpin = "+pin1);
			resultSet1.next();
			name = resultSet1.getString("custname");
			System.out.println("HELLO! "+name+"\n\n");
			 int custid = resultSet1.getInt("custid");
	   		rs1=statement.executeQuery("select * from account where accid ="+custid);
	   		rs1.next();
	   		long accno = rs1.getLong("accno");
	   		System.out.println("Account Number - \n\n"+accno);
	   		rs2=statement.executeQuery("select * from transaction ");
	   		
	   		while(rs2.next())
	   		{
	   			int pin = rs2.getInt("pin");
	   			if(pin==pin1)
	   			{
	   			counter++;
	   				String date = rs2.getString("Date");
	   				String mode = rs2.getString("mode");
	   				double bal =rs2.getDouble("amount");
	   				System.out.println(date+"\t"+mode+"\t"+bal+"\n");
	   			}
	   		}
	   		new Balance_enquiry(pin1, pass);
	   		if(counter==0)
	   			System.out.println("\nNO TRANSACTION FOUND\n");
	   			new Balance_enquiry(pin1, pass);
	       }	
	   	     catch (Exception e) 
	        {
	            System.out.println(e);
	        }
	       try 
	       {
	           if(connection != null) 
	        	    connection.close();
	       } 
	        catch (SQLException ignore) 
	       {}

	}
}
