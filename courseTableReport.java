import javax.swing.JTable.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class courseTableReport extends JFrame 
{
  String[] col={"Hostel Name","Course","Year","Hostel For"};
  Object data[][]=new Object[30][4];
  JTable table;


  private JPanel p1;
  private JButton b1,b2;
  private JLabel tl;
  private Container cn;

  ResultSet rs;
  Dimension screensize;
  Connection connection;
  Statement statement;
	int r;

 public courseTableReport()
 {
        setTitle("Report");

       JFrame fr=new JFrame();      
       Toolkit tkt=fr.getToolkit();                  
       Dimension frsize=tkt.getScreenSize();
       setBounds(frsize.width/4,frsize.height/12,frsize.width/2,frsize.height/8);
       setLayout(null);

        cn=getContentPane();
        cn.setBackground(new Color(230,230,200));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tl=new JLabel("Coursewise Hostels Report");
        tl.setFont(new Font("Engravers MT", 1, 25));
        tl.setForeground(new Color(247,251,249));

        p1=new JPanel();        
        p1.setBounds(0,0,600,50);
	p1.add(tl);
        p1.setBackground(new Color(31,88,166));
        cn.add(p1);


        b1=new JButton("LOAD");
        b1.setMnemonic('L');
        b1.addActionListener(new dispListener());
        b1.setBounds(230,320,120,30);

        b2=new JButton("EXIT");
        b2.setMnemonic('X');
        b2.addActionListener(new exitListener());
        b2.setBounds(350,320,100,30);


    cn.add(b1);cn.add(b2);

    table=new JTable(data,col);
    table.setFont(new Font("Serif",Font.BOLD,16));
    table.setBackground(new Color(180,180,200));
    table.setEnabled(false);

    JScrollPane jsp=new JScrollPane(table);
    jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    jsp.setBounds(5,100,590,200);
    cn.add(jsp);

    screensize=Toolkit.getDefaultToolkit().getScreenSize();

	
      setSize(600,400);
      setVisible(true);
      setVisible(true);
      setResizable(true);
      connect();

  }



   protected void processWindowEvent(WindowEvent we)
   {
     if(we.getID()==we.WINDOW_CLOSING)
     {
       r=JOptionPane.showConfirmDialog(null,"WARNNING","EXIT",JOptionPane.WARNING_MESSAGE);
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


  
public class dispListener implements ActionListener
{
  public void actionPerformed(ActionEvent e)
    {
	int k=0;
        try
       {
         rs=statement.executeQuery("select * from courseTableEntry");
          while(rs.next())
          {
               data[k][0]=rs.getString(1);
               data[k][1]=rs.getString(2);
               data[k][2]=rs.getString(3);
		data[k][3]=rs.getString(4);
	   k++;
          }
           
            data[k][0]=" ";
            data[k][1]=" ";
            data[k][2]=" ";
	    data[k][3]=" ";

           table=new JTable(data,col);
           table.setFont(new Font("Serif",Font.BOLD,16));
           table.setBackground(new Color(180,180,200));

          JScrollPane jsp=new JScrollPane(table);
          jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    jsp.setBounds(5,100,590,200);
          cn.add(jsp);
          
       }
       catch(Exception sq)
            {
              JOptionPane.showMessageDialog(null,"Load Error :"+sq);
            }
        }    
 }

 public class exitListener implements ActionListener
 {
     public void actionPerformed(ActionEvent e)
     {
       int n= JOptionPane.showConfirmDialog(null,"Would U Really Want To Exit","Enter Carefully",JOptionPane.WARNING_MESSAGE);
       if(n==JOptionPane.YES_OPTION)
       dispose();
     }

 }

  
 public static void main(String args[])
 {
      courseTableReport hst=new courseTableReport();
 }
  
}
 

