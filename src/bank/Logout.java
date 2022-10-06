package bank;

import java.util.*;
import java.sql.*; 
public class Logout 
{
	Logout( int pin1,String pass)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("CONFIRM LOGOUT\n Y or N");
		char choice =sc.next().charAt(0);
		if(choice == 'y')
			new Login();
		else 
		{
			new Settings(pin1,pass);
		}
	}
}