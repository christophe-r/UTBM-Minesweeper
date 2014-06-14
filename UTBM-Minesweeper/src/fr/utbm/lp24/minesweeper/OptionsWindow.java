package fr.utbm.lp24.minesweeper;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class OptionsWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;
	private JButton okButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JLabel lbl_custom_mines;

	private JTextField textField_custom_height;
	private JTextField textField_custom_width;
	private JTextField textField_custom_mines;

	private JRadioButton rdbtn_beginner;
	private JRadioButton rdbtn_intermediate;
	private JRadioButton rdbtn_advanced;
	private JRadioButton rdbtn_custom;

	private boolean optionChanged = false;
	private PreferencesManager userPreferences;


	/**
	 * Create the dialog.
	 */
	public OptionsWindow(final MinesweeperGame controller) {
		setTitle("Options");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 390, 280);
		contentPanel.setBounds(0, 0, 384, 251);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					optionWindowOK(controller);
				}
			});
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		getContentPane().setLayout(null);
		getContentPane().add(contentPanel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Difficulty", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
										.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
										.addContainerGap())
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(okButton)
								.addComponent(cancelButton))
								.addGap(105))
				);
		panel.setLayout(null);


		rdbtn_beginner = new JRadioButton("<html>Beginner<br>10 mines<br>9 x 9 tile grid");
		rdbtn_beginner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonCustom();
				optionChanged = true;
			}
		});

		buttonGroup.add(rdbtn_beginner);		
		rdbtn_beginner.setBounds(12, 28, 109, 43);
		panel.add(rdbtn_beginner);

		rdbtn_intermediate = new JRadioButton("<html>Intermediate<br>40 mines<br>16 x 16 tile grid");
		rdbtn_intermediate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonCustom();
				optionChanged = true;
			}
		});
		buttonGroup.add(rdbtn_intermediate);
		rdbtn_intermediate.setBounds(12, 80, 109, 43);
		panel.add(rdbtn_intermediate);

		rdbtn_advanced = new JRadioButton("<html>Advanced<br>99 mines<br>16 x 30 tile grid");
		rdbtn_advanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonCustom();
				optionChanged = true;
			}
		});
		buttonGroup.add(rdbtn_advanced);
		rdbtn_advanced.setBounds(12, 132, 109, 43);
		panel.add(rdbtn_advanced);

		rdbtn_custom = new JRadioButton("Custom");
		rdbtn_custom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonCustom();
				optionChanged = true;
			}
		});

		buttonGroup.add(rdbtn_custom);
		rdbtn_custom.setBounds(170, 28, 71, 23);
		panel.add(rdbtn_custom);


		JLabel lbl_custom_height = new JLabel("Height (9-24):");
		lbl_custom_height.setBounds(196, 50, 71, 14);
		panel.add(lbl_custom_height);

		textField_custom_height = new JTextField();
		textField_custom_height.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textFieldCustomIntInRange(textField_custom_height, 9, 24);
				textFieldCustomIntInRangeMines();
				updateLabelMaxMines();
				optionChanged = true;
			}
		});
		textField_custom_height.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				textFieldMaxLength(textField_custom_height, 2);
			}
		});
		lbl_custom_height.setLabelFor(textField_custom_height);
		textField_custom_height.setBounds(286, 47, 53, 20);
		panel.add(textField_custom_height);
		textField_custom_height.setColumns(10);

		JLabel lbl_custom_width = new JLabel("Width (9-30):");
		lbl_custom_width.setBounds(196, 80, 71, 14);
		panel.add(lbl_custom_width);

		textField_custom_width = new JTextField();
		textField_custom_width.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textFieldCustomIntInRange(textField_custom_width, 9, 30);
				textFieldCustomIntInRangeMines();
				updateLabelMaxMines();
				optionChanged = true;
			}
		});
		textField_custom_width.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				textFieldMaxLength(textField_custom_width, 2);
			}
		});
		lbl_custom_width.setLabelFor(textField_custom_width);
		textField_custom_width.setBounds(286, 77, 53, 20);
		panel.add(textField_custom_width);
		textField_custom_width.setColumns(10);

		lbl_custom_mines = new JLabel("Mines (10-710):");
		lbl_custom_mines.setBounds(196, 110, 83, 14);
		panel.add(lbl_custom_mines);

		textField_custom_mines = new JTextField();
		textField_custom_mines.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textFieldCustomIntInRange(textField_custom_mines, 10, 710);
				textFieldCustomIntInRangeMines();
				optionChanged = true;
			}
		});
		textField_custom_mines.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				textFieldMaxLength(textField_custom_mines, 3);
			}
		});
		lbl_custom_mines.setLabelFor(textField_custom_mines);
		textField_custom_mines.setBounds(286, 108, 53, 20);
		panel.add(textField_custom_mines);
		textField_custom_mines.setColumns(10);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{rdbtn_beginner, rdbtn_intermediate, rdbtn_advanced, rdbtn_custom, lbl_custom_height, textField_custom_height, lbl_custom_width, textField_custom_width, lbl_custom_mines, textField_custom_mines}));
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPanel, panel, rdbtn_beginner, rdbtn_intermediate, rdbtn_advanced, rdbtn_custom, lbl_custom_height, textField_custom_height, lbl_custom_width, textField_custom_width, lbl_custom_mines, textField_custom_mines, okButton, cancelButton}));

		optionWindowInit();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), contentPanel, panel, rdbtn_beginner, rdbtn_intermediate, rdbtn_advanced, rdbtn_custom, lbl_custom_height, textField_custom_height, lbl_custom_width, textField_custom_width, lbl_custom_mines, textField_custom_mines, okButton, cancelButton}));
		setVisible(true);

	}


	/**
	 * This function split the string if its length exceeded
	 * @param textField
	 * @param length
	 */
	private void textFieldMaxLength(JTextField textField, int length){
		if(textField.getText().length()>length){
			textField.setText(textField.getText().substring(0, length));
		}
	}


	/**
	 * This function sets the new text in the label of the new amount of mines possible
	 */
	private void updateLabelMaxMines(){
		lbl_custom_mines.setText("Mines (10-"+maxMines()+"):");
	}


	/**
	 * This function checks if the integer entered is in the given range, if not, it puts the min or max value
	 * @param textField The textField
	 * @param min The minimum value
	 * @param max The maximum value
	 */
	private void textFieldCustomIntInRange(JTextField textField, int min, int max){
		int textFieldInt = parseInt(textField.getText());

		if( textFieldInt < min  ){
			textField.setText(""+min);
		} else if( textFieldInt > max ){ 	
			textField.setText(""+max);
		}else{
			textField.setText("" + parseInt(textField.getText()));
		}

	}


	/**
	 * Function to check that the number of mines entered (in the custom case) is not to high, relative
	 * to the width and the height. If it's not in range, it sets the min or max value.
	 */
	private void textFieldCustomIntInRangeMines(){

		int custom_mines = parseInt(textField_custom_mines.getText());

		int maxMines = maxMines();

		if( custom_mines < 10 ){
			textField_custom_mines.setText("10");
		} else if( custom_mines > maxMines ){
			textField_custom_mines.setText(""+maxMines);
		}
	}


	/**
	 * This function compute the maximum value of mines possible. The formula is WIDTH*HEIGHT-3*3-1.
	 * "3*3" because of the first click. And "-1" to avoid to win on first click
	 * @return
	 */
	private int maxMines(){
		int custom_height = parseInt(textField_custom_height.getText());
		int custom_width =  parseInt(textField_custom_width.getText());
		return custom_height*custom_width-10;
	}


	/**
	 * To handle the activation of the 3 text fields relative to the radio buttons .
	 */
	private void radioButtonCustom(){
		if( rdbtn_custom.isSelected() ){
			textField_custom_height.setEnabled(true);
			textField_custom_width.setEnabled(true);
			textField_custom_mines.setEnabled(true);
		} else {
			textField_custom_height.setEnabled(false);
			textField_custom_width.setEnabled(false);
			textField_custom_mines.setEnabled(false);
		}

	}


	/**
	 * This function returns an integer from a string. If the string is empty, it returns 0.
	 * @param s The string
	 * @return The parsed integer
	 */
	private int parseInt(String s){
		s = s.replaceAll("[^0-9]", "");
		return s.equals("")?0:Integer.parseInt(s);
	}	


	/**
	 * What to do on the initialization.
	 */
	private void optionWindowInit(){
		// Retrieving user preferences
		userPreferences = new PreferencesManager();
		String difficulty = userPreferences.getPref("difficulty", "beginner");

		if( difficulty.equals("beginner") ){
			rdbtn_beginner.setSelected(true);
		} else if( difficulty.equals("intermediate") ){
			rdbtn_intermediate.setSelected(true);
		} else if( difficulty.equals("advanced") ){
			rdbtn_advanced.setSelected(true);
		} else if( difficulty.equals("custom") ){
			rdbtn_custom.setSelected(true);
		}

		radioButtonCustom();

		textField_custom_height.setText(userPreferences.getPref("difficulty_custom_height", "20"));
		textField_custom_width.setText(userPreferences.getPref("difficulty_custom_width", "25"));
		textField_custom_mines.setText(userPreferences.getPref("difficulty_custom_mines", "300"));

		updateLabelMaxMines();

	}


	/**
	 * What to do when "OK" button is pressed.
	 * @param controller
	 */
	private void optionWindowOK(MinesweeperGame controller){
		userPreferences = new PreferencesManager();

		String difficulty = "beginner";

		if( rdbtn_beginner.isSelected() ){
			difficulty = "beginner";
		} else if( rdbtn_intermediate.isSelected() ){
			difficulty = "intermediate";
		} else if( rdbtn_advanced.isSelected() ){
			difficulty = "advanced";
		} else if( rdbtn_custom.isSelected() ){
			difficulty = "custom";
		}


		userPreferences.setPref("difficulty", difficulty);

		userPreferences.setPref("difficulty_custom_height", "" + parseInt(textField_custom_height.getText())); // security to prevent any upcoming bug
		userPreferences.setPref("difficulty_custom_width", "" + parseInt(textField_custom_width.getText()));
		userPreferences.setPref("difficulty_custom_mines", "" + parseInt(textField_custom_mines.getText()));

		setVisible(false);
		System.out.println(optionChanged +"");
		if(optionChanged)
			controller.newGame();

	}

}
