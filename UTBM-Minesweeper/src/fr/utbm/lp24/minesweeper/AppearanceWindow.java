package fr.utbm.lp24.minesweeper;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;

public class AppearanceWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	private PreferencesManager userPreferences;
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JRadioButton rdbtnWin7Classic;
	private final JRadioButton rdbtnWin7Flower;
	private final JRadioButton rdbtnWin98Classic;
	private final JRadioButton rdbtnWin8Classic;


	/**
	 * Create the dialog.
	 */
	public AppearanceWindow() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Change Appearance");
		setBounds(100, 100, 550, 255);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					appearanceWindowOK();
				}
			});
			okButton.setBounds(189, 194, 100, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
				}
			});
			cancelButton.setBounds(299, 194, 100, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}

		rdbtnWin7Classic = new JRadioButton("Windows 7 Classic");
		buttonGroup.add(rdbtnWin7Classic);
		rdbtnWin7Classic.setBounds(20, 139, 125, 23);
		contentPanel.add(rdbtnWin7Classic);

		rdbtnWin7Flower = new JRadioButton("Windows 7 Flower");
		buttonGroup.add(rdbtnWin7Flower);
		rdbtnWin7Flower.setBounds(147, 139, 125, 23);
		contentPanel.add(rdbtnWin7Flower);

		rdbtnWin98Classic = new JRadioButton("Windows 98 Classic");
		buttonGroup.add(rdbtnWin98Classic);
		rdbtnWin98Classic.setBounds(274, 139, 125, 23);
		contentPanel.add(rdbtnWin98Classic);
		
		rdbtnWin8Classic = new JRadioButton("Windows 8 Classic");
		buttonGroup.add(rdbtnWin8Classic);
		rdbtnWin8Classic.setBounds(408, 139, 125, 23);
		contentPanel.add(rdbtnWin8Classic);


		appearanceWindowInit();


		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);


	}


	/**
	 * Called when the window opens
	 */
	private void appearanceWindowInit(){
		// Retrieving user preferences
		userPreferences = new PreferencesManager();
		String theme = userPreferences.getPref("theme", "win7_classic");

		if( theme.equals("win7_classic") ){
			rdbtnWin7Classic.setSelected(true);
		} else if( theme.equals("win7_flower") ){
			rdbtnWin7Flower.setSelected(true);
		} else if( theme.equals("win98_classic") ){
			rdbtnWin98Classic.setSelected(true);
		} else if( theme.equals("win8_classic") ){
			rdbtnWin8Classic.setSelected(true);
		}

	}


	/**
	 * Called when the user clicks on the OK button
	 */
	private void appearanceWindowOK(){

		String theme = "win7_classic";

		if( rdbtnWin7Classic.isSelected() ){
			theme = "win7_classic";
		} else if( rdbtnWin7Flower.isSelected() ){
			theme = "win7_flower";
		} else if( rdbtnWin98Classic.isSelected() ){
			theme = "win98_classic";
		} else if( rdbtnWin8Classic.isSelected() ){
			theme = "win8_classic";
		}

		userPreferences.setPref("theme", theme);

		setVisible(false);

	}


}
