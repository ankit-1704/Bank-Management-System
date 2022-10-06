package bank;
import java.util.*;
import java.sql.*; 
public class Login 
{ 
	private int pin;
	private String pass ;
	Login()
	{
		
		Scanner sc = new Scanner(System.in);
		int choice;
		
		
		
		Connection connection = null;
		try
		{
			// below two lines are used for connectivity.
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/bms",
				"root", "password");

			System.out.println("1.login\n 2. sign up\n3.Forgot password?\n4.Forgot PIN?");
		    choice = sc.nextInt();
			 if(choice == 1)
			   {
			    	System.out.println("Enter 4 digit pin :");
			        pin = sc.nextInt();
			    	System.out.println("Enter password :");
			    	pass = sc.next();
			    	new Menu( pin, pass);
			   }
			 else if (choice ==2)
			  {
		     	new SignUp();
		      }
			 else if(choice ==3)
			 {
				 new Forgot_password();
			 }
			 else if(choice == 4)
			 {
				new Forgot_pin(pin,pass);
			 }
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
	public static void main(String arg[])
	{	
		System.out.println("WELCOME TO BANK MANAGEMENT SYSTEM\n");
		new Login();
	} 
} 
