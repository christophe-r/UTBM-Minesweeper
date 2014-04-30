package fr.utbm.lp24.minesweeper;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;



/**
 * Class to manage the Minesweeper (main) window.
 * @author Christophe and Vincent
 */
public class MinesweeperWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	// menu items
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuGame = new JMenu("Game");
	
	private JMenuItem menuGameItem1 = new JMenuItem("New Game");
	private JMenuItem menuGameItem2 = new JMenuItem("Statistics");
	private JMenuItem menuGameItem3 = new JMenuItem("Options");
	private JMenuItem menuGameItem4 = new JMenuItem("Change appearance");
	private JMenuItem menuGameItem5 = new JMenuItem("Exit");
	
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem menuHelpItem1 = new JMenuItem("About UTBM Minesweeper");
	
	// container
	private JPanel container = new JPanel(); // main container
	private JPanel begin = new JPanel();
	
	private String msg = "Click to begin the game ...";
	private JLabel label = new JLabel(msg);
	private BoardDraw boardDraw = new BoardDraw();
	private int square_size = 20;

	/**
	* Initialize the main windows and display the first view
	* @author Vincent
	*/	
	public MinesweeperWindow(final MinesweeperGame controller){ 

		getContentPane().setBackground(UIManager.getColor("inactiveCaption"));
		boardDraw.loadImages();
		this.setTitle("Minesweeper");
		this.setSize(300, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		URL iconURL = getClass().getResource("/resources/icon64.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
		// add listener for menu
	    
		this.menuGame.add(menuGameItem1);
		menuGameItem1.addActionListener(this);
		menuGameItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		
		this.menuGame.addSeparator();
		
		this.menuGame.add(menuGameItem2);
		menuGameItem2.addActionListener(this);
		menuGameItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		
		this.menuGame.add(menuGameItem3);
		menuGameItem3.addActionListener(this);
		menuGameItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		
		this.menuGame.add(menuGameItem4);
		menuGameItem4.addActionListener(this);
		menuGameItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		
		this.menuGame.addSeparator();
		
		this.menuGame.add(menuGameItem5);
		menuGameItem5.addActionListener(this);

		this.menuHelp.add(menuHelpItem1);
		menuHelpItem1.addActionListener(this);

		this.menuBar.add(menuGame);
		this.menuBar.add(menuHelp);

		this.setJMenuBar(menuBar);
		
		//set the input and button object
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    begin.add(label);

	    // update view
	    container.add(begin, BorderLayout.SOUTH);
	    this.setContentPane(container);
	    this.setVisible(true);  
	    
	    
    
    /**
     * Create a listener to call rightClickOnTile and leftClickOnTile methods
     * 
     * @author Vincent
     */
	    this.addMouseListener(new MouseAdapter(){
	        public void mousePressed(MouseEvent e){
	        	Point containerlocate =  container.getLocation();
		     	switch( e.getButton() ){
		     		case 1: controller.leftClickOnBoard((e.getX() - containerlocate.x - 3 ),(e.getY() - containerlocate.y - 25)); break;
		     		case 3: controller.rightClickOnBoard((e.getX() - containerlocate.x - 3 ),(e.getY() - containerlocate.y - 25)); break;
		     		default: break;
		        }
	        }
	     });
	
	}
	
	
    /**
     * This method recreate the board on the screen
     * @author Vincent
     */
	public void drawBoard(Tile stateboard[][]){
		System.out.println("largueur : " + stateboard.length*square_size + "   hauteur  : " +  stateboard[0].length*square_size);
		this.setSize(stateboard[0].length*square_size + 100 , stateboard.length*square_size + 150);
		boardDraw.setNewBoard(stateboard, square_size);
		boardDraw.repaint();
		container.add(boardDraw, BorderLayout.CENTER);
		this.setContentPane(container);
		this.setVisible(true);  
	}
	
	/**
	 * update the bottom message
	 * 
	 * @param s the new message for the label
	 */
	public void updateMsgPanel(String s){
		begin.remove(label);
		JLabel label = new JLabel(s);
	    begin.add(label);
	    container.add(begin, BorderLayout.SOUTH);
		this.setContentPane(container);
		this.setVisible(true); 
	}
	
	/**
	 * Event handlers for the JMenu
	 */
	public void actionPerformed(ActionEvent event) {

		if( event.getSource() == menuGameItem1 ){
			System.out.println("New game event");
			
			
		} else if( event.getSource() == menuGameItem2 ){
			System.out.println("Statistics event");
		} else if( event.getSource() == menuGameItem3 ){
			new OptionsWindow();
		} else if( event.getSource() == menuGameItem4 ){
			System.out.println("Change appearance event");
		} else if( event.getSource() == menuGameItem5 ){
			System.exit(0);
		} else if( event.getSource() == menuHelpItem1 ){
			new AboutWindow();
		}
		
	}
	
	/**
	 * ~~~~~~~~~~~ 
	 * get the square_size
	 * ~~~~~~~~~~~ 
	 */
	public int getsquaresize(){
		return this.square_size;
	}

}



