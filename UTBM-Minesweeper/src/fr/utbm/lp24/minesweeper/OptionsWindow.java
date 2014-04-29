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

public class OptionsWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;
	private JButton okButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_custom_height;
	private JTextField textField_custom_width;
	private JTextField textField_custom_mines;


	/**
	 * Create the dialog.
	 */
	public OptionsWindow() {
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
					// Save settings here
					setVisible(false);
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
		
		JRadioButton rdbtn_intermediate = new JRadioButton("<html>Intermediate<br>\r\n40 mines<br>\r\n16 x 16 tile grid");
		buttonGroup.add(rdbtn_intermediate);
		rdbtn_intermediate.setBounds(12, 80, 109, 43);
		panel.add(rdbtn_intermediate);
		
		JRadioButton rdbtn_advanced = new JRadioButton("<html>Advanced<br>\r\n99 mines<br>\r\n16 x 30 tile grid");
		buttonGroup.add(rdbtn_advanced);
		rdbtn_advanced.setBounds(12, 132, 109, 43);
		panel.add(rdbtn_advanced);
		
		JRadioButton rdbtn_custom = new JRadioButton("Custom");
		buttonGroup.add(rdbtn_custom);
		rdbtn_custom.setBounds(170, 28, 71, 23);
		panel.add(rdbtn_custom);
		
		JRadioButton rdbtn_beginner = new JRadioButton("<html>Beginner<br>\r\n10 mines<br>\r\n9 x 9 tile grid");
		buttonGroup.add(rdbtn_beginner);
		rdbtn_beginner.setBounds(12, 28, 109, 43);
		panel.add(rdbtn_beginner);
		
		JLabel lbl_custom_height = new JLabel("Height (9-24):");
		lbl_custom_height.setBounds(196, 50, 71, 14);
		panel.add(lbl_custom_height);
		
		textField_custom_height = new JTextField();
		lbl_custom_height.setLabelFor(textField_custom_height);
		textField_custom_height.setBounds(286, 47, 53, 20);
		panel.add(textField_custom_height);
		textField_custom_height.setColumns(10);
		
		JLabel lbl_custom_width = new JLabel("Width (9-30):");
		lbl_custom_width.setBounds(196, 80, 71, 14);
		panel.add(lbl_custom_width);
		
		textField_custom_width = new JTextField();
		lbl_custom_width.setLabelFor(textField_custom_width);
		textField_custom_width.setBounds(286, 77, 53, 20);
		panel.add(textField_custom_width);
		textField_custom_width.setColumns(10);
		
		JLabel lbl_custom_mines = new JLabel("Mines (10-668):");
		lbl_custom_mines.setBounds(196, 110, 83, 14);
		panel.add(lbl_custom_mines);
		
		textField_custom_mines = new JTextField();
		lbl_custom_mines.setLabelFor(textField_custom_mines);
		textField_custom_mines.setBounds(286, 108, 53, 20);
		panel.add(textField_custom_mines);
		textField_custom_mines.setColumns(10);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{rdbtn_beginner, rdbtn_intermediate, rdbtn_advanced, rdbtn_custom, lbl_custom_height, textField_custom_height, lbl_custom_width, textField_custom_width, lbl_custom_mines, textField_custom_mines}));
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPanel, panel, rdbtn_beginner, rdbtn_intermediate, rdbtn_advanced, rdbtn_custom, lbl_custom_height, textField_custom_height, lbl_custom_width, textField_custom_width, lbl_custom_mines, textField_custom_mines, okButton, cancelButton}));
		
		
		
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), contentPanel, panel, rdbtn_beginner, rdbtn_intermediate, rdbtn_advanced, rdbtn_custom, lbl_custom_height, textField_custom_height, lbl_custom_width, textField_custom_width, lbl_custom_mines, textField_custom_mines, okButton, cancelButton}));
			setVisible(true);
		
	}
}
