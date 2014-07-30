import java.net.*;	 /*Optional*/
import java.sql.*;	 /*For Sql Query*/
import java.awt.event.*;	 /*ActionListioner ,Click on Button for action*/
import javax.swing.*; 	/* Defined JButton,Jlabel  n Font etc containing elt*/
import java.awt.*;  	/*defind for Color,font,toolkit,Container,Frame (not JFrame) */

public class studentStatus extends JFrame
{
	private JTextField tRoll,tCourse;
	private JLabel lRoll,lCourse,lhead,lYear;
	String strRoll,strCourse,strYear,str,st,str1;
	private JButton bSearch,bExit;

	JComboBox cbCourse,cbYear;

    	Connection con;	//con is object of Connection class
    	Statement stmt;		//stmt is object of Statement class
    	private Icon icon; //for pics load
    	ResultSet rs;   //use for query	
	String[] strcb={"BTech","BArch","MBA","MCA","MTech","PHD"};
	String[] stryear={"1","2","3","4"};
	
	

	public studentStatus()
	{
		JFrame frame = new JFrame("Student Status");
                Toolkit tkt = frame.getToolkit();
                Dimension frsize = tkt.getScreenSize(); 
                          
              	frame.setBounds(frsize.width/8,frsize.height/8,frsize.width/4,frsize.height/2); //size of frame
              	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close pgm
            
		Container cn=frame.getContentPane();         
                cn.setLayout(null);
				
                lhead = new JLabel("Check student Status...! "); //heading
                lhead.setForeground(new Color(10,10,10));
                lhead.setFont(new Font("Algerian",Font.BOLD,40));
                lhead.setBounds(30,20,700,80);  //left,top ,width,height

 
                lRoll = new JLabel("Roll Number");  //label or Caption define
                lRoll.setFont(new Font("Serif",Font.BOLD,20));
                lRoll.setBounds(50,150,180,30);

                tRoll = new JTextField(20);	//text field define
                tRoll.setFont(new Font("Serif",Font.BOLD,20));
                tRoll.setBounds(250,150,200,30);  //position of text box adjust (left,top,width,height)


                lCourse = new JLabel("Course");
                lCourse.setFont(new Font("Serif",Font.BOLD,20));
                lCourse.setBounds(50,200,200,30);

                cbCourse=new JComboBox(strcb);
                cbCourse.setFont(new Font("Serif",Font.BOLD,20));
                cbCourse.setBounds(250,200,200,30);
		cbCourse.addActionListener(new addYearListener());

		lYear = new JLabel("Year");
                lYear.setFont(new Font("Serif",Font.BOLD,20));
                lYear.setBounds(50,250,200,30);

                cbYear=new JComboBox(stryear);
                cbYear.setFont(new Font("Serif",Font.BOLD,20));
                cbYear.setBounds(250,250,200,30);

              
	  	bSearch=new JButton("SEARCH");
                bSearch.setFont(new Font("Serif",Font.BOLD,20));
                bSearch.setMnemonic('F');
                bSearch.setBounds(150,300,150,30);
                bSearch.addActionListener(new searchListener());
                
                bExit=new JButton("EXIT");
                bExit.setFont(new Font("Serif",Font.BOLD,20));
                bExit.setMnemonic('X');
                bExit.setBounds(350,300,150,30);
                bExit.addActionListener(new exitListener(frame));
                

                cn.add(lhead);
                cn.add(lRoll);cn.add(tRoll); //load in container all label ,text and button
                cn.add(lCourse);cn.add(cbCourse);
		cn.add(lYear);cn.add(cbYear);
		
		cn.add(bSearch);cn.add(bExit);

 
                cn.setBackground(new Color(238,232,170));  //color code 
                frame.pack();  //bind
                frame.setSize(600,400); //framesize
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



  private class searchListener implements ActionListener
     {
      public void actionPerformed(ActionEvent e)
        {
         try
            {
            	   strRoll=tRoll.getText();
                   strCourse=(String)cbCourse.getSelectedItem();
		   strYear=(String)cbYear.getSelectedItem();

		   new status(strRoll,strCourse,strYear);
            }
            catch(Exception sq)
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
        studentStatus stud = new studentStatus();

}

}
