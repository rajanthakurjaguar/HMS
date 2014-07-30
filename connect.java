import java.sql.*;	 /*For Sql Query*/

public class connect extends JFrame
{
	public connect()
	{

		try
     		{
		 try {
         	 Class.forName("com.mysql.jdbc.Driver").newInstance();
         	 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/root","root","");  
         	 stmt= con.createStatement();
		 stmt1= con.createStatement();
         	 
        	     } 
		catch (Exception e)  
		{
	          e.printStackTrace();
	        }
		}
		catch (Exception e)  
		{
	          JOptionPane.showMessageDialog(null,"JavaError:Not Connect");
		}
	}
}

