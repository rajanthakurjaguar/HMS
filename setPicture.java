import java.awt.*;
import javax.swing.*;

  public class setPicture extends JFrame
   {
     	public setPicture(String strRoll) 
      	{
        getContentPane().setLayout(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
    
		Icon icon=new ImageIcon("photo/"+strRoll.trim()+".jpg"); //pic on form
                JLabel lpic=new JLabel(icon);
                lpic.setBounds(0,0,270,320);
		getContentPane().add(lpic);
		
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(900,100,270,320);
		setVisible(true);
	}

    
   }
