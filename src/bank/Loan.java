package bank;

import java.util.*;

class Loan {
	private String loantype;
	private double amount;
	Loan()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the type of loan");
		loantype = sc.next();
		System.out.println("enter loan amount");		
	}
}
