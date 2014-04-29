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
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
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
	private JPanel container = new JPanel(); // main container container
	private JPanel begin = new JPanel();
	/*private JPanel cards = new JPanel();  // center no-used for the time being
	String[] listContent = {"CARD_1", "CARD_2"};*/
	
	private JFormattedTextField jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JLabel label = new JLabel("Click to generate a game");
	private BoardDraw boarddraw = new BoardDraw();
	
	// link controller and view
	private MinesweeperGame controleur;

	
	
	public MinesweeperWindow(final MinesweeperGame controleur){ 
		/**
		 * Initialise the main windows and display the first view
		 * @author vincent
		 */
		getContentPane().setBackground(UIManager.getColor("inactiveCaption"));
		this.controleur	= controleur;
		
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
	//    final CardLayout cl = new CardLayout(); //no-used for the time being

	    
	      
	    
	    
		// add listener for menu
	    menuGameItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		this.menuGame.add(menuGameItem1);
		menuGameItem1.addActionListener(this);
		menuGameItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		this.menuGame.addSeparator();
		this.menuGame.add(menuGameItem2);
		menuGameItem2.addActionListener(this);
		menuGameItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		this.menuGame.add(menuGameItem3);
		menuGameItem3.addActionListener(this);
		menuGameItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
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
		
		//set the input and button object
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
		JLabel label = new JLabel("Click to generate a game");
		//JPanel begin = new JPanel();
	            
	    begin.add(label);
	
	 	
	/*  // no-used for the time being
	    
	    JButton nextcard = new JButton("next"); 
	    bouton2.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	    	  cl.next(cards);
	      }
	    });
	    top.add(nextcard);
		
		cards.setLayout(cl);
	    //we add the card to the stack with a name to find it later
	    cards.add(new BoardDraw(), listContent[0]);
	    cards.add(card2, listContent[1]);*/
	    // container.add(boarddraw, BorderLayout.CENTER);
	    //container.add(cards, BorderLayout.CENTER);
	    
	    
	    // update view
	    container.add(begin, BorderLayout.CENTER);
	    this.setContentPane(container);
	    this.setVisible(true);  
	    
	    
    
    /**
     * Create a listener to call rightClickOnTile and leftClickOnTile methods
     * @author Vincent
     */
	    this.addMouseListener(new MouseAdapter(){
	        public void mousePressed(MouseEvent e){
	        	Point containerlocate =  container.getLocation();
		     	switch( e.getButton() ){
				case 1: controleur.leftClickOnBoard((e.getX() - containerlocate.x - 3 ),(e.getY() - containerlocate.y - 25)); break;
				case 3: controleur.rightClickOnBoard(e.getX(),e.getY()); break;
				
				default: break;
		        }
	        }
	     });
	
	}
	/**
	 * Load the view with the board
	 * @author vincent
	 */
	public void boardView(){
		boarddraw.repaint();
		container.remove(begin);
		    container.add(boarddraw, BorderLayout.CENTER);
		    this.setContentPane(container);
		    this.setVisible(true);  
	}
	
	
	
    /**
     * This method recreate the board on the screen
     * @author Vincent
     */
	public void drawBoard(TileContent stateboard[][]){
		boarddraw.setNewBoard(stateboard);
		boarddraw.repaint();
		
	}

	public void actionPerformed(ActionEvent event) {

		if( event.getSource() == menuGameItem1 ){
			System.out.println("New game event");
			
		} else if( event.getSource() == menuGameItem2 ){
			System.out.println("Statistics event");
		} else if( event.getSource() == menuGameItem3 ){
		} else if( event.getSource() == menuGameItem4 ){
			System.out.println("Change appearance event");
		} else if( event.getSource() == menuGameItem5 ){
			System.exit(0);
		} else if( event.getSource() == menuHelpItem1 ){
			new AboutWindow();
		}
		
	}
	
	  class BoutonListener implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		      System.out.println("TEXT : " + jtf.getText());
		    }
		  }
	  

}



