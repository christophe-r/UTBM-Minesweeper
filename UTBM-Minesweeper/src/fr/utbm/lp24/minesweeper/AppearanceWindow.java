package fr.utbm.lp24.minesweeper;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(100, 100, 300, 420);
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
			okButton.setBounds(72, 357, 100, 23);
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
			cancelButton.setBounds(182, 357, 100, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}

		rdbtnWin7Classic = new JRadioButton("Windows 7 Classic");
		buttonGroup.add(rdbtnWin7Classic);
		rdbtnWin7Classic.setBounds(20, 139, 113, 23);
		rdbtnWin7Classic.setFocusPainted(false);
		contentPanel.add(rdbtnWin7Classic);

		rdbtnWin7Flower = new JRadioButton("Windows 7 Flower");
		buttonGroup.add(rdbtnWin7Flower);
		rdbtnWin7Flower.setBounds(159, 139, 113, 23);
		rdbtnWin7Flower.setFocusPainted(false);
		contentPanel.add(rdbtnWin7Flower);

		rdbtnWin98Classic = new JRadioButton("Windows 98 Classic");
		buttonGroup.add(rdbtnWin98Classic);
		rdbtnWin98Classic.setBounds(20, 305, 119, 23);
		rdbtnWin98Classic.setFocusPainted(false);
		contentPanel.add(rdbtnWin98Classic);

		rdbtnWin8Classic = new JRadioButton("Windows 8 Classic");
		buttonGroup.add(rdbtnWin8Classic);
		rdbtnWin8Classic.setBounds(157, 305, 113, 23);
		rdbtnWin8Classic.setFocusPainted(false);
		contentPanel.add(rdbtnWin8Classic);



		JPanel preview_win7classic = new JPanel(){

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {  
				Image img = Toolkit.getDefaultToolkit().getImage(
						AppearanceWindow.class.getResource("/resources/themes/win7_classic/preview.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
			}

		};
		preview_win7classic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnWin7Classic.doClick();
			}
		});
		preview_win7classic.setBounds(20, 20, 110, 110);
		contentPanel.add(preview_win7classic);

		JPanel preview_win7flower = new JPanel(){

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {  
				Image img = Toolkit.getDefaultToolkit().getImage(
						AppearanceWindow.class.getResource("/resources/themes/win7_flower/preview.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
			}

		};
		preview_win7flower.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnWin7Flower.doClick();
			}
		});
		preview_win7flower.setBounds(160, 20, 110, 110);
		contentPanel.add(preview_win7flower);

		JPanel preview_win98classic = new JPanel(){

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {  
				Image img = Toolkit.getDefaultToolkit().getImage(
						AppearanceWindow.class.getResource("/resources/themes/win98_classic/preview.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
			}

		};
		preview_win98classic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnWin98Classic.doClick();
				//rdbtnWin98Classic.setFocusPainted(false);

			}
		});
		preview_win98classic.setBounds(20, 188, 110, 110);
		contentPanel.add(preview_win98classic);

		JPanel preview_win8classic = new JPanel(){

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {  
				Image img = Toolkit.getDefaultToolkit().getImage(
						AppearanceWindow.class.getResource("/resources/themes/win8_classic/preview.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
			}

		};
		preview_win8classic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnWin8Classic.doClick();
			}
		});
		preview_win8classic.setBounds(159, 188, 110, 110);
		contentPanel.add(preview_win8classic);


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
