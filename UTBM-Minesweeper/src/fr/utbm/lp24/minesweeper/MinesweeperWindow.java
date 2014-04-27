package fr.utbm.lp24.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MinesweeperWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuGame = new JMenu("Game");
	
	private JMenuItem menuGameItem1 = new JMenuItem("New Game");
	private JMenuItem menuGameItem2 = new JMenuItem("Statistics");
	private JMenuItem menuGameItem3 = new JMenuItem("Options");
	private JMenuItem menuGameItem4 = new JMenuItem("Change appearance");
	private JMenuItem menuGameItem5 = new JMenuItem("Exit");
	

	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem menuHelpItem1 = new JMenuItem("About UTBM Minesweeper");
	

	public MinesweeperWindow(){

		this.setTitle("Minesweeper");
		this.setSize(300, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		URL iconURL = getClass().getResource("/resources/icon64.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		 
		 
		 this.menuGame.add(menuGameItem1);
		 menuGameItem1.addActionListener(this);
		 
		 this.menuGame.addSeparator();
		 
		 this.menuGame.add(menuGameItem2);
		 menuGameItem2.addActionListener(this);
		 
		 this.menuGame.add(menuGameItem3);
		 menuGameItem3.addActionListener(this);
		 
		 this.menuGame.add(menuGameItem4);
		 menuGameItem4.addActionListener(this);
		 
		 this.menuGame.addSeparator();
		 
		 this.menuGame.add(menuGameItem5);
		 menuGameItem5.addActionListener(this);
		 

		 
		 this.menuHelp.add(menuHelpItem1);
		 menuHelpItem1.addActionListener(this);
		 
			
		this.menuBar.add(menuGame);
	    this.menuBar.add(menuHelp);
	    
	    
		this.setJMenuBar(menuBar);
		this.setVisible(true);
			
	
	}


	public void actionPerformed(ActionEvent event) {
		
		if( event.getSource() == menuGameItem1 ){
			System.out.println("New game event");
		} else if( event.getSource() == menuGameItem2 ){
			System.out.println("Statistics event");
		} else if( event.getSource() == menuGameItem3 ){
			System.out.println("Options event");
		} else if( event.getSource() == menuGameItem4 ){
			System.out.println("Change appearance event");
		} else if( event.getSource() == menuGameItem5 ){
			System.exit(0);
		} else if( event.getSource() == menuHelpItem1 ){
			new AboutWindow();
		}
		
	}

}
