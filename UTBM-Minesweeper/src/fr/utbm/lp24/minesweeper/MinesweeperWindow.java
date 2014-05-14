package fr.utbm.lp24.minesweeper;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
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

	// Menu items
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuGame = new JMenu("Game");

	private JMenuItem menuGameItem1 = new JMenuItem("New Game");
	private JMenuItem menuGameItem2 = new JMenuItem("Statistics");
	private JMenuItem menuGameItem3 = new JMenuItem("Options");
	private JMenuItem menuGameItem4 = new JMenuItem("Change appearance");
	private JMenuItem menuGameItem5 = new JMenuItem("Exit");
	private JCheckBoxMenuItem menuGameItem6 = new JCheckBoxMenuItem("Enable shadows");

	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem menuHelpItem1 = new JMenuItem("About UTBM Minesweeper");

	// Main container
	private JPanel container = new JPanel();

	private BoardDraw boardDraw = new BoardDraw();
	private BottomBar bottomBar = new BottomBar();
	private int square_size = 20;


	private MinesweeperGame controller;

	private ArrayList<Integer> cheatCodePressedKeys;

	/**
	 * Initialize the main window and display the first view
	 * @author Vincent
	 */	
	public MinesweeperWindow(final MinesweeperGame controller){ 

		this.controller = controller;
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

		this.cheatCodePressedKeys = new ArrayList<Integer>();

		// Add items and listeners for the JMenu
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

		
		this.menuGame.add(menuGameItem6);
		menuGameItem6.addActionListener(this);
		
		this.menuGame.addSeparator();

		this.menuGame.add(menuGameItem5);
		menuGameItem5.addActionListener(this);

		this.menuHelp.add(menuHelpItem1);
		menuHelpItem1.addActionListener(this);

		this.menuBar.add(menuGame);
		this.menuBar.add(menuHelp);

		this.setJMenuBar(menuBar);


		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());

		// Update view
		this.setContentPane(container);
		this.setVisible(true);  


		/**
		 * Create a listener to draw the help shadow
		 *  @author Vincent
		 */
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				controller.helpShadow((e.getX())-5, (e.getY()-45));
				controller.cheatPixel((e.getX())-5, (e.getY()-45));
			}
		});


		/**
		 * Key listener for cheat code
		 * @author Christophe
		 */
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {

				if( cheatCodePressedKeys.size() >= 6 ){ // Do not exceed 6 chars in the list (delete the first one instead)
					cheatCodePressedKeys.remove(0);
				}

				cheatCodePressedKeys.add(e.getKeyCode());

				ArrayList<Integer> pixelCode = new ArrayList<Integer>(6);
				pixelCode.add(88); // X
				pixelCode.add(89); // Y
				pixelCode.add(90); // Z
				pixelCode.add(90); // Z
				pixelCode.add(89); // Y
				pixelCode.add(10); // Enter

				if( cheatCodePressedKeys.equals(pixelCode) ){ // If the last pressed keys match with the cheat code
					controller.enableCheatPixel();
				}

			}
			@Override
			public void keyReleased(KeyEvent e){}
			@Override
			public void keyTyped(KeyEvent e){}
		});


		/**
		 * Create a listener to call rightClickOnTile and leftClickOnTile methods
		 * @author Vincent
		 */
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Point containerlocate =  container.getLocation();
				switch( e.getButton() ){
				case 1: controller.leftClickOnBoard((e.getX() - containerlocate.x - 3 ),(e.getY() - containerlocate.y - 25)); break;
				case 3: controller.rightClickOnBoard((e.getX() - containerlocate.x - 3 ),(e.getY() - containerlocate.y - 25)); break;
				case 2: controller.centralClickOnBoard((e.getX() - containerlocate.x - 3 ),(e.getY() - containerlocate.y - 25)); break;
				default: break;
				}
			}
		});

	}


	/**
	 * This method redraw the board on the screen
	 * @author Vincent
	 */
	public void drawBoard(Tile stateboard[][], boolean endgame){
		this.setSize(stateboard[0].length*square_size + 100 , stateboard.length*square_size + 150);
		boardDraw.setNewBoard(stateboard, square_size, endgame);
		boardDraw.repaint();
		this.updateBottom(stateboard[0].length*square_size + 100 , "size");
		container.add(boardDraw, BorderLayout.CENTER);
		this.setContentPane(container);
		this.setVisible(true);  
	}


	/**
	 * Updates the bottom bar
	 * @param s the new message for the label
	 * @author Vincent
	 */
	public void updateBottom(int value, String type){
		bottomBar.updateParameter(value, type);
		bottomBar.removeAll();
		JLabel label = new JLabel(" ");
		bottomBar.add(label);
		bottomBar.repaint();
		container.add(bottomBar, BorderLayout.SOUTH);
		this.setContentPane(container);
		this.setVisible(true); 
	}

	/**
	 * Event handlers for the JMenu
	 */
	public void actionPerformed(ActionEvent event) {

		if( event.getSource() == menuGameItem1 ){
			System.out.println("New game event");
			controller.newGame();
		} else if( event.getSource() == menuGameItem2 ){
			System.out.println("Statistics event");
		} else if( event.getSource() == menuGameItem3 ){
			new OptionsWindow(controller);
		} else if( event.getSource() == menuGameItem4 ){
			new AppearanceWindow();
			boardDraw.repaint(); // display the new board
		} else if( event.getSource() == menuGameItem5 ){
			System.exit(0);
		} else if( event.getSource() == menuHelpItem1 ){
			new AboutWindow();
		} else if( event.getSource() == menuGameItem6 ){
			PreferencesManager userPreferences = new PreferencesManager();
			userPreferences.setPref("heplshadow", ""+menuGameItem6.isSelected());
		}

	}


	/**
	 * Get the square size
	 * @return square size
	 */
	public int getSquareSize(){
		return this.square_size;
	}


	/**
	 * Draw the shadow on the board
	 * @param x 
	 * @param y 
	 * @param shadow
	 */
	public void drawShadow(int x, int y, boolean shadow){
		boardDraw.repaint();
		boardDraw.shadow(x, y, shadow);
	}


	/**
	 * Draw the cheat pixel
	 * @param pixelState true=there is a mine, false=no mine
	 * @author Christophe
	 */
	public void drawCheatPixel(boolean pixelState){
		boardDraw.repaint();
		boardDraw.cheatPixel(pixelState);
	}


}



