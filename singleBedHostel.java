import java.net.*;	
import java.sql.*;	
import java.awt.event.*;	
import javax.swing.*; 	
import java.awt.*;  	


public class singleBedHostel extends JFrame  
{
        private JTextField troom_no,tstd_roll;	
    	private JLabel lhostelname,lroom_no,lseat_no,lstd_roll,lstatus,lhead,lpic,l1,l2;
  	String str1,str2,str3,str4,str5,str6;
        private JButton bsave,bupdate,bdelete,bexit;  

    JComboBox cbhostelname,cbstatus;  
    JPanel panel;		
    Connection connection;	
    Statement statement;		
    private Icon icon; 
    ResultSet rs;   
String[] hname={"D","E","F","G","MLH","BMH"};
String[] status={"V","F"};


 public singleBedHostel()  
        {
                              
             
               JFrame frame = new JFrame("Hostels");
               Toolkit tkt = frame.getToolkit();
               Dimension frsize = tkt.getScreenSize(); 
                          
              frame.setBounds(frsize.width/8,frsize.height/8,frsize.width/4,frsize.height/2); 
              frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
            
		Container cn=frame.getContentPane();
               cn.setLayout(null);

                icon=new ImageIcon("photo/g.jpg"); 
                lpic=new JLabel(icon);
                lpic.setBounds(0,0,370,390);

		panel=new JPanel();
		panel.setBounds(0,0,370,390);
		panel.add(lpic);
		cn.add(panel);
						
               lhead = new JLabel("Single Bed Room "); //heading
               lhead.setForeground(Color.red);
               lhead.setFont(new Font("Arial",Font.BOLD,30));
               lhead.setBounds(400,10,910,50);  
 		
		lhostelname = new JLabel("Hostel Name");  //label or Caption define
                lhostelname.setFont(new Font("Serif",Font.BOLD,20));
                lhostelname.setBounds(400,100,180,30);
		
		 cbhostelname=new JComboBox(hname);
                 cbhostelname.setFont(new Font("Serif",Font.BOLD,20));
                 cbhostelname.setBounds(600,100,140,30);
            

                lroom_no = new JLabel("Room Number");  //label or Caption define
                lroom_no.setFont(new Font("Serif",Font.BOLD,20));
                lroom_no.setBounds(400,150,180,30);

                troom_no = new JTextField(20);	//text field define
                troom_no.setFont(new Font("Serif",Font.BOLD,20));
                troom_no.setBounds(600,150,140,30);  //position of text box adjust (left,top,width,height)

                lstd_roll = new JLabel("Roll Number");
                lstd_roll.setFont(new Font("Serif",Font.BOLD,20));
                lstd_roll.setBounds(400,200,180,30);

                tstd_roll= new JTextField(20);
                tstd_roll.setFont(new Font("Serif",Font.BOLD,20));
                tstd_roll.setBounds(600,200,140,30);
		//tstd_roll.setEditable(false);

                lstatus = new JLabel("Status");
                lstatus.setFont(new Font("Serif",Font.BOLD,20));
                lstatus.setBounds(400,250,180,30);

          	 cbstatus=new JComboBox(status);
                 cbstatus.setFont(new Font("Serif",Font.BOLD,20));
                 cbstatus.setBounds(600,250,140,30);
      		
		l1 = new JLabel("V: Vacent");
		l1.setFont(new Font("Serif",Font.BOLD,20));
                l1.setBounds(420,280,180,20);
                
		l2 = new JLabel("F: Fill");
		l2.setFont(new Font("Serif",Font.BOLD,20));
                l2.setBounds(580,280,180,20);
                
                bsave=new JButton("SAVE");//button define
                bsave.setFont(new Font("Serif",Font.BOLD,20));
                bsave.setBounds(400,320,150,30);
                bsave.setMnemonic('S');
                bsave.addActionListener(new saveListener());

		bdelete=new JButton( "DELETE ");
                bdelete.setFont(new Font("Serif",Font.BOLD,20));
                bdelete.setBounds(580,320,150,30);
                bdelete.setMnemonic('D');
                bdelete.addActionListener(new deleteListener());
                
              
                bupdate=new JButton("UPDATE");
                bupdate.setFont(new Font("Serif",Font.BOLD,20));
                bupdate.setBounds(400,350,150,30);
                bupdate.setMnemonic('U');
                bupdate.addActionListener(new updateListener());
                
               bexit=new JButton("EXIT");
               bexit.setFont(new Font("Serif",Font.BOLD,20));
               bexit.setMnemonic('X');
               bexit.setBounds(580,350,150,30);
               bexit.addActionListener(new exitListener(frame));
                

                cn.add(lhead);cn.add(lroom_no);cn.add(troom_no); //load in connectiontainer all label ,text and button
                cn.add(lhostelname);cn.add(cbhostelname);cn.add(lstd_roll);cn.add(tstd_roll);  
                cn.add(lstatus);cn.add(cbstatus);cn.add(l1);cn.add(l2);
		cn.add(bdelete);cn.add(bexit);cn.add(bsave);cn.add(bupdate);
                
 
                cn.setBackground(new Color(200,180,200));  //color code 
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
		   str1=(String)cbhostelname.getSelectedItem();
                   str2=troom_no.getText();
		   str3=tstd_roll.getText();
                   str4=(String)cbstatus.getSelectedItem();
		   str5="1";

                   statement.executeUpdate("insert into "+str1+" values('"+str2+"','"+str6+"','"+str3+"','"+str4+"')");
                   statement.executeUpdate("commit");
                   JOptionPane.showMessageDialog(null,"Added Room in hostel: " +str1);
		   troom_no.requestFocus();
		  
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
            str1=JOptionPane.showInputDialog("Hostel Name");
	    str2=JOptionPane.showInputDialog("Room Number");
             rs=statement.executeQuery("select * from "+str1+" where room_no='"+str2+"'");
             rs.next();
	     cbhostelname.setSelectedItem(str1);
             troom_no.setText(rs.getString(1));
             tstd_roll.setText(rs.getString(3));
             cbstatus.setSelectedItem(rs.getString(4));

            statement.executeUpdate("delete from "+str1+" where room_no='"+str2+"'");                         
            statement.executeUpdate("Commit");
            JOptionPane.showMessageDialog(null,"Deleted");
         
           }
        catch(SQLException sqle)
         {
          JOptionPane.showMessageDialog(null,"Error");
         }
                             
      }
    }

  private class updateListener implements ActionListener
    {
     public void actionPerformed(ActionEvent e)
       {
        try
           {
            str1=(String)cbhostelname.getSelectedItem();
            str2=troom_no.getText();
	    str3=tstd_roll.getText();
            str4=(String)cbstatus.getSelectedItem();

            statement.executeUpdate("update "+str1+" set std_roll='"+str3+"',status='"+str4+"' where room_no='"+str2+"'");
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
        singleBedHostel sbh = new singleBedHostel();

    }
} 

