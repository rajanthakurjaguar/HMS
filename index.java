import java.lang.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.JTable.*;
import java.awt.print.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import static java.awt.event.InputEvent.*;
public class index extends JFrame
{
  private JMenuBar menubar=new JMenuBar();
  private JMenu repoMenu,hostelMenu,devopMenu;
  private JMenuItem smenu1,smenu2,smenu3,smenu4,smenu5,smenu6,smenu7;
  private JMenuItem menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7,menuItem8,menuItem9,menuItem10,menuItem11,menuItem12,menuItem13;

  
  private JLabel l1,l2,l3,l4,l5,l6;
  private JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
  private JPanel p1,p2,p3,p4,p5;
  private Container cn;
  private String str1,str2,str3,str4,str5,str6,str;
  private Connection connection;
  private Statement statement;
  private ResultSet rs;
  Cursor cr=new Cursor(Cursor.HAND_CURSOR);
  int height,width;

  public index()
  {
    super("NITC");setLayout(null);
    Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    height=screensize.height;
    width=screensize.width;
    setBounds(0,0,width,height);
    setSize(width,height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    cn=getContentPane();
    cn.setBackground(new Color(160,140,100));

    setJMenuBar(menubar);

    //MENUS

    repoMenu=new JMenu("  Report");
    hostelMenu=new JMenu("  Hostels ");
    devopMenu=new JMenu("Developer");
 
    //MENU MENUITEMS
       // Report

         smenu1=new JMenuItem("Coursewise Hostel Entry");
         smenu2=new JMenuItem("Course Hostel Report");
	 
         repoMenu.add(smenu1);
         repoMenu.add(smenu2);
     
       //Hostel View
              
    smenu3=new JMenu(" Single Bed");hostelMenu.add(smenu3);
    smenu4=new JMenu(" Double Bed");hostelMenu.add(smenu4);
    smenu5=new JMenu("Triple Bed");hostelMenu.add(smenu5);
    smenu6=new JMenu("Appartment");hostelMenu.add(smenu6);

        //Developer
    smenu7=new JMenuItem("Developer Details");devopMenu.add(smenu7);

    // Sub MenuItems -- Hostel Type
    
    menuItem1=new JMenuItem("  D   ");smenu3.add(menuItem1);//Single Bed/room
    menuItem2=new JMenuItem("  E   ");smenu3.add(menuItem2);
    menuItem3=new JMenuItem("  F   ");smenu3.add(menuItem3);
    menuItem5=new JMenuItem("  G   ");smenu3.add(menuItem5);
    menuItem6=new JMenuItem(" MLH  ");smenu3.add(menuItem6);
    menuItem7=new JMenuItem(" BMH  ");smenu3.add(menuItem7);

    menuItem8=new JMenuItem("   PG1  ");smenu4.add(menuItem8);//Double Bed/Room
    menuItem9=new JMenuItem("   PG2  ");smenu4.add(menuItem9);

    menuItem10=new JMenuItem("   A  ");smenu5.add(menuItem10);//Triple Bed/Room
    menuItem11=new JMenuItem("   B  ");smenu5.add(menuItem11);
    menuItem12=new JMenuItem("   C  ");smenu5.add(menuItem12);

    menuItem13=new JMenuItem("   FF  ");smenu6.add(menuItem13);//AppartMent type

         //MNEMONICS

     repoMenu.setMnemonic('R');
     hostelMenu.setMnemonic('H');
     devopMenu.setMnemonic('D');
     
     menubar.add(repoMenu);
     menubar.add(hostelMenu);
     menubar.add(devopMenu);

     //Add Action Listener on Menu

     smenu1.addActionListener(new reportListener());
     smenu2.addActionListener(new reportListener());

     menuItem1.addActionListener(new hostelListener());
     menuItem2.addActionListener(new hostelListener());
     menuItem3.addActionListener(new hostelListener());
     menuItem5.addActionListener(new hostelListener());
     menuItem6.addActionListener(new hostelListener());
     menuItem7.addActionListener(new hostelListener());
     menuItem8.addActionListener(new hostelListener());
     menuItem9.addActionListener(new hostelListener());
     menuItem10.addActionListener(new hostelListener());
     menuItem11.addActionListener(new hostelListener());
     menuItem12.addActionListener(new hostelListener());

     menuItem13.addActionListener(new hostelListener());

     smenu7.addActionListener(new developerListener());
  

//defined content on Top Panel
  
        l1=new JLabel("NITC HOSTEL MANAGEMENT SYSTEM");
       l1.setFont(new Font("Algerian",Font.BOLD, 40));
        l1.setForeground(new Color(210,105,30));

        p1=new JPanel();p1.add(l1);
        p1.setBounds(0,0,width,60);
        p1.setBackground(new Color(189,183,107));
        cn.add(p1,BorderLayout.CENTER);

//defined content on Bottom Panel
          l6=new JLabel("@nitc mca(S4)");
	  l6.setBounds(250,height,width,80);
  
          p4=new JPanel();cn.add(l6,BorderLayout.CENTER);
/*          p4.setLayout(null);
          p4.setBounds(0,height-80,width,80);
          p4.setBackground(new Color(90,60,30));
          cn.add(p4,BorderLayout.CENTER);*/

//defined content on Center Panel

                Icon homePic=new ImageIcon("photo/main.jpg");
        	l2=new JLabel(homePic);
        	l2.setBounds(0,0,918,height-140);

	          p5=new JPanel();
        	  p5.setLayout(null);
        	  p5.setBounds(200,60,918,height-140);
        	  p5.setBackground(new Color(20,20,20));
		  p5.add(l2,BorderLayout.CENTER);
        	  cn.add(p5,BorderLayout.CENTER);
      
   /*Content On Left Panel  */
 
	         Icon logo=new ImageIcon("photo/logo1.jpg");
        	 l3=new JLabel(logo);
        	 l3.setBounds(0,0,200,300);
  
	          p2=new JPanel();
	          p2.setLayout(null);
	          p2.setBounds(0,60,200,height);
	          p2.setBackground(new Color(90,60,30));
		  p2.add(l3,BorderLayout.NORTH);
	          cn.add(p2,BorderLayout.CENTER);

                 b1=new JButton("Students");
                 b1.setFont(new Font("Serif",Font.BOLD,18));
                 b1.setBounds(0,300,200,40);
		 b1.setBackground(new Color(90,60,30));
		 b1.setForeground(new Color(80,130,100)); 
                 b1.addActionListener(new leftListener());
               
                 b2=new JButton("Hostel Alotment");
                 b2.setFont(new Font("Serif",Font.BOLD,16));
                 b2.setBounds(0,340,200,40);
		 b2.setBackground(new Color(90,60,30));
		 b2.setForeground(new Color(80,130,100)); 
                 b2.addActionListener(new leftListener());     

                 b3=new JButton("Search Student");
                 b3.setFont(new Font("Serif",Font.BOLD,16));
                 b3.setBounds(0,380,200,40);
		 b3.setBackground(new Color(90,60,30));
		 b3.setForeground(new Color(80,130,100)); 
                 b3.addActionListener(new leftListener()); 
                 
                 b4=new JButton("Room Availabilty");
                 b4.setFont(new Font("Serif",Font.BOLD,16));
                 b4.setBounds(0,420,200,40);
		 b4.setBackground(new Color(90,60,30));
		 b4.setForeground(new Color(80,130,100)); 
                 b4.addActionListener(new leftListener()); 

		 p2.add(b1);p2.add(b2);p2.add(b3);p2.add(b4);


  /*Content On Right Panel  */

	          p3=new JPanel();
	          p3.setLayout(null);
	          p3.setBounds(1118,60,250,height);
	          p3.setBackground(new Color(90,60,30));
	          cn.add(p3,BorderLayout.CENTER);

        	l4=new JLabel("Add New Room");
                l4.setFont(new Font("Serif",Font.BOLD,20));
		l4.setForeground(new Color(80,130,100));
        	l4.setBounds(0,0,250,40);

                 b5=new JButton("A");
                 b5.setFont(new Font("Serif",Font.BOLD,20));
                 b5.setBounds(0,40,200,40);
		 b5.setBackground(new Color(90,60,30));
		 b5.setForeground(new Color(80,130,100)); 
                 b5.addActionListener(new rightListener());

                 b6=new JButton("B");
                 b6.setFont(new Font("Serif",Font.BOLD,20));
                 b6.setBounds(0,80,200,40);
		 b6.setBackground(new Color(90,60,30));
		 b6.setForeground(new Color(80,130,100)); 
                 b6.addActionListener(new rightListener()); 

                 b7=new JButton("C");
                 b7.setFont(new Font("Serif",Font.BOLD,20));
                 b7.setBounds(0,120,200,40);
		 b7.setBackground(new Color(90,60,30));
		 b7.setForeground(new Color(80,130,100)); 
                 b7.addActionListener(new rightListener()); 

                 b8=new JButton("PG1");
                 b8.setFont(new Font("Serif",Font.BOLD,20));
                 b8.setBounds(0,160,200,40);
		 b8.setBackground(new Color(90,60,30));
		 b8.setForeground(new Color(80,130,100)); 
                 b8.addActionListener(new rightListener()); 

                 b9=new JButton("PG2");
                 b9.setFont(new Font("Serif",Font.BOLD,20));
                 b9.setBounds(0,200,200,40);
		 b9.setBackground(new Color(90,60,30));
		 b9.setForeground(new Color(80,130,100)); 
                 b9.addActionListener(new rightListener()); 

        	l5=new JLabel("Single Bed/Room ");
		l5.setForeground(new Color(80,130,100));
        	l5.setBounds(0,260,200,40);
                l5.setFont(new Font("Serif",Font.BOLD,20));

                 b10=new JButton("D ,E , F");
                 b10.setFont(new Font("Serif",Font.BOLD,20));
                 b10.setBounds(0,300,200,40);
		 b10.setBackground(new Color(90,60,30));
		 b10.setForeground(new Color(80,130,100)); 
                 b10.addActionListener(new rightListener()); 

                 b11=new JButton("FF Apartment");
                 b11.setFont(new Font("Serif",Font.BOLD,20));
                 b11.setBounds(0,360,200,40);
		 b11.setBackground(new Color(90,60,30));
		 b11.setForeground(new Color(80,130,100)); 
                 b11.addActionListener(new rightListener()); 

			p3.add(l4);p3.add(l5);p3.add(b5);
			p3.add(b6);p3.add(b7);p3.add(b8);
			p3.add(b9);p3.add(b10);p3.add(b11);
    connect();
    setVisible(true);
    setResizable(true);
 }

	 private class leftListener implements ActionListener
	  {
	   public void actionPerformed(ActionEvent e)
	   {
		    if(e.getSource()== b1)
		       new student();

		   else if(e.getSource()== b2)
		       new registration();

		   else if(e.getSource()== b3)
		       new studentStatus();

		  else if(e.getSource()== b4)
		       new hostelStatus();
	   }
	  }

	 private class rightListener implements ActionListener
	  {
	   public void actionPerformed(ActionEvent e)
	   {
		    if(e.getSource()== b5)
		       new A();

		   else if(e.getSource()== b6)
		       new B();

		   else if(e.getSource()== b7)
		       new C();

		  else if(e.getSource()== b8)
		       new PG1();

		  else if(e.getSource()== b9)
		       new PG2();

		  else if(e.getSource()== b10)
		       new singleBedHostel();

		  else if(e.getSource()== b11)
		       new FF();
	   }
	  }


  private class reportListener implements ActionListener
  {
   public void actionPerformed(ActionEvent e)
   {
    if(e.getSource()==smenu1)
       new courseTableEntry();

    else if(e.getSource()==smenu2)
      new courseTableReport();
   }
  }

	  private class hostelListener implements ActionListener
	  {
	   public void actionPerformed(ActionEvent e)
	   {
	    if(e.getSource() == menuItem1)
	      new reportD();
	
	    else if(e.getSource()==menuItem2)
	     new reportE();
	
	    else if(e.getSource()==menuItem3)
	     new reportF();

	    else if(e.getSource()==menuItem5)
	     new reportG();

	    else if(e.getSource()==menuItem6)
	     new reportMLH();

	    else if(e.getSource()==menuItem7)
	     new reportBMH();

	    else if(e.getSource()==menuItem8)
	     new reportPG1();

	    else if(e.getSource()==menuItem9)
	     new reportPG2();

	    else if(e.getSource()==menuItem10)
	     new reportA();

	    else if(e.getSource()==menuItem11)
	     new reportB();

	    else if(e.getSource()==menuItem12)
	     new reportC();

	    else if(e.getSource()==menuItem13)
	     new reportFF();

	   }
	  }

	  private class developerListener implements ActionListener
	  {
	   public void actionPerformed(ActionEvent e)
	   {
		    if(e.getSource()== smenu7);
	//	new devloper();
	   }
	  }

   protected void processWindowEvent(WindowEvent we)
   { int r;
     if(we.getID() == we.WINDOW_CLOSING)
     {
       r=JOptionPane.showConfirmDialog(null,"Do You Want To Exit Now !","Enter Carefully",JOptionPane.WARNING_MESSAGE);
       if(r==JOptionPane.YES_OPTION)
         dispose();
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


public static void main(String args[])
{
  index id=new index();
}
}


