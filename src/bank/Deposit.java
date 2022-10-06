package bank;

import java.text.*;
import java.util.*;
import java.util.Date;
import java.sql.*; 
public class Deposit
{
	Scanner sc = new Scanner(System.in);
	private long amount;
	public Deposit(int pin1)
	{
		System.out.println("Enter amount you want to deposit");
   		amount=sc.nextInt();
		Connection connection = null;
	       try 
	       {
	   		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms",
	   				"root", "password");
	   		Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet1,rs1;
			resultSet1 = statement.executeQuery("select * from customer where 4digitpin = "+pin1);
			resultSet1.next();
			int custid1 = resultSet1.getInt("custid");
	   		rs1=statement.executeQuery("select * from account where accid ="+custid1);
	   		rs1.next();
	   		int bal = rs1.getInt("balance");
	   		bal +=amount;
	   			String mode = "Deposite";
	   	        SimpleDateFormat sd = new SimpleDateFormat(
	   	             "yyyy.MM.dd G 'at' HH:mm:ss z");
	   	         Date date = new Date();
	   	         sd.setTimeZone(TimeZone.getTimeZone("IST"));
	   	        String date1 = sd.format(date);
	  			String sql = "update account set balance=? where accid=?";
	  			PreparedStatement ps = connection.prepareStatement(sql);
	  			ps.setInt(1,bal );
	  			ps.setInt(2,custid1);
	  			ps.executeUpdate();
	  			
	  			ps =connection.prepareStatement( "insert into transaction"	+ " (pin,Date,mode,amount)" + " values (?,?,?,?)");
	  			
	  			ps.setInt(1,pin1);
	  			ps.setString(2,date1);
	  			ps.setString(3,mode);
	  			ps.setLong(4,amount);
	  			ps.executeUpdate();
	  			
	  			System.out.println("You have deposited "+amount+" rupees only");
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
	       {
	       }
	}	
}