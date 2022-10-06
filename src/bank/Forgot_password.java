package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Forgot_password 
{
	Scanner sc = new Scanner(System.in);
	 private int pin,counter=0;
	 private long phno;
	 public Forgot_password() 
	{
		Connection connection = null;
		       try 
		       {
		   		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root", "password");
		   		Statement statement;
		   		statement = connection.createStatement();
		   		ResultSet resultSet1;
				 System.out.println("enter your PIN");
				 pin = sc.nextInt();
		
				 resultSet1 =statement.executeQuery("select * from customer");
				 while(resultSet1.next())
				 {
					  int  pin1 = resultSet1.getInt("4digitpin");
					  if(pin1==pin)
					  {
						  String nAmE = resultSet1.getString("custname");	
	    					System.out.println("HII! "+nAmE+"\n");
	    					System.out.println("Enter Your new password");
	    					String pass = sc.next();
	    					if(pass.length()<6)
	    					{
	    						System.out.println("PASSWORD SHOULD BE GREATER THAN 6");
	    						System.out.println("Enter Your new password");
		    					 pass = sc.next();
	    					}
	    					String sql = "update customer set password =? where 4digitpin=?";
	    		  			PreparedStatement ps = connection.prepareStatement(sql);
	    		  			ps.setString(1, pass);
	    		  			ps.setInt(2,pin);
	    		  			ps.executeUpdate();
	    		  			
	    		  			System.out.println("Password changed successfully!");
	    		  			new Login();
					  }
					  else
					  {
						  counter++;
					  }
				 }
				 if(counter!=0)
				 {
					 System.out.println("ACCOUNT NOT FOUND");
					 new Login();
				 }
		       }
		        catch (Exception e) {
		 
		            System.out.println(e);
		        }      
		       try 
		       {
		           if(connection != null) 
		        	    connection.close();
		       } 
		        catch (SQLException ignore) {}
	}
}
