package fr.utbm.lp24.minesweeper;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
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
	private JCheckBoxMenuItem menuGameItem6 = new JCheckBoxMenuItem("Enable help shadow");

	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem menuHelpItem1 = new JMenuItem("About UTBM Minesweeper");

	// Main container
	private JPanel container = new JPanel();
	private Background background = new Background();
	private BoardDraw boardDraw = new BoardDraw();
	private BottomBar bottomBar = new BottomBar();
	
	private int square_size = 20;
	private MinesweeperGame controller;
	private ArrayList<Integer> cheatCodePressedKeys;

	/**
	 * Initialize the main window and display the first view
	 */	
	public MinesweeperWindow(final MinesweeperGame controller){ 

		this.controller = controller;
		getContentPane().setBackground(UIManager.getColor("inactiveCaption"));
		boardDraw.loadImages(); 

		this.setTitle("Minesweeper");
		this.setSize(300, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(exitListener);
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

		PreferencesManager userPreferences = new PreferencesManager();
		if( userPreferences.getPref("help_shadow", "false").equals("true") ){
			menuGameItem6.setSelected(true);
			menuGameItem6.setText("Disable help shadow");
		} else {
			menuGameItem6.setSelected(false);
		}

		boardDraw.setOpaque(false);
		bottomBar.setOpaque(false);
		background.setOpaque(false);

		container.setLayout(null);

		// Update view
		this.setContentPane(container);
		this.setVisible(true);  


		/**
		 * Create a listener to draw the help shadow
		 */
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				controller.helpShadow((e.getX()-3), (e.getY()-46));
				controller.cheatPixel((e.getX()-3), (e.getY()-46));
			}
		});


		/**
		 * Key listener for cheat code
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
		 */
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Point containerlocate =  container.getLocation();
				switch( e.getButton() ){
				case 1: controller.leftClickOnBoard((e.getX() - containerlocate.x - 3 ), (e.getY() - containerlocate.y - 25)); break;
				case 3: controller.rightClickOnBoard((e.getX() - containerlocate.x - 3 ), (e.getY() - containerlocate.y - 25)); break;
				case 2: controller.centralClickOnBoard((e.getX() - containerlocate.x - 3 ), (e.getY() - containerlocate.y - 25)); break;
				default: break;
				}
			}
		});

	}


	/**
	 * This method redraw the board on the screen
	 */
	public void drawBoard(Tile stateBoard[][], boolean endGame){

		this.setSize(stateBoard[0].length*square_size + 50 , stateBoard.length*square_size + 125);
		boardDraw.setNewBoard(stateBoard, square_size, endGame);
		boardDraw.setBounds(0, 0, stateBoard[0].length*square_size + 50, stateBoard.length*square_size + 125);
		boardDraw.repaint();
		container.remove(boardDraw);
		container.add(boardDraw);	
		this.updateBottom(stateBoard[0].length*square_size + 40 , "size");
		this.setContentPane(container);
	}


	/**
	 * Updates the bottom bar
	 * @param s the new message for the label
	 */
	public void updateBottom(int value, String type){
		Dimension size = this.getSize();
		bottomBar.updateParameter(value, type);
		bottomBar.setBounds(0, size.height-85, size.width, size.height);
		bottomBar.repaint();
		container.remove(bottomBar);
		container.add(bottomBar);

		background.setBounds(0, 0, size.width, size.height);
		background.repaint();
		container.add(background);

		this.setContentPane(container);
	}

	/**
	 * Event handlers for the JMenu
	 */
	public void actionPerformed(ActionEvent event) {

		if( event.getSource() == menuGameItem1 ){
			controller.newGame();
		} else if( event.getSource() == menuGameItem2 ){
			new StatisticsWindow();
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
			userPreferences.setPref("help_shadow", ""+menuGameItem6.isSelected());
			if(menuGameItem6.isSelected()){
				menuGameItem6.setText("Disable help shadow");
			}else{
				menuGameItem6.setText("Enable help shadow");
			}
		}

	}

	/**
	 * Event handlers called when you quit the game
	 */
	WindowListener exitListener = new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			controller.exit();
			System.exit(0);
		}
	};



	/**
	 * Get the square size
	 * @return square size
	 */
	public int getSquareSize(){
		return this.square_size;
	}


	/**
	 * Draw the shadow on the board
	 * @param x  X coordinate in px
	 * @param y  Y coordinate in px
	 * @param shadow
	 */
	public void drawShadow(int x, int y, boolean shadow){
		boardDraw.repaint();
		boardDraw.shadow(x, y, shadow);
	}


	/**
	 * Draw the cheat pixel
	 * @param pixelState true=there is a mine, false=no mine
	 */
	public void drawCheatPixel(boolean pixelState){
		boardDraw.repaint();
		boardDraw.cheatPixel(pixelState);
	}

}
