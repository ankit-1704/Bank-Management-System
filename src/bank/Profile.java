package bank;

import java.sql.*;
import java.util.Scanner;

public class Profile {
	private String custname,dob,address,acctype,branchname,branchaddress,bankname,bankaddress;
	private Long phone,accno;
	Scanner sc = new Scanner(System.in);
	public Profile(int pin,String pass) {
		Connection connection = null;
	       try 
	       {
	   		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root", "password");
	   		Statement statement;
	   		statement = connection.createStatement();
	   		ResultSet resultSet1,resultSet2,resultSet3,resultSet4;
	   		
	   		resultSet1 = statement.executeQuery("select * from customer where 4digitpin ="+pin);
	   		resultSet1.next();
	   		
	   		custname = resultSet1.getString("custname");
	   		phone = resultSet1.getLong("phone");
	   		dob = resultSet1.getString("dob");
	   		address = resultSet1.getString("address");
	   		int custid = resultSet1.getInt("custid");
	   		
	   		resultSet2 = statement.executeQuery("select * from account where accid ="+custid);
	   		resultSet2.next();
	   		
	   		accno = resultSet2.getLong("accno");
	   		acctype = resultSet2.getString("acctype");
	   		
	   		resultSet3 = statement.executeQuery("select * from branch where branchid ="+custid);
	   		resultSet3.next();
	   		
	   		branchname = resultSet3.getString("branchname");
	   		branchaddress = resultSet3.getString("branchaddress");
	   		
	   		resultSet4 = statement.executeQuery("select * from bank where bankid ="+custid);
	   		resultSet4.next();
	   		
	   		bankname = resultSet4.getString("bankname");
	   		bankaddress = resultSet4.getString("bankaddress");
	   		
	   		System.out.println("NAME\n\t\t"+custname+"\n\t\t________________");
	   		System.out.println("PHONE\n\t\t"+phone+"\n\t\t________________");
	   		System.out.println("DATE OF BIRTH\n\t\t"+dob+"\n\t\t________________");
	   		System.out.println("ADRESS\n\t\t"+address+"\n\t\t________________");
	   		System.out.println("PIN NO\n\t\t"+pin+"\n\t\t________________");
	   		System.out.println("PASSWORD\n\t\t"+pass+"\n\t\t________________");
	   		System.out.println("ACCOUNT NO\n\t\t"+accno+"\n\t\t________________");
	   		System.out.println("ACCOUNT TYPE\n\t\t"+acctype+"\n\t\t________________");
	   		System.out.println("BANK \n\t\t"+bankname+"\n\t\t________________");
	   		System.out.println("BANK ADDRESS\n\t\t"+bankaddress+"\n\t\t________________");
	   		System.out.println("BRANCH \n\t\t"+branchname+"\n\t\t________________");
	   		System.out.println("BRANCH ADDRESS\n\t\t"+branchaddress+"\n\t\t________________");
	   		
	   		System.out.println("Back to main menu? Y or N");
	   		char choice= sc.next().charAt(0);
	   		if(choice =='y')
	   			new Menu(pin,pass);
	   		else
	   			new Profile(pin,pass);
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
