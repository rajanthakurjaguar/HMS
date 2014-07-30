import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;


  public class reportB extends JFrame
   {
	private JLabel wname1,wname,wcontact1,wcontact,totalBed,totalBed1,vacentBed,vacentBed1,lhead;
	private JButton b1;
	String totalSeat,vacentSeat;

    	Connection connection;	
    	Statement statement;
    	private Icon icon;
    	ResultSet rs;   

     	public reportB() 
      	{
		connect();
		countRoom();
        getContentPane().setLayout(null);
	getContentPane().setBackground(new Color(220,220,210));

	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

	Icon icon=new ImageIcon("photo/b.jpg");
        JLabel lpic=new JLabel(icon);
        lpic.setBounds(0,-80,500,400);
	getContentPane().add(lpic);
	
               lhead = new JLabel("Hostel B");
               lhead.setForeground(Color.red);
               lhead.setFont(new Font("Algerian",Font.BOLD,40));
               lhead.setBounds(180,280,200,80);
		getContentPane().add(lhead);  

                wname = new JLabel("Warden Name    :");
                wname.setFont(new Font("Serif",Font.BOLD,20));
                wname.setBounds(75,380,200,30);
		getContentPane().add(wname);

                wname1 = new JLabel("Dr.A S SAJITH");	
                wname1.setFont(new Font("Serif",Font.BOLD,20));
                wname1.setBounds(275,380,200,30);
		getContentPane().add(wname1);
		 

                wcontact1 = new JLabel("Warden Contact :");
                wcontact1.setFont(new Font("Serif",Font.BOLD,20));
                wcontact1.setBounds(75,410,200,30);
		getContentPane().add(wcontact1);

                wcontact = new JLabel("9947311812");
                wcontact.setFont(new Font("Serif",Font.BOLD,20));
                wcontact.setBounds(275,410,150,30);
		getContentPane().add(wcontact);

                totalBed = new JLabel("Total Beds          :");
                totalBed.setFont(new Font("Serif",Font.BOLD,20));
                totalBed.setBounds(75,440,200,30);
		getContentPane().add(totalBed);
		
                totalBed1=new JLabel(totalSeat);
                totalBed1.setFont(new Font("Serif",Font.BOLD,20));
                totalBed1.setBounds(330,440,150,30);
		getContentPane().add(totalBed1);

		vacentBed = new JLabel("Vacant Beds       :");
                vacentBed.setFont(new Font("Serif",Font.BOLD,20));
                vacentBed.setBounds(75,470,200,30);
		getContentPane().add(vacentBed);

                vacentBed1=new JLabel(vacentSeat);
                vacentBed1.setFont(new Font("Serif",Font.BOLD,20));
                vacentBed1.setBounds(330,470,150,30);
		getContentPane().add(vacentBed1);

	        b1=new JButton("close");
	        getContentPane().add(b1);
	        b1.setBounds(220,500,80,25);
		b1.addActionListener(new closeListener());

	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        setBounds(400,40,500,550);
		setVisible(true);
    
	}

	public void countRoom()
	{
        try
        {
        rs=statement.executeQuery("select totalSeat,vacentSeat from hostelStatus where hostelName='B'");
        rs.next();
	totalSeat=rs.getString(1);
	vacentSeat=rs.getString(2);
	}  
        catch(SQLException sq)
                   {
                     JOptionPane.showMessageDialog(null,"No Data Found");
                   }
	
	}

   	private class closeListener implements ActionListener
	    {
		public void actionPerformed(ActionEvent e)
	  	{
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
	reportB rb=new reportB();
	}

   }
