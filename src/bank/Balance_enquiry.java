package bank;
import java.util.*;
import java.sql.*;
public class Balance_enquiry
{
	Balance_enquiry(int pin1,String pass)
	{
		Scanner sc = new Scanner(System.in);
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
		   		rs1=statement.executeQuery("select * from account where accid ="+custid1+"");
		   		rs1.next();
		   		Long bal = rs1.getLong("balance");
		   		System.out.println("Available balance : "+bal);
		   		System.out.println("Back to main menu(Y or N)");
		   		char choice = sc.next().charAt(0);
		   		if(choice == 'y')
		   			new Menu(pin1,pass);
		        else
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
		        catch (SQLException ignore) {}
		
	}

}
