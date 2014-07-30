import java.net.*;	
import java.sql.*;	
import java.awt.event.*;	 
import javax.swing.*; 	
import java.awt.*;  	

public class courseTableEntry extends JFrame  
{	
    	private JLabel lhead,l1,l2,l3,l4;
  		String str1,str2,str3,str4;
    private JButton bsave,bdelete,bexit;  //button declaration

    JComboBox cbHostelName,cbCourse,cbYear,cbGender;  //dropdown box
    JPanel panel;		//panel to paste all element during runtime connectionstructor
    Connection connection;	//connection is object of Connection class
    Statement statement;		//statement is object of Statement class
    ResultSet rs;   //use for query
String[] strHostel={"A","B","C","D","E","F","FF","G","PG1","PG2","BMH","MLH"};
String[] strCourse={"BTech","BArch"};
String[] strYear={"1"};
String[] strGender={"male"};


 public courseTableEntry()  
        {
                              
             
               JFrame frame = new JFrame("Entry Form");
               Toolkit tkt = frame.getToolkit();
               Dimension frsize = tkt.getScreenSize(); 
                          
              frame.setBounds(frsize.width/8,frsize.height/8,frsize.width/4,frsize.height/2);
              frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
		Container cn=frame.getContentPane();
               
                cn.setLayout(null);
                						
               lhead = new JLabel("Cousrewise Hostel Detail"); 
               lhead.setForeground(Color.red);
               lhead.setFont(new Font("Algerian",Font.BOLD,40));
               lhead.setBounds(50,20,700,80);  
 
                l1 = new JLabel("Hostel Name");
                l1.setFont(new Font("Serif",Font.BOLD,20));
                l1.setBounds(100,150,180,30);

		 cbHostelName=new JComboBox(strHostel);
                 cbHostelName.setFont(new Font("Serif",Font.BOLD,20));
                 cbHostelName.setBounds(300,150,130,30);
		 cbHostelName.addActionListener(new hostelListener());

                l2= new JLabel("Course");
                l2.setFont(new Font("Serif",Font.BOLD,20));
                l2.setBounds(100,200,180,30);

		 cbCourse=new JComboBox(strCourse);
                 cbCourse.setFont(new Font("Serif",Font.BOLD,20));
                 cbCourse.setBounds(300,200,130,30);

		l3= new JLabel("Year");
                l3.setFont(new Font("Serif",Font.BOLD,20));
                l3.setBounds(100,250,180,30);

		 cbYear=new JComboBox(strYear);
                 cbYear.setFont(new Font("Serif",Font.BOLD,20));
                 cbYear.setBounds(300,250,130,30);

		l4= new JLabel("HostelFor");
                l4.setFont(new Font("Serif",Font.BOLD,20));
                l4.setBounds(100,300,180,30);

		 cbGender=new JComboBox(strGender);
                 cbGender.setFont(new Font("Serif",Font.BOLD,20));
                 cbGender.setBounds(300,300,130,30);
              

                bsave=new JButton("SAVE");
                bsave.setFont(new Font("Serif",Font.BOLD,20));
                bsave.setBounds(450,150,150,30);
                bsave.setMnemonic('S');
                bsave.addActionListener(new saveListener());

		bdelete=new JButton( "DELETE ");
                bdelete.setFont(new Font("Serif",Font.BOLD,20));
                bdelete.setBounds(450,200,150,30);
                bdelete.setMnemonic('D');
                bdelete.addActionListener(new deleteListener());
                
               bexit=new JButton("EXIT");
               bexit.setFont(new Font("Serif",Font.BOLD,20));
               bexit.setMnemonic('X');
               bexit.setBounds(450,250,150,30);
               bexit.addActionListener(new exitListener(frame));
                

                cn.add(lhead);cn.add(l1);cn.add(cbHostelName); //load in connectiontainer all label ,text and button
                cn.add(l2);cn.add(cbCourse);cn.add(l3);cn.add(cbYear); cn.add(l4);cn.add(cbGender); 
                cn.add(bdelete);cn.add(bexit);cn.add(bsave);
                
 
                cn.setBackground(new Color(170,170,160));  //color code 
                 frame.pack();  //bind
                frame.setSize(700,450); //framesize
                frame.setVisible(true);
                connect();  //function for connectionnect to database
                
       }

 private class hostelListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
          String st;
          st=(String)cbHostelName.getSelectedItem();
             try
                {
                  cbCourse.removeActionListener(this);
                  cbCourse.removeAllItems();

     		  cbYear.removeActionListener(this);
                  cbYear.removeAllItems();
		
		  cbGender.removeActionListener(this);
                  cbGender.removeAllItems();


			if(st.equals("A") || st.equals("PG1"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("BArch");cbYear.addItem("1");cbGender.addItem("male");
			}                 
			else if(st.equals("B") || st.equals("PG2"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("BArch");cbYear.addItem("2");cbGender.addItem("male");
			}                 

			else if(st.equals("C") || st.equals("BMH"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("BArch");cbYear.addItem("3");cbGender.addItem("male");
			}

			else if(st.equals("D") || st.equals("E") || st.equals("F"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("BArch");cbYear.addItem("4");cbGender.addItem("male");
			}

			else if(st.equals("G"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("PHD");cbYear.addItem("4");
			 cbYear.addItem("1");cbYear.addItem("2");cbYear.addItem("3");cbGender.addItem("male");
			}
			
			else if(st.equals("MLH"))
			{
			 cbCourse.addItem("MCA");cbCourse.addItem("BTech");
			 cbYear.addItem("1");cbYear.addItem("2");cbYear.addItem("3");cbGender.addItem("female");
			}
			
			else if(st.equals("FF"))
			{
			 cbCourse.addItem("MCA");cbCourse.addItem("MBA");cbCourse.addItem("MTech");cbGender.addItem("male");
			 cbYear.addItem("1");cbYear.addItem("2");
			}


                }

        	catch(Exception er)
        	{
                 JOptionPane.showMessageDialog(null,"Error Load");
        	}
         }
     }

   private class saveListener implements ActionListener
      {
       public void actionPerformed(ActionEvent e)
         {
           
            try
              {
                   str1=(String)cbHostelName.getSelectedItem();
                   str2=(String)cbCourse.getSelectedItem();
		   str3=(String)cbYear.getSelectedItem();
		   str4=(String)cbGender.getSelectedItem();
                   
                   statement.executeUpdate("insert into courseTableEntry values('"+str1+"','"+str2+"','"+str3+"','"+str4+"')");
                   statement.executeUpdate("commit");
                   JOptionPane.showMessageDialog(null,"Added ");
		  
           	}
        	catch(SQLException  sqle)
        	{
                 JOptionPane.showMessageDialog(null,"Uncompleted ");
        	}

         }
     }
 
 private class deleteListener implements ActionListener
    {
     public void actionPerformed(ActionEvent e)
       {
        try
           {
            str1=JOptionPane.showInputDialog("hostel Name");
	    str2=JOptionPane.showInputDialog("Course");
             rs=statement.executeQuery("select * from courseTableEntry where hostelName='"+str1+"' and course='"+str2+"'");
             rs.next();
             cbHostelName.setSelectedItem(rs.getString(1));
             cbCourse.setSelectedItem(rs.getString(2));
	     cbYear.setSelectedItem(rs.getString(3));
	     cbYear.setSelectedItem(rs.getString(4));
             
            statement.executeUpdate("delete from courseTableEntry where hostelName='"+str1+"' and course='"+str2+"'");                         
            statement.executeUpdate("Commit");
            JOptionPane.showMessageDialog(null,"Deleted");
         
           }
        catch(SQLException sqle)
         {
          JOptionPane.showMessageDialog(null,"Failure to Delete");
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
        courseTableEntry cte = new courseTableEntry();

    }
} 

