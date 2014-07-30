import java.net.*;	
import java.sql.*;	
import java.awt.event.*;
import javax.swing.*; 	
import java.awt.*;
import java.lang.*;  	


public class roomAlot extends JFrame
{
	private JLabel tName,tCourse,tYear,tHostel,troom_no,tseat_no;
	private JLabel tRoll,lRoll,lName,lHostelName,lYear,lCourse,lroom_no,lseat_no,lhead;
	String strHostel,strroom,strseat;
	private JButton bExit;
	int flag=0;

    	Connection con;	
    	Statement stmt,stmt1;
    	private Icon icon;
    	ResultSet rs,rs1;   
	

	public roomAlot(String strRoll,String strName,String strCourse,String strYear,String strGender)
	{
		           connect();
				  
	   	JFrame frame = new JFrame("Alloted Hostel");
                Toolkit tkt = frame.getToolkit();
                Dimension frsize = tkt.getScreenSize(); 
                          
              	frame.setBounds(frsize.width,frsize.height/16,frsize.width/4,frsize.height/4);
              	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
            
		Container cn=frame.getContentPane();         
                cn.setLayout(null);
                cn.setBackground(new Color(143,188,143));   

				
                lhead = new JLabel("Room ");
                lhead.setForeground(new Color(10,10,10));
                lhead.setFont(new Font("Algerian",Font.BOLD,40));
                lhead.setBounds(350,20,700,80);
		cn.add(lhead);

		search(strRoll,strName,strCourse,strYear,strGender);
 
                lRoll = new JLabel("Roll");
                lRoll.setFont(new Font("Serif",Font.BOLD,20));
                lRoll.setBounds(50,150,180,30);

                tRoll = new JLabel(strRoll);	
                tRoll.setFont(new Font("Serif",Font.BOLD,20));
                tRoll.setBounds(250,150,150,30);
		 

                lName = new JLabel("Name");
                lName.setFont(new Font("Serif",Font.BOLD,20));
                lName.setBounds(50,200,150,30);

                tName = new JLabel(strName);
                tName.setFont(new Font("Serif",Font.BOLD,20));
                tName.setBounds(200,200,200,30);

                lCourse = new JLabel("Course");
                lCourse.setFont(new Font("Serif",Font.BOLD,20));
                lCourse.setBounds(50,250,200,30);
		
                tCourse=new JLabel(strCourse);
                tCourse.setFont(new Font("Serif",Font.BOLD,20));
                tCourse.setBounds(250,250,150,30);

		lYear = new JLabel("Year");
                lYear.setFont(new Font("Serif",Font.BOLD,20));
                lYear.setBounds(50,300,200,30);

                tYear=new JLabel(strYear);
                tYear.setFont(new Font("Serif",Font.BOLD,20));
                tYear.setBounds(250,300,150,30);
		tYear.setText(strYear);
		

		lHostelName = new JLabel("Hostel Name");
                lHostelName.setFont(new Font("Serif",Font.BOLD,20));
                lHostelName.setBounds(50,350,200,30);

		tHostel=new JLabel(strHostel);
                tHostel.setFont(new Font("Serif",Font.BOLD,20));
                tHostel.setBounds(250,350,150,30);

		lroom_no = new JLabel("Room Number");
                lroom_no.setFont(new Font("Serif",Font.BOLD,20));
                lroom_no.setBounds(50,400,200,30);

		troom_no=new JLabel(strroom);
                troom_no.setFont(new Font("Serif",Font.BOLD,20));
                troom_no.setBounds(250,400,150,30);                
                
		lseat_no = new JLabel("Bed Number");
                lseat_no.setFont(new Font("Serif",Font.BOLD,20));
                lseat_no.setBounds(50,450,150,30);

		tseat_no=new JLabel(strseat);
                tseat_no.setFont(new Font("Serif",Font.BOLD,20));
                tseat_no.setBounds(250,450,150,30);                

               bExit=new JButton("CLOSE");
               bExit.setFont(new Font("Serif",Font.BOLD,20));
               bExit.setMnemonic('C');
               bExit.setBounds(500,550,130,30);
               bExit.addActionListener(new exitListener(frame));

		Icon icon=new ImageIcon("photo/"+strRoll.trim()+".jpg"); //pic on form
                JLabel lpic=new JLabel(icon);
                lpic.setBounds(400,250,300,240);
		cn.add(lpic);
                
                cn.add(lRoll);cn.add(tRoll); 
                cn.add(lName);cn.add(tName);
                cn.add(lCourse);cn.add(tCourse);
		cn.add(lHostelName);cn.add(tHostel);
		cn.add(lYear);cn.add(tYear);  
                cn.add(lroom_no);cn.add(troom_no);
                cn.add(lseat_no );cn.add(tseat_no);
		cn.add(bExit);
 
                frame.pack();
                frame.setVisible(true);
                frame.setSize(700,700); 
	   
	}

	public void search(String strRoll,String strName,String strCourse,String strYear,String strGender)
	{
        try
        {
        rs=stmt.executeQuery("select hostelName from courseTableEntry where course='"+strCourse+"' and year='"+strYear+"' and hostelFor='"+strGender+"'");
        while(rs.next())
	{
		 strHostel=rs.getString(1);

	   try {
		rs1=stmt1.executeQuery("select room_no,seat_no from "+strHostel+" where std_roll='' and status='V'");
                    rs1.next();

		 	 strroom=rs1.getString(1);
			 strseat=rs1.getString(2);

			try{
stmt1.executeUpdate("update "+strHostel+" set std_roll='"+strRoll+"',status='F'where room_no='"+strroom+"' and seat_no='"+strseat+"'");
stmt1.executeUpdate("update student set hostelName='"+strHostel+"' where std_roll='"+strRoll+"'");
        		 stmt1.executeUpdate("commit");
            		//JOptionPane.showMessageDialog(null,"Hostel :"+strHostel+" Room No :"+strroom+" Seat No :"+strseat);
			rs1.close();flag=1;
	    		break;
			    }
			catch(SQLException sqle)
        		{
        		JOptionPane.showMessageDialog(null,sqle);
        		}

	     }
            catch(SQLException sqle)
                   {
                     JOptionPane.showMessageDialog(null,"NO Vacent Room in "+strHostel);
                   }
		

        }
		if(flag == 0) JOptionPane.showMessageDialog(null,"No Hostel for "+strCourse+" of "+strYear);
	}  
        catch(SQLException sqle)
                   {
                     JOptionPane.showMessageDialog(null,sqle);
                   }
	
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


    public static void main (String args[])
    {
        roomAlot ra = new roomAlot("m120369ca","Amit Kumar Sah","Mca","2","male");

    }

}
