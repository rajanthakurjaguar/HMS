import java.net.*;	
import java.sql.*;	
import java.awt.event.*;
import javax.swing.*; 	
import java.awt.*;
import java.lang.*;  	


public class status extends JFrame
{
	private JLabel tName,tHostel,troom_no,tcontact_no;
	private JLabel tRoll,lRoll,lName,lHostelName,lroom_no,lcontact_no,lhead;
	String strRoll,strName,strCourse,strHostel,strYear,strroom,strContact;
	private JButton bExit;
	int flag;

    	Connection con;	
    	Statement stmt,stmt1;
    	private Icon icon;
    	ResultSet rs,rs1;   
	

	public status(String strRoll,String strCourse,String strYear)
	{
	        connect();
		int flag=search(strRoll,strCourse,strYear);
		if (flag==1)
		{
	   	JFrame frame = new JFrame("Status of Student");
                Toolkit tkt = frame.getToolkit();
                Dimension frsize = tkt.getScreenSize(); 
                          
              	frame.setBounds(frsize.width/8,frsize.height/8,frsize.width/4,frsize.height/2);
              	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
            
		Container cn=frame.getContentPane();         
                cn.setLayout(null);
                cn.setBackground(new Color(250,250,250));   

				
                lhead = new JLabel("Room ");
                lhead.setForeground(new Color(10,10,10));
                lhead.setFont(new Font("Algerian",Font.BOLD,40));
                lhead.setBounds(350,20,700,80);
		cn.add(lhead);

		
 
                lRoll = new JLabel("Roll");
                lRoll.setFont(new Font("Serif",Font.BOLD,20));
                lRoll.setBounds(150,150,180,30);

                tRoll = new JLabel(strRoll);	
                tRoll.setFont(new Font("Serif",Font.BOLD,20));
                tRoll.setBounds(350,150,150,30);
		 

                lName = new JLabel("Name");
                lName.setFont(new Font("Serif",Font.BOLD,20));
                lName.setBounds(150,200,200,30);

                tName = new JLabel(strName);
                tName.setFont(new Font("Serif",Font.BOLD,20));
                tName.setBounds(350,200,150,30);

		lHostelName = new JLabel("Hostel Name");
                lHostelName.setFont(new Font("Serif",Font.BOLD,20));
                lHostelName.setBounds(150,250,200,30);

		tHostel=new JLabel(strHostel);
                tHostel.setFont(new Font("Serif",Font.BOLD,20));
                tHostel.setBounds(350,250,150,30);

		lroom_no = new JLabel("Room Number");
                lroom_no.setFont(new Font("Serif",Font.BOLD,20));
                lroom_no.setBounds(150,300,200,30);

		troom_no=new JLabel(strroom);
                troom_no.setFont(new Font("Serif",Font.BOLD,20));
                troom_no.setBounds(350,300,150,30);                
                
		lcontact_no = new JLabel("Contact Number");
                lcontact_no.setFont(new Font("Serif",Font.BOLD,20));
                lcontact_no.setBounds(150,350,150,30);

		tcontact_no=new JLabel(strContact);
                tcontact_no.setFont(new Font("Serif",Font.BOLD,20));
                tcontact_no.setBounds(350,350,150,30);                

               bExit=new JButton("CLOSE");
               bExit.setFont(new Font("Serif",Font.BOLD,20));
               bExit.setMnemonic('C');
               bExit.setBounds(500,550,130,30);
               bExit.addActionListener(new exitListener(frame));
                
                cn.add(lRoll);cn.add(tRoll); 
                cn.add(lName);cn.add(tName);
		cn.add(lHostelName);cn.add(tHostel);
                cn.add(lroom_no);cn.add(troom_no);
                cn.add(lcontact_no );cn.add(tcontact_no);
		cn.add(bExit);
 
                frame.pack();
                frame.setVisible(true);
                frame.setSize(700,650);
		}
		else 
			JOptionPane.showMessageDialog(null,"Entered Details not valid  ");
	   
	}

	public int search(String strRoll,String strCourse,String strYear)
	{
        try
        {
        rs=stmt.executeQuery("select hostelName from courseTableEntry where course='"+strCourse+"' and year='"+strYear+"'");
        while(rs.next())
	{
		 strHostel=rs.getString(1);

	   try {
		rs1=stmt1.executeQuery("select room_no from "+strHostel+" where std_roll='"+strRoll+"' and status='F'");
                    rs1.next();

		 	strroom=rs1.getString(1);

			try{
				rs1=stmt1.executeQuery("select std_name,contact_no from student where std_roll='"+strRoll+"'");
        			rs1.next();
				strName=rs1.getString(1);
				strContact=rs1.getString(2);
            			rs1.close();
	    			return 1;
			    }
			catch(SQLException sqle)
        		{
        		JOptionPane.showMessageDialog(null,sqle);
        		}

	     }
            catch(SQLException sqle)
                   {
                     JOptionPane.showMessageDialog(null,"Roll number does not exists :"+strRoll);
                   }
		

        }
	}  
        catch(SQLException sqle)
                   {
                     JOptionPane.showMessageDialog(null,"No Hostel for "+strCourse+" of "+strYear);
                   }
	return 0;
	
	}


    private class exitListener implements  ActionListener
	{
		JFrame aw;
                public exitListener(JFrame aWindow)
		{
		 aw=aWindow;
		 }
		public void actionPerformed(ActionEvent e)
		{
          aw.dispose();
        }
    	
    }


	public void connect()
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


/*  public static void main (String args[])
    {
        status ra = new status("b130371cs","Manoj Gupta sharma","btech","2");

    }*/

}
