package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Change_pass
{
	Scanner sc = new Scanner(System.in);
	public Change_pass(int pin1 ,String pass)
	{
		Connection connection = null;
       
	       try 
	       {
	   		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms",
	   				"root", "password");
	   		Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet1,rs1;
			
			System.out.println("Enter your Old Password");
			String old = sc.next();
			if(old.equals(pass))
			{
				System.out.println("Enter Your New Password");
				String newpass = sc.next();
				if(newpass.length()<=6)
		    	{
		    		System.out.println("PASSWORD LENGTH MUST BE GREATER THAN 6");
		    		new Change_pass(pin1,pass);
		    	}
				else
				{
		    		System.out.println("Confirm Your New Password");
		    		String newpass1 = sc.next();
					if(newpass.equals(newpass1))
					{
						String sql = "update customer set password=? where 4digitpin=?";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setString(1, newpass);
						ps.setInt(2,pin1);
						ps.executeUpdate();
						System.out.println("PASSWORD changed successfully");
						new Settings(pin1,pass);
					}
					else
					{
						System.out.println("NEW PASSWORD AND CONFIRM PASWORD DOESN'T MATCH");
					}
		    	}
			}
			else
			{
				System.out.println("WRONG OLD PASSWORD");
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
	       {
	       }
	}
}
