package bank;

import java.util.Scanner;

public class Settings 
{
	Scanner sc = new Scanner(System.in);
		Settings(int pin1,String pass)
		{
			System.out.println("1.CHANGE PASSWORD\n2.CHANGE PIN\n3.LOGOUT\n4.BAck to MAIN MENU");
			int choice = sc.nextInt();
			switch(choice)
			{
				case 1:
				{
					new Change_pass(pin1,pass);
				}
				break;
				case 2:
				{
					new Change_pin(pin1,pass);
				}
				break;
				case 3:
				{
					new Logout( pin1, pass);
				}
				case 4:
				{
					new Menu(pin1,pass);
				}
				break;
				default:
				{
					System.out.println("WRONG INPUT!");
					new Settings(pin1,pass);
				}
			}
		}
}
