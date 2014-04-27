package fr.utbm.lp24.minesweeper;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AboutWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	

	JButton bouton;

	public AboutWindow(){

		this.setTitle("About UTBM Minesweeper");
		this.setSize(300, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		URL iconURL = getClass().getResource("/resources/icon64.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		 
		 
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			bouton = new JButton("OK");
			bouton.addActionListener(this);
			panel.add(bouton);
			
			this.add(panel);

		
		this.setVisible(true);
			
	
	}

	
 
	public void actionPerformed(ActionEvent event) {
		if( event.getSource() == bouton ){
			this.setVisible(false);
	    }
	}
	

}
