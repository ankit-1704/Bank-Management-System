package bank;
import java.util.*;
import java.sql.*;
public class SignUp
{
	private String address ,password,name1,bankName,branchName,bankAddress,branchAddress,accType,dOb;
	private long accNo,phNo;
	int counter =0;
	int pin;
	Scanner sc = new Scanner(System.in);
	SignUp()
	{
		Connection connection = null;
       PreparedStatement ps =null ;
	       try {
	   		connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bms",
					"root", "password");
	   		Statement statement;
	   		statement = connection.createStatement();
	   		ResultSet resultSet1;
	   		
	    	   System.out.println("Enter name of the bank in which you have account");
	    	   bankName = sc.next();
		    	   
		        System.out.println("Enter bank address");
		        bankAddress = sc.next();

		        
	            System.out.println("Enter branch name");
		        branchName = sc.next();

		        
		        System.out.println("Enter branch address");
		        branchAddress = sc.next();

		        
	            System.out.println("Enter account no");
		        accNo = sc.nextLong();
		        resultSet1 = statement.executeQuery("select * from account ");
		        while(resultSet1.next())
		        {
		        	long accno1 = resultSet1.getLong("accno");
		        	if(accNo == accno1)
		        	{
		        		System.out.println("ACCOUNT NUMBER ALREADY EXISTS");
			        	new Login();
		        	}

		        }
	            System.out.println("Enter account type");
		        accType = sc.next();

		        
	            System.out.println("Enter Your Name");
		        name1 = sc.next();

		        System.out.println("Create password");
		    	password = sc.next();
		    	if(password.length()<=6)
		    	{
		    		System.out.println("PASSWORD LENGTH MUST BE GREATER THAN 6");
		    		new SignUp();
		    	}
		    	   
	            System.out.println("Enter date of birth(DD/MM/YY)");
		        dOb = sc.next();

	            System.out.println("Enter your mobile number");
		        phNo = sc.nextLong();
		        
	            System.out.println("Enter your address");
		        address = sc.next();
		        
		        pin = (int)(Math.random()*10000);
		        
		        
		        ps  = connection.prepareStatement("insert into bank"+ " (bankname,bankaddress)" + " values (?, ?)");
		    	ps.setString(1,bankName );
		    	ps.setString(2, bankAddress);
		    	
		    	ps.executeUpdate();
		    	
		    	ps =  connection.prepareStatement( "insert into branch"	+ " (branchname,branchaddress)" + " values (?, ?)");
		    	ps.setString(1 ,branchName);
		    	ps.setString(2 ,branchAddress);
		    	
		    	ps.executeUpdate();
		  
		    	ps = connection.prepareStatement("insert into account"	+ " (accno,acctype)" + " values (?, ?)");
		    	ps.setLong(1 ,accNo);
		    	ps.setString(2 ,accType);
		    	
		    	ps.executeUpdate();
		    	
		    	ps =connection.prepareStatement( "insert into customer"	+ " (custname,dob,phone,address,password,4digitpin)" + " values (?,?,?,?,?,?)");
		    	ps.setString(1 ,name1);
		    	ps.setString(2 ,dOb);
		    	ps.setLong(3,phNo);
		    	ps.setString(4, address);
		    	ps.setString(5, password);
		    	ps.setInt(6, pin);
		    	
		    	ps.executeUpdate();
		    	
		    	 System.out.println("you have Succesfully signed up.Now login to open your account");
		    	 System.out.print("REMEMBER YOUR PIN - "+pin);
		    	 System.out.println(" and PASSWORD - "+password);
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
