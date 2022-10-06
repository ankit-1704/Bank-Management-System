package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Delete {
	Delete(Long phno)
	{
	System.out.println("Enter amount you want to deposite");
	Connection connection = null;
 
       try 
       {
   		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms",
				"root", "password");
   		Statement statement;

		statement = connection.createStatement();
		ResultSet resultSet1,rs1;
		resultSet1 = statement.executeQuery("select * from customer where phone = "+phno);
   		resultSet1.next();
		int custid1 = resultSet1.getInt("custid");
   		rs1=statement.executeQuery("select * from bank where bankid ="+custid1+"");
   		rs1.next();
   				
   		String sql = "delete from account  where bankid=?";
   		PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,custid1);
    	ps.executeUpdate();
    	System.out.println("Account deleted Suucessfully");
   		}
        catch (Exception e) 
        {
            System.out.println(e);
        }
	}
}

	