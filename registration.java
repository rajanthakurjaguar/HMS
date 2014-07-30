import java.net.*;	 /*Optional*/
import java.sql.*;	 /*For Sql Query*/
import java.awt.event.*;	 /*ActionListioner ,Click on Button for action*/
import javax.swing.*; 	/* Defined JButton,Jlabel  n Font etc containing elt*/
import java.awt.*;  	/*defind for Color,font,toolkit,Container,Frame (not JFrame) */

public class registration extends JFrame
{
	private JTextField tRoll,tName,tEmail,tPerm,tContact;
	private JLabel lRoll,lName,lYear,lCourse,lGender,lEmail,lPerm,lContact,lhead;
	String strRoll,strName,strCourse,strGender,strHostel,strYear,strEmail,strPerm,strContact,str,st,str1;
	private JButton bNext,bClear,bExit;

	JComboBox cbCourse,cbYear,cbGender;
	roomAlot ra; //Class defined for call

    	Connection con;	//con is object of Connection class
    	Statement stmt;		//stmt is object of Statement class
    	private Icon icon; //for pics load
    	ResultSet rs;   //use for query	
	String[] strcb={"BTech","BArch","MBA","MCA","MTech","PHD"};
	String[] stryear={"1","2","3","4"};
	String[] strGend={"male","female"};
	

	public registration()
	{
		JFrame frame = new JFrame("Registration");
                Toolkit tkt = frame.getToolkit();
                Dimension frsize = tkt.getScreenSize(); 
                          
              	frame.setBounds(frsize.width/16,frsize.height/16,frsize.width/4,frsize.height/2); //size of frame
              	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close pgm
            
		Container cn=frame.getContentPane();         
                cn.setLayout(null);
				
                lhead = new JLabel("Student's Hostel Registration "); //heading
                lhead.setForeground(new Color(10,10,10));
                lhead.setFont(new Font("Algerian",Font.BOLD,40));
                lhead.setBounds(50,20,700,80);  

                lRoll = new JLabel("Roll");  //label or Caption define
                lRoll.setFont(new Font("Serif",Font.BOLD,20));
                lRoll.setBounds(150,150,180,30);

                tRoll = new JTextField(20);	//text field define
                tRoll.setFont(new Font("Serif",Font.BOLD,20));
                tRoll.setBounds(350,150,200,30);  //position of text box adjust (left,top,width,height)

                lName = new JLabel("Name");
                lName.setFont(new Font("Serif",Font.BOLD,20));
                lName.setBounds(150,200,200,30);

                tName = new JTextField(20);
                tName.setFont(new Font("Serif",Font.BOLD,18));
                tName.setBounds(350,200,200,30);

                lGender = new JLabel("Gender");
                lGender.setFont(new Font("Serif",Font.BOLD,20));
                lGender.setBounds(150,250,200,30);

                cbGender=new JComboBox(strGend);
                cbGender.setFont(new Font("Serif",Font.BOLD,24));
                cbGender.setBounds(350,250,200,30);
		cbGender.addActionListener(new addYearListener());

                lCourse = new JLabel("Course");
                lCourse.setFont(new Font("Serif",Font.BOLD,20));
                lCourse.setBounds(150,300,200,30);

                cbCourse=new JComboBox(strcb);
                cbCourse.setFont(new Font("Serif",Font.BOLD,24));
                cbCourse.setBounds(350,300,200,30);
		cbCourse.addActionListener(new addYearListener());

		lYear = new JLabel("Year");
                lYear.setFont(new Font("Serif",Font.BOLD,20));
                lYear.setBounds(150,350,200,30);

                cbYear=new JComboBox(stryear);
                cbYear.setFont(new Font("Serif",Font.BOLD,20));
                cbYear.setBounds(350,350,200,30);

                lEmail = new JLabel("Email");
                lEmail.setFont(new Font("Serif",Font.BOLD,20));
                lEmail.setBounds(150,400,205,30);

                tEmail= new JTextField(20);
                tEmail.setFont(new Font("Serif",Font.BOLD,16));
                tEmail.setBounds(350,400,200,30);
		tEmail.setText("@gmail.com");
                
                
                lPerm= new JLabel("Permanent Address");
                lPerm.setFont(new Font("Serif",Font.BOLD,20));
                lPerm.setBounds(100,450,250,30);

                tPerm = new JTextField(30);
                tPerm.setFont(new Font("Serif",Font.BOLD,16));
                tPerm.setBounds(350,450,200,30);


                lContact= new JLabel("Contact");
                lContact.setFont(new Font("Serif",Font.BOLD,20));
                lContact.setBounds(150,500,200,30);

                tContact= new JTextField(20);
                tContact.setFont(new Font("Serif",Font.BOLD,16));
                tContact.setBounds(350,500,200,30);

                bNext=new JButton("Next");				//button define
                bNext.setFont(new Font("Serif",Font.BOLD,20));
                bNext.setBounds(500,600,100,30);
                bNext.setMnemonic('N');
                bNext.addActionListener(new nextListener());
			
               bClear=new JButton("Clear");
               bClear.setFont(new Font("Serif",Font.BOLD,20));
               bClear.setBounds(400,600,100,30);
               bClear.setMnemonic('l');
               bClear.addActionListener(new clearListener());

                
               bExit=new JButton("Cancel");
               bExit.setFont(new Font("Serif",Font.BOLD,20));
               bExit.setMnemonic('C');
               bExit.setBounds(600,600,130,30);
               bExit.addActionListener(new exitListener(frame));
                

                cn.add(lhead);
                cn.add(lRoll);cn.add(tRoll); //load in container all label ,text and button
                cn.add(lName);cn.add(tName);
		cn.add(lGender);cn.add(cbGender);
                cn.add(lCourse);cn.add(cbCourse);
		cn.add(lYear);cn.add(cbYear);  
                cn.add(lEmail);cn.add(tEmail);
                cn.add(lPerm );cn.add(tPerm);
                cn.add(lContact);cn.add(tContact);
                
                cn.add(bNext);cn.add(bExit);cn.add(bClear);

 
                cn.setBackground(new Color(95,158,160));  //color code 
                frame.pack();  //bind
                frame.setSize(850,700); //framesize
                frame.setVisible(true);
                connect();  //function for connect to database		

	}

private class addYearListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
          String st=(String)cbCourse.getSelectedItem();
             try
                {
                  cbYear.removeActionListener(this);
                  cbYear.removeAllItems();

			if(st.equals("BTech") || st.equals("BArch"))
			{
			 cbYear.addItem("1");cbYear.addItem("2");cbYear.addItem("3");cbYear.addItem("4");
			}                 
			else if(st.equals("MCA"))
			{
			 cbYear.addItem("1");cbYear.addItem("2");cbYear.addItem("3");
			}  
			else if(st.equals("MTech"))
			{
			 cbYear.addItem("1");cbYear.addItem("2");
			}
			else if(st.equals("MBA"))
			{
			 cbYear.addItem("1");cbYear.addItem("2");
			}                 	
			else if(st.equals("PHD"))
			{
			 cbYear.addItem("1");cbYear.addItem("2");cbYear.addItem("3");
			}                 

                }

        	catch(Exception er)
        	{
                 JOptionPane.showMessageDialog(null,"Error Load");
        	}
         }
     }


private class clearListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
         	{
           		clear();
		}
	}
	private class nextListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
         	{
           
            	try
              	{
                   strRoll=tRoll.getText();
                   strName=tName.getText();
 		   strGender=(String)cbGender.getSelectedItem();
                   strCourse=(String)cbCourse.getSelectedItem();
		   strHostel="";
		   strYear=(String)cbYear.getSelectedItem();
                   strEmail=tEmail.getText();
                   strPerm=tPerm.getText();
                   strContact=tContact.getText();
                   
		  if(strRoll.equals("") || strName.equals("") || strContact.equals(""))
		    JOptionPane.showMessageDialog(null,"Blank Fields");
			else
		  {
stmt.executeUpdate("insert into student values('"+strRoll.trim()+"','"+strName.trim()+"','"+strGender.trim()+"','"+strCourse.trim()+"','"+strYear.trim()+"','"+strHostel.trim()+"','"+strEmail+"','"+strPerm.trim()+"','"+strContact+"')");
                   stmt.executeUpdate("commit");

		   ra = new roomAlot(strRoll,strName,strCourse,strYear,strGender);
		   }
        	}
        	catch(SQLException  sqle)
        	{
                 JOptionPane.showMessageDialog(null,"Already Exists :"+strRoll);
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
	  ra.dispose();
        }
    	
    }

     public void clear()
      {

       try
          {
           tRoll.setText("");
           tRoll.requestFocus();
           tName.setText(" ");
	   cbGender.setSelectedIndex(0);
	   cbCourse.setSelectedIndex(0);
           cbYear.setSelectedIndex(0);
           tEmail.setText("@gmail.com");
           tPerm.setText(" ");
           tContact.setText(" ");
	   
          }
         catch(Exception e)
          {
           JOptionPane.showMessageDialog(null,"Error");
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
        registration rj = new registration();

    }

}
