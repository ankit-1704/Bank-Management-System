package bank;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
public class Withdraw 
{
	Scanner sc = new Scanner(System.in);
	private int amount;
	public Withdraw(int pin1)
	{
		System.out.println("Enter amount you want to withdraw");
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
	   		if(amount<bal)
	   		{
	   			bal-=amount;
	  			String sql = "update account set balance=? where accid=?";
	  			PreparedStatement ps = connection.prepareStatement(sql);
	  			ps.setInt(1,bal );
	  			ps.setInt(2,custid1);
	  			ps.executeUpdate();
	  			
	  			String mode = "Withdraw";
	   	        SimpleDateFormat sd = new SimpleDateFormat(
	   	             "yyyy.MM.dd G 'at' HH:mm:ss z");
	   	         Date date = new Date();
	   	         sd.setTimeZone(TimeZone.getTimeZone("IST"));
	   	        String date1 = sd.format(date);
	  			
	   	        ps =connection.prepareStatement( "insert into transaction"	+ " (pin,Date,mode,amount)" + " values (?,?,?,?)");
	  			
	  			ps.setInt(1,pin1);
	  			ps.setString(2,date1);
	  			ps.setString(3,mode);
	  			ps.setLong(4,amount);
	  			ps.executeUpdate();
	  			
	  			System.out.println("You have withdrawn "+amount+" Rupees only");
	   		}
	   		else
	   		{
	   			System.out.println("INSUFFICIENT FUND");
	   		}
	
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
