import java.net.*;	 /*Optional*/
import java.sql.*;	 /*For Sql Query*/
import java.awt.event.*;	 /*ActionListioner ,Click on Button for action*/
import javax.swing.*; 	/* Defined JButton,Jlabel  n Font etc containing elt*/
import java.awt.*;  	/*defind for Color,font,toolkit,Container,Frame (not JFrame) */
import java.util.logging.Level;
import java.util.logging.Logger;


public class student extends JFrame
{
	private JTextField tRoll,tName,tGender,tCourse,tEmail,tPerm,tContact;
	private JLabel lRoll,lName,lGender,lHostelName,lYear,lCourse,lEmail,lPerm,lContact,lhead;
	String strRoll,strName,strGender,strCourse,strHostel,strYear,strEmail,strPerm,strContact,str,st,str1;
	private JButton bFirst,bUpdate,bDelete,bSearch,bNext,bPrev,bExit;

	JComboBox cbCourse,cbHostelName,cbYear;
	setPicture a; //Class Object for call
	int f=0;	//flag

    	Connection con;	//con is object of Connection class
    	Statement stmt;		//stmt is object of Statement class
    	private Icon icon; //for pics load
    	ResultSet rs;   //use for query	
	String[] strhname={"A","B","C","D","E","F","G","FF","MLH","BMH","PG1","PG2"};
	String[] strcb={"BTech","BArch"};
	String[] stryear={"1"};
	

	public student()
	{
		JFrame frame = new JFrame("");
                Toolkit tkt = frame.getToolkit();
                Dimension frsize = tkt.getScreenSize(); 
                          
              	frame.setBounds(frsize.width/8,frsize.height/16,frsize.width/4,frsize.height/2); //size of frame
              	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close pgm
            
		Container cn=frame.getContentPane();         
                cn.setLayout(null);
				
                lhead = new JLabel("Student Informatiom"); //heading
                lhead.setForeground(new Color(10,10,10));
                lhead.setFont(new Font("Serif",Font.BOLD,35));
                lhead.setBounds(150,10,700,80);  

 
                lRoll = new JLabel("Roll");  //label or Caption define
                lRoll.setFont(new Font("Serif",Font.BOLD,20));
                lRoll.setBounds(50,150,180,30);

                tRoll = new JTextField(20);	//text field define
                tRoll.setFont(new Font("Serif",Font.BOLD,20));
                tRoll.setBounds(250,150,200,30);  //position of text box adjust (left,top,width,height)

                lName = new JLabel("Name");
                lName.setFont(new Font("Serif",Font.BOLD,20));
                lName.setBounds(50,200,200,30);

                tName = new JTextField(20);
                tName.setFont(new Font("Serif",Font.BOLD,20));
                tName.setBounds(250,200,200,30);

                lGender = new JLabel("Gender");
                lGender.setFont(new Font("Serif",Font.BOLD,20));
                lGender.setBounds(50,250,200,30);

                tGender = new JTextField(10);
                tGender.setFont(new Font("Serif",Font.BOLD,20));
                tGender.setBounds(250,250,200,30);
		tGender.setText("male");
		tGender.setEditable(false);

		lHostelName = new JLabel("Hostel Name");
                lHostelName.setFont(new Font("Serif",Font.BOLD,20));
                lHostelName.setBounds(50,300,200,30);

                cbHostelName=new JComboBox(strhname);
                cbHostelName.setFont(new Font("Serif",Font.BOLD,20));
                cbHostelName.setBounds(250,300,200,30);
		cbHostelName.addActionListener(new hostelListener());

                lCourse = new JLabel("Course");
                lCourse.setFont(new Font("Serif",Font.BOLD,20));
                lCourse.setBounds(50,350,200,30);

                cbCourse=new JComboBox(strcb);
                cbCourse.setFont(new Font("Serif",Font.BOLD,20));
                cbCourse.setBounds(250,350,200,30);

		lYear = new JLabel("Year");
                lYear.setFont(new Font("Serif",Font.BOLD,20));
                lYear.setBounds(50,400,200,30);

                cbYear=new JComboBox(stryear);
                cbYear.setFont(new Font("Serif",Font.BOLD,20));
                cbYear.setBounds(250,400,200,30);


                lEmail = new JLabel("Email");
                lEmail.setFont(new Font("Serif",Font.BOLD,20));
                lEmail.setBounds(50,450,205,30);

                tEmail= new JTextField(20);
                tEmail.setFont(new Font("Serif",Font.BOLD,20));
                tEmail.setBounds(250,450,200,30);
                
                
                lPerm= new JLabel("Permanent Address");
                lPerm.setFont(new Font("Serif",Font.BOLD,20));
                lPerm.setBounds(0,500,250,30);

                tPerm = new JTextField(30);
                tPerm.setFont(new Font("Serif",Font.BOLD,20));
                tPerm.setBounds(250,500,200,30);


                lContact= new JLabel("Contact");
                lContact.setFont(new Font("Serif",Font.BOLD,20));
                lContact.setBounds(50,550,200,30);

                tContact= new JTextField(20);
                tContact.setFont(new Font("Serif",Font.BOLD,16));
                tContact.setBounds(250,550,200,30);

               bFirst=new JButton("FIRST");				//button define
                bFirst.setFont(new Font("Serif",Font.BOLD,20));
                bFirst.setBounds(500,250,150,30);
                bFirst.setMnemonic('S');
                bFirst.addActionListener(new firstListener());
			
                bDelete=new JButton("DELETE ");
                bDelete.setFont(new Font("Serif",Font.BOLD,20));
                bDelete.setBounds(500,300,150,30);
                bDelete.setMnemonic('D');
               bDelete.addActionListener(new deleteListener());
                
                
                
                bUpdate=new JButton("UPDATE");
                bUpdate.setFont(new Font("Serif",Font.BOLD,20));
                bUpdate.setBounds(500,350,150,30);
                bUpdate.setMnemonic('U');
                bUpdate.addActionListener(new updateListener());
                

		bSearch=new JButton("SEARCH");
               bSearch.setFont(new Font("Serif",Font.BOLD,20));
               bSearch.setMnemonic('F');
               bSearch.setBounds(500,400,150,30);
               bSearch.addActionListener(new searchListener());

                
               bPrev=new JButton("PREVIOUS");
               bPrev.setFont(new Font("Serif",Font.BOLD,20));
               bPrev.setBounds(500,450,170,30);
               bPrev.setMnemonic('P');
               bPrev.addActionListener(new previousListener());
   
               
               bNext=new JButton("NEXT");
               bNext.setFont(new Font("Serif",Font.BOLD,20));
               bNext.setBounds(500,500,150,30);
               bNext.setMnemonic('N');
               bNext.addActionListener(new nextListener());

                
               bExit=new JButton("EXIT");
               bExit.setFont(new Font("Serif",Font.BOLD,20));
               bExit.setMnemonic('X');
               bExit.setBounds(500,550,150,30);
               bExit.addActionListener(new exitListener(frame));
                

                cn.add(lhead);
                cn.add(lRoll);cn.add(tRoll); //load in container all label ,text and button
                cn.add(lName);cn.add(tName);
		cn.add(lGender);cn.add(tGender);
                cn.add(lCourse);cn.add(cbCourse);
		cn.add(lHostelName);cn.add(cbHostelName);
		cn.add(lYear);cn.add(cbYear);  
                cn.add(lEmail);cn.add(tEmail);
                cn.add(lPerm );cn.add(tPerm);
                cn.add(lContact);cn.add(tContact);
                
                cn.add(bDelete);
                cn.add(bFirst);cn.add(bUpdate);
                cn.add(bNext);cn.add(bPrev);
                cn.add(bSearch);cn.add(bExit);

 
                cn.setBackground(new Color(194,239,215));  //color code 
                frame.pack();  //bind
                frame.setSize(820,650); //framesize
                frame.setVisible(true);
                connect();  //function for connect to database		

	}

		public void picLoad(String strRoll)
		{
		   if(f==1) a.dispose();
		    a=new setPicture(strRoll);
		  f=1;
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
		
		  
			if(st.equals("A") || st.equals("PG1"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("BArch");cbYear.addItem("1");tGender.setText("male");
			}                 
			else if(st.equals("B") || st.equals("PG2"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("BArch");cbYear.addItem("2");tGender.setText("male");
			}                 

			else if(st.equals("C") || st.equals("BMH"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("BArch");cbYear.addItem("3");tGender.setText("male");
			}

			else if(st.equals("D") || st.equals("E") || st.equals("F"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("BArch");cbYear.addItem("4");tGender.setText("male");
			}

			else if(st.equals("G"))
			{
			 cbCourse.addItem("BTech");cbCourse.addItem("PHD");cbYear.addItem("4");
			 cbYear.addItem("1");cbYear.addItem("2");cbYear.addItem("3");tGender.setText("male");
			}
			
			else if(st.equals("MLH"))
			{
			 cbCourse.addItem("MCA");cbCourse.addItem("BTech");
			 cbYear.addItem("1");cbYear.addItem("2");cbYear.addItem("3");tGender.setText("female");
			}
			
			else if(st.equals("FF"))
			{
			 cbCourse.addItem("MCA");cbCourse.addItem("MBA");cbCourse.addItem("MTech");tGender.setText("male");
			 cbYear.addItem("1");cbYear.addItem("2");
			}


                }

        	catch(Exception er)
        	{
                 JOptionPane.showMessageDialog(null,"Error Load");
        	}
         }
     }


  private class firstListener implements ActionListener
     {
      public void actionPerformed(ActionEvent e)
       {
        try
           {
            rs=stmt.executeQuery("select * from student");
            rs.next();
             tRoll.setText(rs.getString(1));
             tName.setText(rs.getString(2));
            tGender.setText(rs.getString(3));
	    cbCourse.setSelectedItem(rs.getString(4));
	    cbYear.setSelectedItem(rs.getString(5));
	    cbHostelName.setSelectedItem(rs.getString(6));
            tEmail.setText(rs.getString(7));
            tPerm.setText(rs.getString(8));
            tContact.setText(rs.getString(9));
	    
	    picLoad(rs.getString(1)); //Loading Picutre on new Frame

           }
         catch(SQLException sqle)
          {
           JOptionPane.showMessageDialog(null,"Error in Loading");
          }
            
     }

   }
			

private class deleteListener implements ActionListener
    {	
     public void actionPerformed(ActionEvent e)
       {
        try
           {
            strRoll=JOptionPane.showInputDialog("Roll To Delete");

             rs=stmt.executeQuery("select * from student where std_roll='"+strRoll+"'");
             rs.next();
             tRoll.setText(rs.getString(1));
             tName.setText(rs.getString(2));
            tGender.setText(rs.getString(3));
	    cbCourse.setSelectedItem(rs.getString(4));
	    cbYear.setSelectedItem(rs.getString(5));
	    cbHostelName.setSelectedItem(rs.getString(6));
            tEmail.setText(rs.getString(7));
            tPerm.setText(rs.getString(8));
            tContact.setText(rs.getString(9));
             

            stmt.executeUpdate("delete from student where std_roll='"+strRoll+"'");                         
            stmt.executeUpdate("Commit");
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
            strRoll=tRoll.getText();
            strName=tName.getText();
            strGender=tGender.getText();
            strCourse=(String)cbCourse.getSelectedItem();
            strYear=(String)cbYear.getSelectedItem();
            strHostel=(String)cbHostelName.getSelectedItem();
            strEmail=tEmail.getText();
            strPerm=tPerm.getText();
            strContact=tContact.getText();
            

            stmt.executeUpdate("Update student set std_name='"+strName+"',gender='"+strGender+"',course='"+strCourse+"',year='"+strYear+"',hostelName='"+strHostel+"',email='"+strEmail+"',permanent_add='"+strPerm+"'where std_roll='"+strRoll+"'");
            stmt.executeUpdate("commit");
	    picLoad(strRoll); //Loading Picutre on new Frame

            JOptionPane.showMessageDialog(null,"updated");

          }
        catch(SQLException  sqle)
          {
            System.out.println("Could not update"+sqle);
          }

     }
   }

 private class nextListener implements ActionListener
    {
     public void actionPerformed(ActionEvent e)
      {
       
       try
          {
           str=tRoll.getText();
           if(str.equals(""))
             JOptionPane.showMessageDialog(null,"No Record Select");
           else
           {
              rs=stmt.executeQuery("select * from student");
            while(rs.next())
            {
             st=rs.getString(1);
             if(str.compareTo(st)==0)
             break;
            }
            rs.next();
            tRoll.setText(rs.getString(1));
             tName.setText(rs.getString(2));
            tGender.setText(rs.getString(3));
	    cbCourse.setSelectedItem(rs.getString(4));
	    cbYear.setSelectedItem(rs.getString(5));
	    cbHostelName.setSelectedItem(rs.getString(6));
            tEmail.setText(rs.getString(7));
            tPerm.setText(rs.getString(8));
            tContact.setText(rs.getString(9));
             	    picLoad(rs.getString(1)); //Loading Picutre on new Frame

           }
          }
       catch(SQLException sqle)
       {
        JOptionPane.showMessageDialog(null,"This is last record");
       }
      }
   }


 public class previousListener implements ActionListener
   {
    public void actionPerformed(ActionEvent e)
      {
	int c,k;
       str=tRoll.getText();
       try
          {
           rs=stmt.executeQuery("select * from student");
            c=0;
            while(rs.next())
            {
             st=rs.getString(1);
             if(str.compareTo(st)==0)

             break;
             c++;
            }

         rs=stmt.executeQuery("select * from student");
           k=0;
           while(rs.next())
            {
             k++;
              if(k==c)
              break;
            }
           tRoll.setText(rs.getString(1));
             tName.setText(rs.getString(2));
            tGender.setText(rs.getString(3));
	    cbCourse.setSelectedItem(rs.getString(4));
	    cbYear.setSelectedItem(rs.getString(5));
	    cbHostelName.setSelectedItem(rs.getString(6));
            tEmail.setText(rs.getString(7));
            tPerm.setText(rs.getString(8));
            tContact.setText(rs.getString(9));

	    picLoad(rs.getString(1)); //Loading Picutre on new Frame

         }
         catch(SQLException sqle)
          {
           JOptionPane.showMessageDialog(null,"This is First Record");
          }

     }
  }

  private class searchListener implements ActionListener
     {
      public void actionPerformed(ActionEvent e)
        {
         try
            {
             st=JOptionPane.showInputDialog("Enter the Roll to be searched");
             rs=stmt.executeQuery("select * from student where std_roll='"+st+"'");
             rs.next();
             tRoll.setText(rs.getString(1));
             tName.setText(rs.getString(2));
            tGender.setText(rs.getString(3));
	    cbCourse.setSelectedItem(rs.getString(4));
	    cbYear.setSelectedItem(rs.getString(5));
	    cbHostelName.setSelectedItem(rs.getString(6));
            tEmail.setText(rs.getString(7));
            tPerm.setText(rs.getString(8));
            tContact.setText(rs.getString(9));

	    	    picLoad(rs.getString(1)); //Loading Picutre on new Frame
             JOptionPane.showMessageDialog(null,"Searched");
            }
            catch(SQLException sqle)
             {
              JOptionPane.showMessageDialog(null,"Not Exist");
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
	  //a.dispose();
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
        student stud = new student();

    }

}
