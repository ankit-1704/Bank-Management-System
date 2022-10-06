package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu
{
	Scanner sc = new Scanner(System.in);
	private int choice,counter=0;
	Menu(int pin1,String pass)
	{
		Connection connection = null;
		try
		{
					Class.forName("com.mysql.cj.jdbc.Driver");
					connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bms",
						"root", "password");

					Statement statement;
					statement = connection.createStatement();
					ResultSet resultSet1;
				
					resultSet1 = statement.executeQuery("select * from customer");
			    	while (resultSet1.next())
			    	{
			    		int pin = resultSet1.getInt("4digitpin");
			    		String pass1 = resultSet1.getString("password");
			    		if(pass.equals(pass1)) {
			    				if(pin==pin1) 
			    				{
			    					String nAmE = resultSet1.getString("custname");	
			    					System.out.println("WELCOME "+nAmE+"\n");
			    					System.out.println("1.Profile\n2.Transactions\n3.Balance Enquiry\n4. Transaction History\n5.Settings");
			    					choice = sc.nextInt();
			    					switch(choice)
			    					{
			    						case 1:
			    						{
			    							new Profile(pin1,pass);
			    							new Menu(pin1,pass);
			    						}
			    						break;
			    						case 2:
			    						{
			    							new Transaction(pin1,pass);
			    							new Menu(pin1,pass);
			    						}
			    						break;
			    						case 3:
			    						{
			    							new Balance_enquiry(pin1,pass);
			    							new Menu(pin1,pass);
			    						}
			    						break;
			    						case 4:
			    						{
			    							new Transaction_history(pin1,pass);
			    							new Menu(pin1,pass);
			    						}
			    						break;
			    						case 5:
			    						{
			    							new Settings(pin1,pass);
			    						}
			    						break;
			    						default :
			    						{
			    							System.out.println("Wrong Input!");
			    							new Menu(pin1,pass);
			    						}
			    						break;
			    		
			    					}

			    				}
			    				else
			    				{
			    					System.out.println("INVALID PIN");
			    				}
			    		}	
			    		else
			    		{
			    			counter++;
			    		}
			    	}
			    	if(counter!=0)
			    	{
			    		System.out.println("INVALID PASSWORD");
			    	}
			    
			    	new Login();
		}
		catch (Exception exception)
		{
			System.out.println(exception);
		}
	       try 
	       {
	           if(connection != null) 
	        	    connection.close();
	       } 
	        catch (SQLException ignore) {}
	}

}
