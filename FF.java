import java.net.*;	
import java.sql.*;	
import java.awt.event.*;	 
import javax.swing.*; 	/* Defined JButton,Jlabel  n Font etc connectiontaining elt*/
import java.awt.*;  	/*defind for Color,font,toolkit,Container,Frame (not JFrame) */


public class FF extends JFrame  
{
    private JTextField tstd_roll;	
    	private JLabel lroom_no,lseat_no,lstd_roll,lstatus,lhead,lpic;
  		String str1,str2,str3,str4,str,st,str5;
    private JButton bsave,bupdate,bdelete,bexit;  //button declaration

    JComboBox cbSeat_no,cbroom,cbstatus;  //dropdown box
    JPanel panel;		//panel to paste all element during runtime connectionstructor
    Connection connection;	//connection is object of Connection class
    Statement statement;		//statement is object of Statement class
    private Icon icon; //for pics load
    ResultSet rs;   //use for query
String[] strcb={"1","2","3","4","5","6"};
String[] stp={"V","F"};
String[] room={"FF01","FF02","FF03","FF04","FF05","FF06","FF07","FF08","FF09","FF10","FF11","FF12","FF13","FF14","FF15","FF16","FF17","FF18","FF19","FF20"};

 public FF()  //Constructor
        {
                              
             
               JFrame frame = new JFrame("Hostel FF");
               Toolkit tkt = frame.getToolkit();
               Dimension frsize = tkt.getScreenSize(); 
                          
              frame.setBounds(frsize.width/8,frsize.height/8,frsize.width/4,frsize.height/2); //size of frame
              frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close pgm
            
		Container cn=frame.getContentPane();
               
                cn.setLayout(null);
                icon=new ImageIcon("photo/ff.jpg"); //pic on form
                lpic=new JLabel(icon);
                lpic.setBounds(0,0,275,300);

		panel=new JPanel();
		panel.setBounds(5,100,275,320);
		panel.add(lpic);
		cn.add(panel);
						
               lhead = new JLabel("Hostel FF Data Entry "); //heading
               lhead.setForeground(Color.red);
               lhead.setFont(new Font("Algerian",Font.BOLD,40));
               lhead.setBounds(300,20,910,80);  
 
                lroom_no = new JLabel("Room Number");  //label or Caption define
                lroom_no.setFont(new Font("Serif",Font.BOLD,25));
                lroom_no.setBounds(310,150,220,30);
                
                cbroom=new JComboBox(room);
                cbroom.setFont(new Font("Serif",Font.BOLD,25));
                cbroom.setBounds(550,150,200,30);

                lseat_no = new JLabel("Seat Number");
                lseat_no.setFont(new Font("Serif",Font.BOLD,25));
                lseat_no.setBounds(310,200,200,30);

		cbSeat_no=new JComboBox(strcb);
                 cbSeat_no.setFont(new Font("Serif",Font.BOLD,25));
                 cbSeat_no.setBounds(550,200,200,30);


                lstd_roll = new JLabel("Roll Number");
                lstd_roll.setFont(new Font("Serif",Font.BOLD,25));
                lstd_roll.setBounds(310,250,200,30);

                tstd_roll= new JTextField(25);
                tstd_roll.setFont(new Font("Serif",Font.BOLD,25));
                tstd_roll.setBounds(550,250,200,30);
		tstd_roll.setEditable(false);

 

                lstatus = new JLabel("Status");
                lstatus.setFont(new Font("Serif",Font.BOLD,25));
                lstatus.setBounds(310,300,200,30);

                cbstatus= new JComboBox(stp);
                cbstatus.setFont(new Font("Serif",Font.BOLD,25));
                cbstatus.setBounds(550,300,200,30);
                
                

                bsave=new JButton("SAVE");//button define
                bsave.setFont(new Font("Serif",Font.BOLD,20));
                bsave.setBounds(310,350,150,30);
                bsave.setMnemonic('S');
                bsave.addActionListener(new saveListener());

		bdelete=new JButton( "DELETE ");
                bdelete.setFont(new Font("Serif",Font.BOLD,20));
                bdelete.setBounds(465,350,150,30);
                bdelete.setMnemonic('D');
                bdelete.addActionListener(new deleteListener());
                
              
                bupdate=new JButton("UPDATE");
                bupdate.setFont(new Font("Serif",Font.BOLD,20));
                bupdate.setBounds(620,350,150,30);
                bupdate.setMnemonic('U');
                bupdate.addActionListener(new updateListener());
                
               bexit=new JButton("EXIT");
               bexit.setFont(new Font("Serif",Font.BOLD,20));
               bexit.setMnemonic('X');
               bexit.setBounds(460,400,120,30);
               bexit.addActionListener(new exitListener(frame));
                

                cn.add(lhead);cn.add(lroom_no);cn.add(cbroom); //load in connectiontainer all label ,text and button
                cn.add(lseat_no);cn.add(cbSeat_no);cn.add(lstd_roll);cn.add(tstd_roll);  
                cn.add(lstatus);cn.add(cbstatus);cn.add(bdelete);cn.add(bexit);cn.add(bsave);cn.add(bupdate);
                
 
                cn.setBackground(new Color(180,170,160));  //color code 
                 frame.pack();  //bind
                frame.setSize(920,480); //framesize
                frame.setVisible(true);
                connect();  //function for connectionnect to database
                
       }
   private class saveListener implements ActionListener
      {
       public void actionPerformed(ActionEvent e)
         {
           
            try
              {
                   str1=(String)cbroom.getSelectedItem();
                   str2=(String)cbSeat_no.getSelectedItem();
		   str3=tstd_roll.getText();
                   str4=(String)cbstatus.getSelectedItem();

                   statement.executeUpdate("insert into FF values('"+str1+"','"+str2+"','"+str3+"','"+str4+"')");
                   statement.executeUpdate("commit");
                   JOptionPane.showMessageDialog(null,"Inserted");
		   
		  
           	}
        	catch(SQLException  sqle)
        	{
                 JOptionPane.showMessageDialog(null,"Error in Insert"+sqle);
        	}

         }
        

     }
 
 private class deleteListener implements ActionListener
    {
     public void actionPerformed(ActionEvent e)
       {
        try
           {
            str1=JOptionPane.showInputDialog("Room Number");
	    str2=JOptionPane.showInputDialog("Seat Number");
             rs=statement.executeQuery("select * from FF where room_no='"+str1+"' and seat_no='"+str2+"'");
             rs.next();
             cbroom.setSelectedItem(rs.getString(1));
             cbSeat_no.setSelectedItem(rs.getString(2));
             tstd_roll.setText(rs.getString(3));
             cbstatus.setSelectedItem(rs.getString(4));

            statement.executeUpdate("delete from FF where room_no='"+str1+"' and seat_no='"+str2+"'");                         
            statement.executeUpdate("Commit");
            JOptionPane.showMessageDialog(null,"Deleted");
         
           }
        catch(SQLException sqle)
         {
          JOptionPane.showMessageDialog(null,"Failure to Delete");
         }
                             
      }
    }

  private class updateListener implements ActionListener
    {
     public void actionPerformed(ActionEvent e)
       {
        try
           {
            str1=(String)cbroom.getSelectedItem();
            str2=(String)cbSeat_no.getSelectedItem();
            str3=tstd_roll.getText();
            str4=(String)cbstatus.getSelectedItem();

            statement.executeUpdate("update FF set std_roll='"+str3+"',status='"+str4+"' where room_no='"+str1+"' and seat_no='"+str2+"'");
            statement.executeUpdate("commit");
            JOptionPane.showMessageDialog(null,"updated");

          }
        catch(SQLException  sqle)
          {
            System.out.println("Could not update"+sqle);
          }

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
         	 connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/root","root","");  
         	 statement= connection.createStatement();
         	 
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
        FF a = new FF();

    }
} 

