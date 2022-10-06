package bank;

import java.util.Scanner;

public class Transaction {
	private int choice;
	Scanner sc = new Scanner(System.in);
	Transaction(int pin,String pass)
	{
		System.out.println("1.Deposit\n2.Withdraw\n3.Back to main menu");
		choice = sc.nextInt();
		switch(choice)
		{
			case 1:
			{
				new Deposit(pin);
				new Transaction(pin,pass);
			}	
			break;
			case 2:
			{
				new Withdraw(pin);
				new Transaction(pin,pass);
			}	
			break;
			default:
			{
				new Menu(pin,pass);
			}	
			break;
		}
	}
}
