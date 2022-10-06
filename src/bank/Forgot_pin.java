package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Forgot_pin {
	Scanner sc = new Scanner(System.in);
	 private String name;
	 private long phno;
	 private int counter=0;
	 public Forgot_pin(int pin ,String pass) 
	{
		Connection connection = null;
		       try 
		       {
		   		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root", "password");
		   		Statement statement;
		   		statement = connection.createStatement();
		   		ResultSet resultSet1;
				 System.out.println("enter your name");
				 name = sc.next();
				 System.out.println("Enter your phone");
				 phno = sc.nextLong();
				 resultSet1 =statement.executeQuery("select * from customer");
				 while(resultSet1.next())
				 {
					  String name1= resultSet1.getString("custname");
					  Long  phone1 = resultSet1.getLong("phone");
					  if(name.equals(name1) && phno ==phone1 )
					  {
	    					System.out.println("HII! "+name1+"\n");
	    					pin = (int)(Math.random()*10000);
	    					String sql = "update customer set 4digitpin =? where phone=?";
	    		  			PreparedStatement ps = connection.prepareStatement(sql);
	    		  			ps.setInt(1, pin);
	    		  			ps.setLong(2,phno);
	    		  			ps.executeUpdate();
	    		  			
	    		  			System.out.println("PIN changed succesfully");
	    		  			System.out.println("YOUR NEW PIN -"+pin);
	    		  			new Settings(pin, pass);
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
		       	new Login();
			       try 
			       {
			           if(connection != null) 
			        	    connection.close();
			       } 
			        catch (SQLException ignore) {}
	}
}
