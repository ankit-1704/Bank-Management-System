package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Change_pin {
	Scanner sc = new Scanner(System.in);
	public Change_pin(int pin1 ,String pass)
	{
		Connection connection = null;
       
	       try 
	       {
	   		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms",
	   				"root", "password");
	   		Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet1,rs1;
			
			System.out.println("Enter your Old PIN");
			int old = sc.nextInt();
			if(old==pin1)
			{
				
				int pin = (int)(Math.random()*10000);
				String sql = "update customer set 4digitpin=? where password=?";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, pin);
				ps.setString(2,pass);
				ps.executeUpdate();
				System.out.println("PIN changed successfully");
				System.out.println("YOUR NEW PIN -"+pin);
				new Settings(pin1,pass);
		    }
			else
			{
				System.out.println("WRONG OLD PIN");
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
